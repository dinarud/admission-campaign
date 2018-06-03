package com.grsu.committee.web.page.sheet.panel;

import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.service.SheetService;
import com.grsu.committee.service.impl.SheetServiceImpl;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class SheetPanel extends Panel implements Serializable {

    private SheetService sheetService;

    private int year;
    private Faculty faculty;
    protected List<Enrollee> enrolleeList;

    public SheetPanel(String id, int year, Faculty faculty) {
        super(id);
        sheetService = new SheetServiceImpl();

        this.year = year;
        this.faculty = faculty;
        this.enrolleeList = sheetService.resolveStudents(year, faculty.getName());

        EnrolleeDataProvider enrolleeDataProvider = new EnrolleeDataProvider();
        DataView<Enrollee> enrolleeDataView = new DataView<Enrollee>("rows", enrolleeDataProvider) {
            @Override
            protected void populateItem(Item<Enrollee> item) {
                Enrollee enrollee = item.getModelObject();
                item.add(new InvisibleLabel("id", enrollee.getId()));
                item.add(DateLabel.forDatePattern("created", Model.of(enrollee.getCreated()), "dd-MM-yyyy"));
                item.add(new Label("lastName", enrollee.getLastName()));
                item.add(new Label("firstName", enrollee.getFirstName()));
                item.add(new Label("sum", enrollee.getPointsMap().values().stream().mapToInt(value -> value).sum()));
            }
        };
        add(enrolleeDataView);

        InvisibleLabel label = new InvisibleLabel("label-id", "id");
        add(label);
    }

    private class EnrolleeDataProvider extends SortableDataProvider<Enrollee, Serializable> {

        @Override
        public Iterator<? extends Enrollee> iterator(long l, long l1) {
            return enrolleeList.iterator();
        }

        @Override
        public long size() {
            return enrolleeList.size();
        }

        @Override
        public IModel<Enrollee> model(Enrollee enrollee) {
            return new Model(enrollee);
        }
    }

    @AuthorizeAction(roles = {"admin"}, action = Action.RENDER)
    private class InvisibleLabel extends Label {

        public InvisibleLabel(String id, Serializable label) {
            super(id, label);
        }
    }


}
