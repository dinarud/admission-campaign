package com.grsu.committee.entities;

import com.grsu.committee.enums.Subject;

import java.util.HashMap;
import java.util.Objects;

public class Enrollee extends Person {

    private Faculty faculty;
    private HashMap<Subject, Integer> pointsMap;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public HashMap<Subject, Integer> getPointsMap() {
        return pointsMap;
    }

    public void setPointsMap(HashMap<Subject, Integer> pointsMap) {
        this.pointsMap = pointsMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Enrollee enrollee = (Enrollee) o;
        return Objects.equals(faculty, enrollee.faculty) &&
                Objects.equals(pointsMap, enrollee.pointsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), faculty, pointsMap);
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "faculty=" + faculty +
                ", pointsMap=" + pointsMap +
                "} " + super.toString();
    }
}
