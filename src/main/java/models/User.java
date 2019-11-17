package models;

import java.util.Optional;

public class User {
    private Long id;
    private String login;
    private Optional<String> mail;
    private String password;
    private String role;


    public User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.mail = builder.mail;
        this.password = builder.password;
        this.role = builder.role;
    }


    public static class Builder {
        private Long id;
        private String login;
        private Optional<String> mail;
        private String password;
        private String role;

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder email(String mail) {
            this.mail = Optional.ofNullable(mail);
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }


        public User build() {
            return new User(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Optional<String> getMail() {
        return mail;
    }

    public void setMail(Optional<String> mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
