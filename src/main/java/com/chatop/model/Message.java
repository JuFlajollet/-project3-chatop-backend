package com.chatop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long userId;
    private long rentalId;
    private String message;

    public Message(long userId, long rentalId, String message) {
        this.userId = userId;
        this.rentalId = rentalId;
        this.message = message;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {

        private long userId;
        private long rentalId;
        private String message;

        public MessageBuilder setUserId(final long userId) {
            this.userId = userId;
            return this;
        }

        public MessageBuilder setRentalId(final long rentalId) {
            this.rentalId = rentalId;
            return this;
        }

        public MessageBuilder setMessage(final String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(userId, rentalId, message);
        }
    }
}
