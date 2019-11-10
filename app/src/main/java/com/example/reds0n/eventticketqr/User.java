package com.example.reds0n.eventticketqr;

/**
 * Created by reds0n on 5/26/18.
 */

public class User {

        public String password;
        public String email;

    public User() {

    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
