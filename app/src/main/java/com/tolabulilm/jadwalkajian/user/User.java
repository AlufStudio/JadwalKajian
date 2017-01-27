package com.tolabulilm.jadwalkajian.user;

/**
 * Created by fata on 1/27/2017.
 */

public class User {
    private String id;
    private String email;

    public User() {
    }

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}