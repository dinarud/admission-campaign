package com.grsu.committee.web.app;

import com.grsu.committee.entities.UserCredentials;
import com.grsu.committee.service.UserService;
import com.grsu.committee.service.impl.UserServiceImpl;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class AuthorizedSession extends AuthenticatedWebSession {
    private UserService userService;

    private UserCredentials loggedUser;

    private Roles roles;

    public AuthorizedSession(Request request) {

        super(request);
        userService = new UserServiceImpl();
    }

    public static AuthorizedSession get() {
        return (AuthorizedSession) Session.get();
    }

    @Override
    public boolean authenticate(final String email, final String password) {
        loggedUser = userService.getByEmailAndPassword(email, password);
        return loggedUser != null;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && (roles == null)) {
            roles = new Roles();
            roles.addAll(userService.resolveRoles(loggedUser.getId()));
        }
        return roles;
    }

    @Override
    public void signOut() {
        super.signOut();
        loggedUser = null;
        roles = null;
    }

    public UserCredentials getLoggedUser() {
        return loggedUser;
    }
}
