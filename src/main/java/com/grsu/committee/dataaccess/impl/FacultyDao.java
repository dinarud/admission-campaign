package com.grsu.committee.dataaccess.impl;

import com.grsu.committee.dataaccess.AbstractDao;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.table.FacultyTable;

import java.io.Serializable;

public class FacultyDao extends AbstractDao<FacultyTable, Faculty> implements Serializable {

    @Override
    protected Class<FacultyTable> getTableClass() {
        return FacultyTable.class;
    }

    @Override
    public void update(Faculty entity) {
        // get existing data
        final FacultyTable FacultyTable = deserializeFromXml();
        // find by ID
        for (final Faculty row : FacultyTable.getRows()) {
            if (row.getId().equals(entity.getId())) {
                // found!!!
                // copy data
                row.setName(entity.getName());
                row.setSubjectsRequested(entity.getSubjectsRequested());

                break;
            }
        }
        // save updated table
        serializeToXml(FacultyTable);
    }

}
