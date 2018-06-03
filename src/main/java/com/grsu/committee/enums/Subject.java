package com.grsu.committee.enums;

public enum Subject {
    MATH("Математика"),
    PHYSIC("Физика"),
    RUSSIAN("Русский язык"),
    ENGLISH("Английский язык"),
    BIOLOGY("Биология"),
    HISTORY("История"),
    SCHOOL_CERTIFICATE("Школьный сертификат");

    private String name;

    Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
