package com.grsu.committee.service.impl;

import com.grsu.committee.dataaccess.impl.AdministratorDao;
import com.grsu.committee.dataaccess.impl.SheetDao;
import com.grsu.committee.entities.Administrator;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.service.AdministratorService;
import com.grsu.committee.table.AdministratorTable;

public class AdministratorServiceImpl extends AbstractServiceImpl<AdministratorTable, Administrator> implements AdministratorService {

    private SheetDao sheetDao;

    public AdministratorServiceImpl() {
        dao = new AdministratorDao();
        sheetDao = new SheetDao();
    }

    @Override
    public void register(Sheet sheet, Enrollee enrollee) {
        if (sheet.getFaculty() == null) {
            sheet.setFaculty(enrollee.getFaculty());
        }

        if (!sheet.getRegisteredEnrollee().contains(enrollee)) {
            sheet.getRegisteredEnrollee().add(enrollee);
        }

        sheetDao.saveOrUpdate(sheet);
    }
}
