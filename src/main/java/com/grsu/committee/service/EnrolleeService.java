package com.grsu.committee.service;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.enums.Subject;

import java.util.HashMap;

public interface EnrolleeService {

    Enrollee addEnrolleeToFaculty(Enrollee enrollee, String facultyName);

    Enrollee putMarks(Enrollee enrollee, HashMap<Subject, Integer> pointsMap);
}
