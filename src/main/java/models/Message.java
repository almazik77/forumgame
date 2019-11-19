package models;

public class Message {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String message;

    private Message(Builder builder) {
        this.id = builder.id;
        this.fromUserId = builder.fromUserId;
        this.toUserId = builder.toUserId;
        this.message = builder.message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long fromUserId;
        private Long toUserId;
        private String message;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder fromUserId(Long fromUserId) {
            this.fromUserId = fromUserId;
            return this;
        }

        public Builder toUserId(Long toUserId) {
            this.toUserId = toUserId;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public Message(Long id, Long fromUserId, Long toUserId, String message) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Message setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public Message setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
        return this;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public Message setToUserId(Long toUserId) {
        this.toUserId = toUserId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }
}