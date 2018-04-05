package com.grsu.committee.entities;

import java.util.List;
import java.util.Objects;

public class Sheet extends AbstractModel {

    private int yearOfReceipt;
    private int maxMembersNumber;
    private Faculty faculty;
    private List<Enrollee> registeredEnrollee;

    public int getYearOfReceipt() {
        return yearOfReceipt;
    }

    public void setYearOfReceipt(int yearOfReceipt) {
        this.yearOfReceipt = yearOfReceipt;
    }

    public int getMaxMembersNumber() {
        return maxMembersNumber;
    }

    public void setMaxMembersNumber(int maxMembersNumber) {
        this.maxMembersNumber = maxMembersNumber;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Enrollee> getRegisteredEnrollee() {
        return registeredEnrollee;
    }

    public void setRegisteredEnrollee(List<Enrollee> registeredEnrollee) {
        this.registeredEnrollee = registeredEnrollee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sheet sheet = (Sheet) o;
        return yearOfReceipt == sheet.yearOfReceipt &&
                maxMembersNumber == sheet.maxMembersNumber &&
                Objects.equals(faculty, sheet.faculty) &&
                Objects.equals(registeredEnrollee, sheet.registeredEnrollee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), yearOfReceipt, maxMembersNumber, faculty, registeredEnrollee);
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "yearOfReceipt=" + yearOfReceipt +
                ", maxMembersNumber=" + maxMembersNumber +
                ", faculty=" + faculty +
                ", registeredEnrollee=" + registeredEnrollee +
                "} " + super.toString();
    }
}
