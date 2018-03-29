package com.grsu.committee.service.impl;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.service.SheetService;
import com.grsu.committee.utils.Utils;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SheetServiceImpl implements SheetService {
    @Override
    public Set<Enrollee> ResolveStudents(Sheet sheet) {

        SortedSet<Enrollee> sortedFromBestEnrollees = new TreeSet<>(Utils.getSheetEnrolleeComparator());
        sortedFromBestEnrollees.addAll(sheet.getRegisteredEnrollee());

        return sortedFromBestEnrollees.stream().limit(sheet.getFullMembersNumber()).collect(Collectors.toSet());
    }

}
