package com.rentalps.rentalps.controller;

import config.Database;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Validasi Error", "Username dan Password harus diisi!");
            return;
        }

        String sql = "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn != null ? conn.prepareStatement(sql) : null) {

            if (conn == null) {
                showAlert("Database Error", "Tidak dapat terhubung ke database!");
                return;
            }

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String role = rs.getString("role").trim().toLowerCase();

                    if (role.equals("admin")) {
                        openWindow("/fxml/DashboardView.fxml", "Dashboard Admin");
                    } else {
                        openWindow("/fxml/DashboardViewUser.fxml", "Dashboard User");
                    }

                    Stage stage = (Stage) txtUsername.getScene().getWindow();
                    stage.close();

                } else {
                    showAlert("Login Gagal", "Username atau password salah!");
                }
            }

        } catch (SQLException sqlEx) {
            System.err.println("SQL Error: " + sqlEx.getMessage());
            showAlert("Database Error", "Kesalahan database terjadi!");

        } catch (NullPointerException npe) {
            System.err.println("FXML atau Resource Hilang: " + npe.getMessage());
            showAlert("File Error", "Resource tidak ditemukan, periksa kembali file FXML!");

        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            showAlert("Error", "Terjadi kesalahan pada aplikasi!");
        }
    }

    private void openWindow(String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/logos.png")));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.err.println("Gagal membuka halaman: " + title);
            showAlert("Navigasi Error", "Tidak dapat membuka halaman: " + title);
        }
    }

    private void showAlert(String title, String msg) {
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle(title);
        al.setHeaderText(null);
        al.setContentText(msg);
        al.showAndWait();
    }
}