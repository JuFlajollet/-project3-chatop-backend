package com.chatop.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class DBUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonAlias({ "login" })
    private String email;
    private String password;
    private String role;
    private String createdAt;
    private String updatedAt;

    public DBUser() {}

    public DBUser(String name, String email, String password, String role, String createdAt, String updatedAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static DBUser.UserBuilder builder() {
        return new DBUser.UserBuilder();
    }

    public static class UserBuilder {

        private String email;
        private String name;
        private String password;
        private String role;
        private String createdAt;
        private String updatedAt;

        public DBUser.UserBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public DBUser.UserBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public DBUser.UserBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public DBUser.UserBuilder setCreatedAt(final String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DBUser.UserBuilder setUpdatedAt(final String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public DBUser build() {
            return new DBUser(email, name, password, role, createdAt, updatedAt);
        }
    }
}
