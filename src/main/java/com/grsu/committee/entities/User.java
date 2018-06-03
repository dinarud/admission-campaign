package com.grsu.committee.entities;

import java.util.Date;
import java.util.Objects;

public abstract class User extends AbstractModel {

    private String firstName;
    private String lastName;
    private int age;
    private Date created;
    private UserCredentials userCredentials;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(created, user.created) &&
                Objects.equals(userCredentials, user.userCredentials);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), firstName, lastName, age, created, userCredentials);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", created=" + created +
                ", userCredentials=" + userCredentials +
                "} " + super.toString();
    }
}
