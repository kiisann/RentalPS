package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private StackPane contentArea;

    @FXML
    private VBox sidebar;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnUser;

    @FXML
    private Button btnPS;

    @FXML
    private Button btnTransaksi;

    @FXML
    private Button btnLaporan;

    @FXML
    private Button btnLogout;

    private boolean menuVisible = false;

    private final String ACTIVE_STYLE =
            "-fx-background-radius:999; -fx-background-color:#22D3EE; " +
            "-fx-text-fill:#020617; -fx-font-weight:bold; " +
            "-fx-alignment:CENTER_LEFT; -fx-padding:10 18;";

    private final String NORMAL_STYLE =
            "-fx-background-radius:999; -fx-background-color:transparent; " +
            "-fx-text-fill:white; -fx-alignment:CENTER_LEFT; -fx-padding:10 14;";

    @FXML
    public void initialize() {
        if (sidebar != null) {
            sidebar.setVisible(false);
            sidebar.setManaged(true); 
        }
        loadContent("/fxml/DashboardHome.fxml");
        setActiveButton(btnDashboard);
    }

    private void loadContent(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setActiveButton(Button active) {
        Button[] buttons = {btnDashboard, btnUser, btnPS, btnTransaksi, btnLaporan};

        for (Button b : buttons) {
            if (b == null) continue;
            if (b == active) {
                b.setStyle(ACTIVE_STYLE);
            } else {
                b.setStyle(NORMAL_STYLE);
            }
        }

        if (btnLogout != null) {
            btnLogout.setStyle(
                    "-fx-background-color: transparent; " +
                    "-fx-text-fill: #FCA5A5; " +
                    "-fx-font-size: 16; " +
                    "-fx-alignment: CENTER_LEFT; " +
                    "-fx-padding: 10 14;"
            );
        }
    }

    @FXML
    private void toggleMenu() {
        menuVisible = !menuVisible;
        if (sidebar != null) {
            sidebar.setVisible(menuVisible);
            sidebar.setManaged(menuVisible);
        }
    }

    @FXML
    private void handleDashboard(ActionEvent event) {
        loadContent("/fxml/DashboardHome.fxml");
        setActiveButton(btnDashboard);
    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        loadContent("/fxml/UserManagement.fxml");
        setActiveButton(btnUser);
    }

    @FXML
    private void handlePS(ActionEvent event) {
        loadContent("/fxml/PSView.fxml");
        setActiveButton(btnPS);
    }

    @FXML
    private void handleTransaksi(ActionEvent event) {
        loadContent("/fxml/TransaksiView.fxml");
        setActiveButton(btnTransaksi);
    }

    @FXML
    private void handleLaporan(ActionEvent event) {
        loadContent("/fxml/LaporanView.fxml");
        setActiveButton(btnLaporan);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            Stage stage = (Stage) contentArea.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml"));
            stage.setTitle("Rental PS");
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}