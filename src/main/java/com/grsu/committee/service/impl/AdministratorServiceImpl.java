package com.grsu.committee.service.impl;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.service.AdministratorService;

public class AdministratorServiceImpl implements AdministratorService {
    @Override
    public void register(Sheet sheet, Enrollee enrollee) {
        if (sheet.getFaculty() == null) {
            sheet.setFaculty(enrollee.getFaculty());
        }

        if (!sheet.getRegisteredEnrollee().contains(enrollee)) {
            sheet.getRegisteredEnrollee().add(enrollee);
        }

        //todo save sheet

    }
}
