package com.grsu.committee.web.app;

import com.grsu.committee.web.page.home.HomePage;
import com.grsu.committee.web.page.login.LoginPage;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

public class WicketApplication extends AuthenticatedWebApplication {
    @Override
    public void init() {
        super.init();
        //mountPage("/home", HomePage.class);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return AuthorizedSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }
}
