package com.grsu.committee.service.impl;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.enums.Subject;
import com.grsu.committee.service.EnrolleeService;

import java.util.HashMap;

public class EnrolleeServiceImpl implements EnrolleeService {


    @Override
    public Enrollee register(Enrollee enrollee, Faculty faculty) {
        enrollee.setFaculty(faculty);

        return enrollee;
    }

    @Override
    public Enrollee putMarks(Enrollee enrollee, HashMap<Subject, Integer> pointsMap) {
        enrollee.setPointsMap(pointsMap);

        return enrollee;
    }
}
