package com.grsu.committee.service.impl;

import com.grsu.committee.dataaccess.impl.EnrolleeDao;
import com.grsu.committee.dataaccess.impl.FacultyDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.enums.Subject;
import com.grsu.committee.service.EnrolleeService;
import com.grsu.committee.table.EnrolleeTable;

import java.util.HashMap;
import java.util.List;

public class EnrolleeServiceImpl extends AbstractServiceImpl<EnrolleeTable, Enrollee> implements EnrolleeService {

    private FacultyDao facultyDao;

    public EnrolleeServiceImpl() {
        dao = new EnrolleeDao();
        facultyDao = new FacultyDao();
    }

    @Override
    public Enrollee register(Enrollee enrollee, String facultyName) {
        List<Faculty> facultyList = facultyDao.getAll();

        Faculty facultyByName = null;

        for (Faculty faculty : facultyList) {
            if (faculty.getName().equals(facultyName)) {
                facultyByName = faculty;
            }
        }

        if (facultyByName == null) {
            return null;
        }

        enrollee.setFaculty(facultyByName);
        dao.saveOrUpdate(enrollee);

        return enrollee;
    }

    @Override
    public Enrollee putMarks(Enrollee enrollee, HashMap<Subject, Integer> pointsMap) {
        enrollee.setPointsMap(pointsMap);
        dao.saveOrUpdate(enrollee);

        return enrollee;
    }
}
