package com.grsu.committee.service;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;

import java.util.Set;

public interface SheetService {

    Set<Enrollee> ResolveStudents(Sheet sheet);

}
