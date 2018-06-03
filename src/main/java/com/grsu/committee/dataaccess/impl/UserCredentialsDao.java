package com.grsu.committee.dataaccess.impl;

import com.grsu.committee.dataaccess.AbstractDao;
import com.grsu.committee.entities.UserCredentials;
import com.grsu.committee.table.UserCredentialsTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserCredentialsDao extends AbstractDao<UserCredentialsTable, UserCredentials> implements Serializable {
    @Override
    protected Class<UserCredentialsTable> getTableClass() {
        return UserCredentialsTable.class;
    }

    @Override
    public void update(UserCredentials entity) {
        // get existing data
        final UserCredentialsTable userCredentialsTable = deserializeFromXml();
        // find by ID
        for (final UserCredentials row : userCredentialsTable.getRows()) {
            if (row.getId().equals(entity.getId())) {
                // found!!!
                // copy data
                row.setEmail(entity.getEmail());
                row.setPassword(entity.getPassword());
                row.setRole(entity.getRole());

                break;
            }
        }
        // save updated table
        serializeToXml(userCredentialsTable);
    }

    public UserCredentials find(String email, String password) {

        List<UserCredentials> userCredentialsList = getAll();
        List<UserCredentials> allItems = new ArrayList<>();

        for (UserCredentials userCredentials : userCredentialsList) {
            boolean isEmailEqual = userCredentials.getEmail().equalsIgnoreCase(email);
            boolean isPasswordEqual = userCredentials.getPassword().equals(password);
            if (isEmailEqual && isPasswordEqual) {
                allItems.add(userCredentials);
            }
        }
        if (allItems.isEmpty()) {
            return null;
        } else if (allItems.size() == 1) {
            return allItems.get(0);
        } else {
            throw new IllegalArgumentException("more than 1 user found ");
        }
    }
}
