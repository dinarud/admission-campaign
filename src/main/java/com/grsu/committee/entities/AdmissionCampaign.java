package com.grsu.committee.entities;

import java.util.List;
import java.util.Objects;

public class AdmissionCampaign {
    private int yearOfReceipt;
    private int fullMembersNumber;
    private int budgetMembersNumber;
    private Faculty faculty;
    private List<Enrollee> enrolleeList;

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

    public int getBudgetMembersNumber() {
        return budgetMembersNumber;
    }

    public void setBudgetMembersNumber(int budgetMembersNumber) {
        this.budgetMembersNumber = budgetMembersNumber;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Enrollee> getEnrolleeList() {
        return enrolleeList;
    }

    public void setEnrolleeList(List<Enrollee> enrolleeList) {
        this.enrolleeList = enrolleeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdmissionCampaign that = (AdmissionCampaign) o;
        return yearOfReceipt == that.yearOfReceipt &&
                fullMembersNumber == that.fullMembersNumber &&
                budgetMembersNumber == that.budgetMembersNumber &&
                Objects.equals(faculty, that.faculty) &&
                Objects.equals(enrolleeList, that.enrolleeList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(yearOfReceipt, fullMembersNumber, budgetMembersNumber, faculty, enrolleeList);
    }

    @Override
    public String toString() {
        return "AdmissionCampaign{" +
                "yearOfReceipt=" + yearOfReceipt +
                ", fullMembersNumber=" + fullMembersNumber +
                ", budgetMembersNumber=" + budgetMembersNumber +
                ", faculty=" + faculty +
                ", enrolleeList=" + enrolleeList +
                '}';
    }
}
