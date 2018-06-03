package com.grsu.committee.service;

import com.grsu.committee.entities.Enrollee;

import java.util.List;

public interface SheetService {

    List<Enrollee> resolveStudents(int year, String facultyName);

}
