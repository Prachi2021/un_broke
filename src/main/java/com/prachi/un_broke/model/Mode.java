package com.prachi.un_broke.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="modes")
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mode_id;
    private String mode;


    @ManyToOne
    @JoinColumn(name = "mode_id")
    private User user;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "mode")
    private Set<Expense> expenses;

    public Mode(){}
    public Mode(String mode, User user){
        this.mode = mode;
        this.user = user;
    }

    // Getters
    public int getModeId() {
        return mode_id;
    }
    public String getMode() {
        return mode;
    }
    public User getUser() {
        return user;
    }

    // Setters
    public void setModeId(int id) {
        this.mode_id = id;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
