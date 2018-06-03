package com.grsu.committee.unit;

import com.grsu.committee.dataaccess.impl.EnrolleeDao;
import com.grsu.committee.dataaccess.impl.FacultyDao;
import com.grsu.committee.dataaccess.impl.SheetDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.entities.Sheet;
import org.junit.Test;

import java.util.List;

public class SheetDaoTest {

    private static SheetDao sheetDao = new SheetDao();
    private static FacultyDao facultyDao = new FacultyDao();
    private static EnrolleeDao enrolleeDao = new EnrolleeDao();

    @Test
    public void testAdd() {
        List<Faculty> facultyList = facultyDao.getAll();
        if (facultyList.isEmpty()) {
            return;
        }

        List<Enrollee> enrolleeList = enrolleeDao.getAll();
        if (enrolleeList.isEmpty()) {
            return;
        }

        Sheet sheet = new Sheet();
        sheet.setYearOfReceipt(2018);
        sheet.setFaculty(facultyList.get(0));
        sheet.setMaxMembersNumber(10);
        sheet.setRegisteredEnrollee(enrolleeList);

        sheetDao.save(sheet);
    }
}
