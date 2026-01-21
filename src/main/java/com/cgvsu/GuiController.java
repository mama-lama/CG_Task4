package com.cgvsu;

import com.cgvsu.math.NormalsCalculator;
import com.cgvsu.model.Model;
import com.cgvsu.model.ModelTriangulator;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.render_engine.Camera;
import com.cgvsu.render_engine.RenderEngine;
import com.cgvsu.render_engine.RenderSettings;
import com.cgvsu.scene.CameraGizmoFactory;
import com.cgvsu.scene.CameraManager;
import com.cgvsu.scene.SceneCamera;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.vecmath.Vector3f;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GuiController {

    private static final float TRANSLATION = 0.5F;
    private static final float GIZMO_SCALE = 4.0F;

    @FXML
    AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    private Model mesh = null;
    private Image texture = null;

    private final CameraManager cameraManager = new CameraManager();
    private final RenderSettings renderSettings = new RenderSettings(false, true, true, 0xFFB0B0B0);

    private boolean showCameraGizmos = true;
    private double lastMouseX;
    private double lastMouseY;
    private float orbitYaw = 0.0F;
    private float orbitPitch = 0.0F;
    private float orbitDistance = 100.0F;

    private Timeline timeline;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        setupDefaultCamera();
        setupMouseControls();
        setupKeyboardControls();
        canvas.setFocusTraversable(true);
        canvas.requestFocus();

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
            Camera active = getActiveCamera();
            if (active == null) {
                return;
            }
            active.setAspectRatio((float) (width / height));

            List<Model> renderModels = new ArrayList<>();
            if (mesh != null) {
                renderModels.add(mesh);
            }
            if (showCameraGizmos) {
                renderModels.addAll(buildCameraGizmos());
            }
            if (!renderModels.isEmpty()) {
                RenderEngine.renderModels(
                        canvas.getGraphicsContext2D(),
                        active,
                        renderModels,
                        (int) width,
                        (int) height,
                        texture,
                        renderSettings);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());

        try {
            String fileContent = Files.readString(fileName);
            mesh = ObjReader.read(fileContent);
            postprocessModel();
        } catch (IOException exception) {
            // Stub: keep UI minimal for this task set.
        }
    }

    @FXML
    private void onOpenTextureMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image (*.png, *.jpg, *.jpeg, *.bmp)", "*.png", "*.jpg", "*.jpeg", "*.bmp"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        fileChooser.setTitle("Load Texture");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        texture = new Image(file.toURI().toString());
    }

    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        getActiveCamera().movePosition(new Vector3f(0, 0, -TRANSLATION));
        syncOrbitDistance();
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        getActiveCamera().movePosition(new Vector3f(0, 0, TRANSLATION));
        syncOrbitDistance();
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        getActiveCamera().movePosition(new Vector3f(TRANSLATION, 0, 0));
        syncOrbitDistance();
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        getActiveCamera().movePosition(new Vector3f(-TRANSLATION, 0, 0));
        syncOrbitDistance();
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        getActiveCamera().movePosition(new Vector3f(0, TRANSLATION, 0));
        syncOrbitDistance();
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        getActiveCamera().movePosition(new Vector3f(0, -TRANSLATION, 0));
        syncOrbitDistance();
    }

    private void setupMouseControls() {
        Vector3f delta = new Vector3f(getActiveCamera().getPosition());
        delta.sub(getActiveCamera().getTarget());
        orbitDistance = delta.length();
        canvas.setOnMousePressed(event -> {
            lastMouseX = event.getX();
            lastMouseY = event.getY();
        });
        canvas.setOnMouseDragged(event -> {
            if (!event.isPrimaryButtonDown()) {
                return;
            }
            double deltaX = event.getX() - lastMouseX;
            double deltaY = event.getY() - lastMouseY;
            lastMouseX = event.getX();
            lastMouseY = event.getY();

            float sensitivity = 0.3F;
            orbitYaw += deltaX * sensitivity;
            orbitPitch -= deltaY * sensitivity;
            orbitPitch = Math.max(-89.0F, Math.min(89.0F, orbitPitch));

            updateOrbitCamera();
        });
        canvas.setOnScroll(event -> {
            float zoomSpeed = 0.08F;
            orbitDistance *= (1.0F - event.getDeltaY() * zoomSpeed / 100.0F);
            orbitDistance = Math.max(2.0F, Math.min(500.0F, orbitDistance));
            updateOrbitCamera();
        });
    }

    private void setupKeyboardControls() {
        canvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene == null) {
                return;
            }
            newScene.setOnKeyPressed(event -> handleKeyPressed(event.getCode()));
        });
    }

    private void handleKeyPressed(KeyCode code) {
        switch (code) {
            case UP -> handleCameraForward(null);
            case DOWN -> handleCameraBackward(null);
            case LEFT -> handleCameraLeft(null);
            case RIGHT -> handleCameraRight(null);
            case TAB -> cameraManager.cycleActive();
            case C -> addCameraFromActive();
            case DELETE -> cameraManager.removeActive();
            case W -> renderSettings.setDrawWireframe(!renderSettings.isDrawWireframe());
            case T -> renderSettings.setUseTexture(!renderSettings.isUseTexture());
            case L -> renderSettings.setUseLighting(!renderSettings.isUseLighting());
            case G -> showCameraGizmos = !showCameraGizmos;
            case P -> postprocessModel();
            default -> {
            }
        }
        syncOrbitDistance();
    }

    private void postprocessModel() {
        if (mesh == null) {
            return;
        }
        ModelTriangulator.triangulate(mesh);
        NormalsCalculator.recalculateNormals(mesh);
    }

    private Camera getActiveCamera() {
        SceneCamera active = cameraManager.getActive();
        return active == null ? null : active.getCamera();
    }

    private void setupDefaultCamera() {
        Camera camera = new Camera(
                new Vector3f(0, 0, 100),
                new Vector3f(0, 0, 0),
                1.0F,
                1,
                0.01F,
                100);
        SceneCamera defaultCamera = new SceneCamera(camera, "Camera 1", CameraGizmoFactory.createGizmo());
        cameraManager.add(defaultCamera);
    }

    private void addCameraFromActive() {
        SceneCamera active = cameraManager.getActive();
        if (active == null) {
            return;
        }
        Camera source = active.getCamera();
        Camera copy = new Camera(
                new Vector3f(source.getPosition()),
                new Vector3f(source.getTarget()),
                source.getFov(),
                source.getAspectRatio(),
                source.getNearPlane(),
                source.getFarPlane());
        SceneCamera newCamera = new SceneCamera(
                copy,
                "Camera " + (cameraManager.getCameras().size() + 1),
                CameraGizmoFactory.createGizmo());
        cameraManager.add(newCamera);
        cameraManager.setActive(newCamera);
    }

    private List<Model> buildCameraGizmos() {
        List<Model> gizmos = new ArrayList<>();
        for (SceneCamera sceneCamera : cameraManager.getCameras()) {
            Vector3f position = sceneCamera.getCamera().getPosition();
            gizmos.add(CameraGizmoFactory.createGizmoAt(position, GIZMO_SCALE));
        }
        return gizmos;
    }

    private void updateOrbitCamera() {
        Vector3f target = getActiveCamera().getTarget();
        double yawRad = Math.toRadians(orbitYaw);
        double pitchRad = Math.toRadians(orbitPitch);

        float x = (float) (target.x + orbitDistance * Math.cos(pitchRad) * Math.sin(yawRad));
        float y = (float) (target.y + orbitDistance * Math.sin(pitchRad));
        float z = (float) (target.z + orbitDistance * Math.cos(pitchRad) * Math.cos(yawRad));
        getActiveCamera().setPosition(new Vector3f(x, y, z));
    }

    private void syncOrbitDistance() {
        Vector3f delta = new Vector3f(getActiveCamera().getPosition());
        delta.sub(getActiveCamera().getTarget());
        orbitDistance = delta.length();
    }
}
