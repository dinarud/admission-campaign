package com.grsu.committee.entities;

import com.grsu.committee.enums.EnrollmentForm;
import com.grsu.committee.enums.Subject;

import java.util.HashMap;
import java.util.Objects;

public class Sheet {

    private int year;
    private String fullEnrolleeName;
    private String facultyName;
    private HashMap<Subject, Integer> pointsMap;
    private EnrollmentForm enrollmentForm;

    public String getFullEnrolleeName() {
        return fullEnrolleeName;
    }

    public void setFullEnrolleeName(String fullEnrolleeName) {
        this.fullEnrolleeName = fullEnrolleeName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public HashMap<Subject, Integer> getPointsMap() {
        return pointsMap;
    }

    public void setPointsMap(HashMap<Subject, Integer> pointsMap) {
        this.pointsMap = pointsMap;
    }

    public EnrollmentForm getEnrollmentForm() {
        return enrollmentForm;
    }

    public void setEnrollmentForm(EnrollmentForm enrollmentForm) {
        this.enrollmentForm = enrollmentForm;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sheet sheet = (Sheet) o;
        return year == sheet.year &&
                Objects.equals(fullEnrolleeName, sheet.fullEnrolleeName) &&
                Objects.equals(facultyName, sheet.facultyName) &&
                Objects.equals(pointsMap, sheet.pointsMap) &&
                enrollmentForm == sheet.enrollmentForm;
    }

    @Override
    public int hashCode() {

        return Objects.hash(year, fullEnrolleeName, facultyName, pointsMap, enrollmentForm);
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "year=" + year +
                ", fullEnrolleeName='" + fullEnrolleeName + '\'' +
                ", facultyName='" + facultyName + '\'' +
                ", pointsMap=" + pointsMap +
                ", enrollmentForm=" + enrollmentForm +
                '}';
    }
}
