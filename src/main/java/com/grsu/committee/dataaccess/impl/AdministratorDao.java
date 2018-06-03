package com.grsu.committee.dataaccess.impl;

import com.grsu.committee.dataaccess.AbstractDao;
import com.grsu.committee.entities.Administrator;
import com.grsu.committee.table.AdministratorTable;

import java.io.Serializable;

public class AdministratorDao extends AbstractDao<AdministratorTable, Administrator> implements Serializable {

    @Override
    protected Class<AdministratorTable> getTableClass() {
        return AdministratorTable.class;
    }

    @Override
    public void update(Administrator entity) {
        // get existing data
        final AdministratorTable AdministratorTable = deserializeFromXml();
        // find by ID
        for (final Administrator row : AdministratorTable.getRows()) {
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
        serializeToXml(AdministratorTable);
    }

}
