package com.grsu.committee.service.impl;

import com.grsu.committee.dataaccess.impl.SheetDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.service.SheetService;
import com.grsu.committee.table.SheetTable;
import com.grsu.committee.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SheetServiceImpl extends AbstractServiceImpl<SheetTable, Sheet> implements SheetService, Serializable {

    public SheetServiceImpl() {
        dao = new SheetDao();
    }

    @Override
    public List<Enrollee> resolveStudents(int year, String facultyName) {

        Sheet yearSheet = null;

        List<Sheet> sheetList = dao.getAll();

        for (Sheet sheet : sheetList) {
            if (sheet.getYearOfReceipt() == year) {
                yearSheet = sheet;
            }
        }

        if (yearSheet == null) {
            return new ArrayList<>();
        }

        List<Enrollee> filteredEnrollees = new ArrayList<>();
        if (facultyName != null && !facultyName.isEmpty()) {
            for (Enrollee enrollee : yearSheet.getRegisteredEnrollee()) {
                if (enrollee.getFaculty().getName().equalsIgnoreCase(facultyName)) {
                    filteredEnrollees.add(enrollee);
                }
            }
        }

        filteredEnrollees.sort(Utils.getSheetEnrolleeComparator());

        if (filteredEnrollees.size() > yearSheet.getMaxMembersNumber()) {
            filteredEnrollees.subList(yearSheet.getMaxMembersNumber(), filteredEnrollees.size()).clear();
        }

        return filteredEnrollees;
    }

}
