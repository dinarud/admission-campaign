package com.grsu.committee.service.impl;

import com.grsu.committee.dataaccess.impl.AdministratorDao;
import com.grsu.committee.dataaccess.impl.EnrolleeDao;
import com.grsu.committee.dataaccess.impl.UserCredentialsDao;
import com.grsu.committee.entities.Administrator;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.UserCredentials;
import com.grsu.committee.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UserServiceImpl implements UserService {

    private AdministratorDao administratorDao;
    private EnrolleeDao enrolleeDao;
    private UserCredentialsDao userCredentialsDao;

    public UserServiceImpl() {
        super();
        this.administratorDao = new AdministratorDao();
        this.enrolleeDao = new EnrolleeDao();
        this.userCredentialsDao = new UserCredentialsDao();
    }

    @Override
    public void registerAdmin(Administrator administrator, UserCredentials userCredentials) {
        userCredentialsDao.save(userCredentials);
        administrator.setUserCredentials(userCredentials);
        administrator.setCreated(new Date());
        administratorDao.save(administrator);
    }

    @Override
    public void registerEnrollee(Enrollee enrollee, UserCredentials userCredentials) {
        userCredentialsDao.save(userCredentials);
        enrollee.setUserCredentials(userCredentials);
        enrollee.setCreated(new Date());
        enrolleeDao.save(enrollee);
    }

    @Override
    public UserCredentials getCredentials(Long id) {
        return userCredentialsDao.get(id);
    }

    @Override
    public UserCredentials getByEmailAndPassword(String email, String password) {
        return userCredentialsDao.find(email, password);
    }

    @Override
    public Collection<? extends String> resolveRoles(Long id) {
        UserCredentials userCredentials = userCredentialsDao.get(id);
        return Collections.singletonList(userCredentials.getRole().name());
    }

}
