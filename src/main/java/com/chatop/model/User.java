package com.chatop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public User(String email, String name, String password, LocalDate createdAt, LocalDate updatedAt) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    public static class UserBuilder {

        private String email;
        private String name;
        private String password;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        public User.UserBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public User.UserBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public User.UserBuilder setPassword(final String password) {
            this.password = password;
            return this;
        }

        public User.UserBuilder setCreatedAt(final LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User.UserBuilder setUpdatedAt(final LocalDate updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {
            return new User(email, name, password, createdAt, updatedAt);
        }
    }
}
