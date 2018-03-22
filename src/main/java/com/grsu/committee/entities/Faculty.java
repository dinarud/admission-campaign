package com.grsu.committee.entities;

import com.grsu.committee.enums.Subject;

import java.util.Objects;
import java.util.Set;

public class Faculty {
    private String name;
    private Set<Subject> subjectsRequested;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Subject> getSubjectsRequested() {
        return subjectsRequested;
    }

    public void setSubjectsRequested(Set<Subject> subjectsRequested) {
        this.subjectsRequested = subjectsRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) &&
                Objects.equals(subjectsRequested, faculty.subjectsRequested);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, subjectsRequested);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", subjectsRequested=" + subjectsRequested +
                '}';
    }
}
