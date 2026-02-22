package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardControllerUser {

    @FXML
    private StackPane contentArea;

    @FXML
    private VBox sidebar;

    @FXML
    private Button btnDashboardUser;

    @FXML
    private Button btnPSUser;

    @FXML
    private Button btnTransaksiUser;

    @FXML
    private Button btnLogout;

    private boolean menuOpen = false;

    private static final String BASE_STYLE =
            "-fx-background-color: transparent; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16; " +
            "-fx-alignment: CENTER_LEFT; " +
            "-fx-padding: 10 14;";

    private static final String ACTIVE_STYLE =
            "-fx-background-color: #22D3EE; " +
            "-fx-text-fill: #020617; " +
            "-fx-font-size: 16; " +
            "-fx-font-weight: bold; " +
            "-fx-background-radius: 999; " +
            "-fx-alignment: CENTER_LEFT; " +
            "-fx-padding: 10 18;";

    @FXML
    public void initialize() {
        if (sidebar != null) {
            sidebar.setVisible(false);
        }

        setActiveButton(btnDashboardUser);
        loadView("/fxml/DashboardHomeUser.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (NullPointerException npe) {
            System.err.println("File FXML tidak ditemukan: " + fxmlPath);
            showError("File tampilan tidak ditemukan!\nPeriksa path: " + fxmlPath);
        } catch (IOException e) {
            System.err.println("Gagal memuat view: " + fxmlPath);
            e.printStackTrace();
            showError("Terjadi kesalahan saat memuat tampilan.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Kesalahan Terjadi");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setActiveButton(Button active) {
        Button[] buttons = {btnDashboardUser, btnPSUser, btnTransaksiUser, btnLogout};

        for (Button b : buttons) {
            if (b == null) continue;

            if (b == active) {
                b.setStyle(ACTIVE_STYLE);
            } else {
                if (b == btnLogout) {
                    b.setStyle(
                            "-fx-background-color: transparent; " +
                            "-fx-text-fill: #FCA5A5; " +
                            "-fx-font-size: 16; " +
                            "-fx-alignment: CENTER_LEFT; " +
                            "-fx-padding: 10 14;"
                    );
                } else {
                    b.setStyle(BASE_STYLE);
                }
            }
        }
    }

    @FXML
    private void handleDashboardUser() {
        setActiveButton(btnDashboardUser);
        loadView("/fxml/DashboardHomeUser.fxml");
    }

    @FXML
    private void handlePSUser() {
        setActiveButton(btnPSUser);
        loadView("/fxml/PSUserView.fxml");
    }

    @FXML
    private void handleTransaksiUser() {
        setActiveButton(btnTransaksiUser);
        loadView("/fxml/TransaksiView.fxml");
    }

    @FXML
    private void handleLogout() {
        try {
            Stage stage = (Stage) contentArea.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
            stage.setTitle("Rental PS");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            System.err.println("Gagal logout ke LoginView.fxml");
            e.printStackTrace();
            showError("Tidak dapat kembali ke halaman Login!");
        }
    }

    @FXML
    private void toggleMenu() {
        menuOpen = !menuOpen;
        if (sidebar != null) {
            sidebar.setVisible(menuOpen);
        }
    }
}