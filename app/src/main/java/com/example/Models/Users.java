package com.example.Models;

public class Users {
    private int idUsers;
    private String username;
    private String userPassword;
    private boolean status;

    public Users() {
    }

    public Users(String username, String userPassword, boolean status) {
        this.username = username;
        this.userPassword = userPassword;
        this.status = status;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


