package controller;

import model.User;
import config.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import model.User;

public class UserManagementController {

    @FXML private TableView<User> tableUsers;
    @FXML private TableColumn<User, String> colUsername;
    @FXML private TableColumn<User, String> colRole;

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    
    private final ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colUsername.setCellValueFactory(data -> data.getValue().usernameProperty());
        colRole.setCellValueFactory(data -> data.getValue().roleProperty());
        colPassword.setCellValueFactory(data -> data.getValue().passwordProperty());

        loadUsers();
    }

    private void loadUsers() {
        userList.clear();
        try {
            Connection conn = Database.getConnection();
            ResultSet rs = conn.createStatement().executeQuery(
        "SELECT * FROM users WHERE role = 'user'");

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("id_users"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableUsers.setItems(userList);
    }

    @FXML
    private void handleAddUser() {
        String u = txtUsername.getText().trim();
        String p = txtPassword.getText().trim();

        if (u.isEmpty() || p.isEmpty()) {
            show("Semua form harus diisi!");
            return;
        }

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users(username, password, role) VALUES (?,?,?)"
            );
            ps.setString(1, u);
            ps.setString(2, p);
            ps.setString(3, "user");
            ps.executeUpdate();

            show("User berhasil ditambahkan!");
            loadUsers();
            txtUsername.clear();
            txtPassword.clear();

        } catch (Exception e) {
            e.printStackTrace();
            show("Terjadi kesalahan: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selected = tableUsers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            show("Pilih user yang ingin dihapus!");
            return;
        }

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users WHERE id_users=?");
            ps.setInt(1, selected.getId());
            ps.executeUpdate();

            show("User berhasil dihapus!");
            loadUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML private TableColumn<User, String> colPassword;


    private void show(String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(m);
        a.showAndWait();
    }
}