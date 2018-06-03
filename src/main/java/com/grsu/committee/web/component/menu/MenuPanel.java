package com.grsu.committee.web.component.menu;

import com.grsu.committee.web.app.AuthorizedSession;
import com.grsu.committee.web.page.home.HomePage;
import com.grsu.committee.web.page.login.LoginPage;
import com.grsu.committee.web.page.register.RegisterPage;
import com.grsu.committee.web.page.sheet.SheetPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel {
    public MenuPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Link("link-home") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });

        Link linkLogin = new Link("link-login") {
            @Override
            public void onClick() {
                setResponsePage(new LoginPage());
            }
        };
        linkLogin.setVisible(!AuthorizedSession.get().isSignedIn());
        add(linkLogin);

        Link linkRegister = new Link("link-register") {
            @Override
            public void onClick() {
                setResponsePage(new RegisterPage());
            }
        };
        linkRegister.setVisible(!AuthorizedSession.get().isSignedIn());
        add(linkRegister);

        Link linkSheet = new Link("link-sheet") {
            @Override
            public void onClick() {
                setResponsePage(new SheetPage(0, null));
            }
        };
        add(linkSheet);

        Link linkLogout = new Link("link-logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(HomePage.class);
            }
        };
        linkLogout.setVisible(AuthorizedSession.get().isSignedIn());
        add(linkLogout);
    }
}
