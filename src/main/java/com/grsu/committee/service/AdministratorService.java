package com.grsu.committee.service;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;

public interface AdministratorService {

    void addEnrolleeToSheet(Sheet sheet, Enrollee enrollee);

}
