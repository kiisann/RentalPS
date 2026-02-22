package model;

import javafx.beans.property.*;

public class User {

    private final IntegerProperty id_users;
    private final StringProperty username;
    private final StringProperty password;
    private final StringProperty role;

    public User(int id_users, String username, String password, String role) {
        this.id_users = new SimpleIntegerProperty(id_users);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.role = new SimpleStringProperty(role);
    }

    public String getInfo() {
        return "User: " + getUsername() + " | Role: " + getRole();
    }

    public int getId() { 
        return id_users.get(); 
    }
    public void setId(int value) { 
        id_users.set(value); 
    }
    public IntegerProperty idProperty() { 
        return id_users; 
    }

    public String getUsername() { 
        return username.get(); 
    }
    public void setUsername(String value) { 
        username.set(value); 
    }
    public StringProperty usernameProperty() { 
        return username; 
    }

    public String getPassword() { 
        return password.get(); 
    }
    public void setPassword(String value) { 
        password.set(value); 
    }
    public StringProperty passwordProperty() { 
        return password; 
    }

    public String getRole() { 
        return role.get(); 
    }
    public void setRole(String value) { 
        role.set(value); 
    }
    public StringProperty roleProperty() { 
        return role; 
    }
}