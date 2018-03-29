package com.grsu.committee.entities;

import java.util.Objects;
import java.util.Set;

public class Sheet extends AbstractModel {

    private int yearOfReceipt;
    private int fullMembersNumber;
    private Faculty faculty;
    private Set<Enrollee> registeredEnrollee;

    public int getYearOfReceipt() {
        return yearOfReceipt;
    }

    public void setYearOfReceipt(int yearOfReceipt) {
        this.yearOfReceipt = yearOfReceipt;
    }

    public int getFullMembersNumber() {
        return fullMembersNumber;
    }

    public void setFullMembersNumber(int fullMembersNumber) {
        this.fullMembersNumber = fullMembersNumber;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Set<Enrollee> getRegisteredEnrollee() {
        return registeredEnrollee;
    }

    public void setRegisteredEnrollee(Set<Enrollee> registeredEnrollee) {
        this.registeredEnrollee = registeredEnrollee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sheet sheet = (Sheet) o;
        return yearOfReceipt == sheet.yearOfReceipt &&
                fullMembersNumber == sheet.fullMembersNumber &&
                Objects.equals(faculty, sheet.faculty) &&
                Objects.equals(registeredEnrollee, sheet.registeredEnrollee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), yearOfReceipt, fullMembersNumber, faculty, registeredEnrollee);
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "yearOfReceipt=" + yearOfReceipt +
                ", fullMembersNumber=" + fullMembersNumber +
                ", faculty=" + faculty +
                ", registeredEnrollee=" + registeredEnrollee +
                "} " + super.toString();
    }
}
