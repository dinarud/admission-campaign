package com.grsu.committee.web.common.renderer;

import com.grsu.committee.entities.User;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

public class UserChoicePRenderer extends ChoiceRenderer<User> {

    public static final UserChoicePRenderer INSTANCE = new UserChoicePRenderer();

    private UserChoicePRenderer() {
        super();
    }

    @Override
    public Object getDisplayValue(User object) {
        return String.format("%s %s %s", object.getFirstName(), object.getLastName(), object.getUserCredentials().getRole());
    }

    @Override
    public String getIdValue(User object, int index) {
        return String.valueOf(object.getId());
    }
}
