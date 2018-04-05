package com.grsu.committee.service.impl;

import com.grsu.committee.dataaccess.impl.SheetDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.service.SheetService;
import com.grsu.committee.table.SheetTable;
import com.grsu.committee.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SheetServiceImpl extends AbstractServiceImpl<SheetTable, Sheet> implements SheetService {

    public SheetServiceImpl() {
        dao = new SheetDao();
    }

    @Override
    public List<Enrollee> resolveStudents(int year) {

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

        List<Enrollee> sortedFromBestEnrollees = new ArrayList<>(yearSheet.getRegisteredEnrollee());
        sortedFromBestEnrollees.sort(Utils.getSheetEnrolleeComparator());

        if (sortedFromBestEnrollees.size() > yearSheet.getMaxMembersNumber()) {
            sortedFromBestEnrollees.subList(yearSheet.getMaxMembersNumber(), sortedFromBestEnrollees.size()).clear();
        }

        return sortedFromBestEnrollees;
    }

}
