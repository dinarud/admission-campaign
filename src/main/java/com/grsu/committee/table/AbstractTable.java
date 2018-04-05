package com.grsu.committee.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTable<E> implements Serializable {
    protected List<E> rows;

    public List<E> getRows() {
        {
            if (rows == null) {
                rows = new ArrayList<>();
            }
            return rows;
        }
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
