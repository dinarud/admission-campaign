package com.grsu.committee.unit;

import com.grsu.committee.dataaccess.impl.FacultyDao;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.enums.Subject;
import org.junit.Test;

import java.util.EnumSet;

public class FacultyDaoTest {
    private static FacultyDao facultyDao = new FacultyDao();

    @Test
    public void testAdd() {
        Faculty mathFaculty = new Faculty();
        mathFaculty.setName("Факультет информатики");
        EnumSet<Subject> enumSet1 = EnumSet.of(
                Subject.SCHOOL_CERTIFICATE, Subject.MATH, Subject.PHYSIC, Subject.RUSSIAN);
        mathFaculty.setSubjectsRequested(enumSet1);
        facultyDao.save(mathFaculty);

        Faculty journalistFaculty = new Faculty();
        journalistFaculty.setName("Факультет журналистики");
        EnumSet<Subject> enumSet2 = EnumSet.of(
                Subject.SCHOOL_CERTIFICATE, Subject.HISTORY, Subject.ENGLISH, Subject.RUSSIAN);
        journalistFaculty.setSubjectsRequested(enumSet2);
        facultyDao.save(journalistFaculty);


    }
}
