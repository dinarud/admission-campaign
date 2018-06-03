package com.grsu.committee.service;

import com.grsu.committee.entities.Administrator;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.UserCredentials;

import java.util.Collection;

public interface UserService {
    void registerAdmin(Administrator administrator, UserCredentials userCredentials);

    void registerEnrollee(Enrollee enrollee, UserCredentials userCredentials);

    UserCredentials getCredentials(Long id);

    UserCredentials getByEmailAndPassword(String email, String password);

    Collection<? extends String> resolveRoles(Long id);
}
