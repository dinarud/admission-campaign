package com.grsu.committee.web.page.sheet;

import com.grsu.committee.dataaccess.impl.FacultyDao;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.web.page.AbstractPage;
import com.grsu.committee.web.page.sheet.panel.SheetPanel;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SheetPage extends AbstractPage {

    public static final String ID_FORM = "filter_form";

    private FacultyDao facultyDao;

    private int year;
    private Faculty faculty;

    public SheetPage(int year, Faculty faculty) {
        super();
        facultyDao = new FacultyDao();

        if (year == 0) {
            this.year = Calendar.getInstance().get(Calendar.YEAR);
        } else {
            this.year = year;
        }

        if (faculty == null) {
            this.faculty = facultyDao.getAll().get(0);
        } else {
            this.faculty = faculty;
        }

        add(new SheetPanel("list-panel", this.year, this.faculty));
        add(new FeedbackPanel("feedback"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Form<Void> form = new Form<>(ID_FORM);
        form.setDefaultModel(new CompoundPropertyModel<>(this));

        DropDownChoice<Integer> yearList = new DropDownChoice<Integer>("year", new Model<>(year), Arrays.asList(2017, 2018));
        yearList.setModelObject(this.year);
        form.add(yearList);

        List<Faculty> facultyList = facultyDao.getAll();
        DropDownChoice<Faculty> facultyDropdown = new DropDownChoice<>("faculty", new Model<>(faculty), facultyList);
        facultyDropdown.setModelObject(faculty);
        form.add(facultyDropdown);

        form.add(new SubmitLink("filterButton") {
            @Override
            public void onSubmit() {
                super.onSubmit();

                setResponsePage(new SheetPage(year, faculty));
            }
        });

        add(form);
    }


}
