package com.HarvestHopper.model;

import java.util.Date;

public class User {
    private int id;
    private String UserId;
    private String emailId;
    private String password;
    private String ROLE;
    private Date created_at;

    public User(int id, String userId, String emailId, String password, String ROLE, Date created_at) {
        this.id = id;
        UserId = userId;
        this.emailId = emailId;
        this.password = password;
        this.ROLE = ROLE;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
