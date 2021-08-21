package com.ressis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {
    public User() {
    }

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @Column(name = "privileged")
    private boolean privileged;

    public User(String name, String email, String password, boolean privileged) {
        this(null, name, email, password, privileged);
    }

    public User(Integer id, String name, String email, String password, boolean privileged) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.privileged = privileged;
    }

    public User(User user) {
        this(user.id, user.name, user.email, user.password, user.privileged);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrivileged() {
        return privileged;
    }

    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", privileged=" + privileged +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
