package com.grsu.committee.web.page.home;

import com.grsu.committee.web.page.AbstractPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class HomePage extends AbstractPage {

    public HomePage() {
        add(new FeedbackPanel("feedback"));
    }

}
