package com.grsu.committee.dataaccess.impl;

import com.grsu.committee.dataaccess.AbstractDao;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.table.SheetTable;

import java.io.Serializable;

public class SheetDao extends AbstractDao<SheetTable, Sheet> implements Serializable {

    @Override
    protected Class<SheetTable> getTableClass() {
        return SheetTable.class;
    }

    @Override
    public void update(Sheet entity) {
        // get existing data
        final SheetTable sheetTable = deserializeFromXml();
        // find by ID
        for (final Sheet row : sheetTable.getRows()) {
            if (row.getId().equals(entity.getId())) {
                // found!!!
                // copy data
                row.setFaculty(entity.getFaculty());
                row.setMaxMembersNumber(entity.getMaxMembersNumber());
                row.setYearOfReceipt(entity.getYearOfReceipt());
                row.setRegisteredEnrollee(entity.getRegisteredEnrollee());

                break;
            }
        }
        // save updated table
        serializeToXml(sheetTable);
    }
}
