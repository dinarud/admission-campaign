package com.grsu.committee.entities;

import com.grsu.committee.enums.Subject;

import java.util.EnumSet;
import java.util.Objects;

public class Faculty extends AbstractModel {
    private String name;
    private EnumSet<Subject> subjectsRequested;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumSet<Subject> getSubjectsRequested() {
        return subjectsRequested;
    }

    public void setSubjectsRequested(EnumSet<Subject> subjectsRequested) {
        this.subjectsRequested = subjectsRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) &&
                Objects.equals(subjectsRequested, faculty.subjectsRequested);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, subjectsRequested);
    }

    @Override
    public String toString() {
        return name;
    }
}
