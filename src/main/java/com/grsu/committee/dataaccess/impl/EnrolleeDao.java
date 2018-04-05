package com.grsu.committee.dataaccess.impl;

import com.grsu.committee.dataaccess.AbstractDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.table.EnrolleeTable;

import java.io.Serializable;

public class EnrolleeDao extends AbstractDao<EnrolleeTable, Enrollee> implements Serializable {

    @Override
    protected Class<EnrolleeTable> getTableClass() {
        return EnrolleeTable.class;
    }

    @Override
    public void update(Enrollee entity) {
        // get existing data
        final EnrolleeTable EnrolleeTable = deserializeFromXml();
        // find by ID
        for (final Enrollee row : EnrolleeTable.getRows()) {
            if (row.getId().equals(entity.getId())) {
                // found!!!
                // copy data
                row.setFirstName(entity.getFirstName());
                row.setLastName(entity.getLastName());
                row.setAge(entity.getAge());

                break;
            }
        }
        // save updated table
        serializeToXml(EnrolleeTable);
    }

}
