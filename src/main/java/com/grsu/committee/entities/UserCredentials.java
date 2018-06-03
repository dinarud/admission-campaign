package com.grsu.committee.entities;

import com.grsu.committee.enums.UserRole;

import java.util.Objects;

public class UserCredentials extends AbstractModel {
    private String email;

    private String password;

    private UserRole role;

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                role == that.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), email, password, role);
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                "} " + super.toString();
    }
}
