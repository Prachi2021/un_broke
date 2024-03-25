package com.prachi.un_broke.dto;

import com.prachi.un_broke.model.User;

public class UserRegistrationDTO {
    private String user_name;
    private String email;
    private String password;
    private User user;

    public UserRegistrationDTO(){
    }

    public UserRegistrationDTO(String user_name, String email, String password){
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public User getUser() {
        return user;
    }
    public String getUser_name() {
        return user_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    // Setters
    public void setUser(User user) {
        this.user = user;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
