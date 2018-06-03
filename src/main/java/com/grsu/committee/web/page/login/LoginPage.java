package com.grsu.committee.web.page.login;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

public class LoginPage extends WebPage {
    public static final String ID_FORM = "login_form";

    private String email;
    private String password;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // if already logged then should not see login page at all
        if (AuthenticatedWebSession.get().isSignedIn()) {
            setResponsePage(Application.get().getHomePage());
        }

        final Form<Void> form = new Form<>(ID_FORM);
        form.setDefaultModel(new CompoundPropertyModel<>(this));
        EmailTextField emailTextField = new EmailTextField("email");
        emailTextField.isRequired();
        form.add(emailTextField);

        PasswordTextField passwordTextField = new PasswordTextField("password");
        passwordTextField.isRequired();
        form.add(passwordTextField);

        form.add(new SubmitLink("submit-btn") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                if (Strings.isEmpty(email) || Strings.isEmpty(password)) {
                    return;
                }
                final boolean authResult = AuthenticatedWebSession.get().signIn(email, password);
                if (authResult) {
                    // continueToOriginalDestination();
                    setResponsePage(Application.get().getHomePage());
                } else {
                    error("loging error!");
                }
            }
        });

        add(form);

        add(new FeedbackPanel("feedbackpanel"));
    }
}