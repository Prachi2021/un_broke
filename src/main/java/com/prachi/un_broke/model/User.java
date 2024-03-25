package com.prachi.un_broke.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_name;
    private String email;
    private String password;


    public User(){}

    public User(String user_name, String email, String password){
        this.email = email;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getUser_id() {
        return user_id;
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
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
