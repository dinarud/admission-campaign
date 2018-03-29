package com.grsu.committee.service;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.enums.Subject;

import java.util.HashMap;

public interface EnrolleeService {

    Enrollee register(Enrollee enrollee, Faculty faculty);

    Enrollee putMarks(Enrollee enrollee, HashMap<Subject, Integer> pointsMap);
}
