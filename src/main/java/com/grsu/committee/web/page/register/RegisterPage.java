package com.grsu.committee.web.page.register;

import com.grsu.committee.dataaccess.impl.FacultyDao;
import com.grsu.committee.dataaccess.impl.SheetDao;
import com.grsu.committee.entities.Enrollee;
import com.grsu.committee.entities.Faculty;
import com.grsu.committee.entities.Sheet;
import com.grsu.committee.entities.UserCredentials;
import com.grsu.committee.enums.Subject;
import com.grsu.committee.enums.UserRole;
import com.grsu.committee.service.UserService;
import com.grsu.committee.service.impl.UserServiceImpl;
import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.string.Strings;

import java.util.*;

public class RegisterPage extends WebPage {
    private FacultyDao facultyDao;
    private SheetDao sheetDao;
    private UserService userService;

    public static final String ID_FORM = "register_form";

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int age;

    private Faculty faculty;

    private Subject subject1K;
    private Integer subject1V;

    private Subject subject2K;
    private Integer subject2V;

    private Subject subject3K;
    private Integer subject3V;

    private Subject subject4K;
    private Integer subject4V;

    public RegisterPage() {
        facultyDao = new FacultyDao();
        sheetDao = new SheetDao();
        userService = new UserServiceImpl();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        // if already logged then should not see login page at all
//        if (AuthenticatedWebSession.get().isSignedIn()) {
//            setResponsePage(Application.get().getHomePage());
//        }

        final Form<Void> form = new Form<>(ID_FORM);
        form.setDefaultModel(new CompoundPropertyModel<>(this));
        form.add(new EmailTextField("email"));
        PasswordTextField passwordTextField = new PasswordTextField("password");
        form.add(passwordTextField);
        form.add(new RequiredTextField<String>("firstName"));
        form.add(new RequiredTextField<String>("lastName"));
        NumberTextField<Integer> ageField = new NumberTextField<>("age");
        ageField.setMinimum(10);
        ageField.setMaximum(100);
        form.add(ageField);

        List<Faculty> facultyList = facultyDao.getAll();
        List<Subject> subjectList = Arrays.asList(Subject.values());

        Model<String> labelModel = Model.of("Предметы");
        Label subjects = new Label("facultySubjects", labelModel);
        subjects.setOutputMarkupId(true);
        form.add(subjects);

        DropDownChoice<Faculty> facultyDropdown = new DropDownChoice<Faculty>("faculty", facultyList);
        facultyDropdown.add(
                new AjaxFormComponentUpdatingBehavior("onchange") {
                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        labelModel.setObject(faculty.getSubjectsRequested().toString());
                        target.add(subjects);
                    }
                }
        );
        form.add(facultyDropdown);

        DropDownChoice<Subject> subject1Dropdown = new DropDownChoice<Subject>("subject1K", subjectList);
        form.add(subject1Dropdown);
        NumberTextField<Integer> subject1Number = new NumberTextField<Integer>("subject1V");
        subject1Number.setMinimum(0);
        subject1Number.setMaximum(100);
        form.add(subject1Number);

        DropDownChoice<Subject> subject2Dropdown = new DropDownChoice<Subject>("subject2K", subjectList);
        form.add(subject2Dropdown);
        NumberTextField<Integer> subject2Number = new NumberTextField<Integer>("subject2V");
        subject2Number.setMinimum(0);
        subject2Number.setMaximum(100);
        form.add(subject2Number);

        DropDownChoice<Subject> subject3Dropdown = new DropDownChoice<Subject>("subject3K", subjectList);
        form.add(subject3Dropdown);
        NumberTextField<Integer> subject3Number = new NumberTextField<Integer>("subject3V");
        subject3Number.setMinimum(0);
        subject3Number.setMaximum(100);
        form.add(subject3Number);

        DropDownChoice<Subject> subject4Dropdown = new DropDownChoice<Subject>("subject4K", subjectList);
        form.add(subject4Dropdown);
        NumberTextField<Integer> subject4Number = new NumberTextField<Integer>("subject4V");
        subject4Number.setMinimum(0);
        subject4Number.setMaximum(100);
        form.add(subject4Number);

        form.add(new SubmitLink("submit-btn") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                if (Strings.isEmpty(email) || Strings.isEmpty(password)) {
                    return;
                }

                UserCredentials userCredentials = new UserCredentials();
                userCredentials.setEmail(email);
                userCredentials.setPassword(password);
                userCredentials.setRole(UserRole.USER);

                Enrollee enrollee = new Enrollee();
                enrollee.setFirstName(firstName);
                enrollee.setLastName(lastName);
                enrollee.setAge(age);
                enrollee.setFaculty(faculty);

                HashMap<Subject, Integer> pointsMap = new HashMap<>();
                pointsMap.put(subject1K, subject1V);
                pointsMap.put(subject2K, subject2V);
                pointsMap.put(subject3K, subject3V);
                pointsMap.put(subject4K, subject4V);
                enrollee.setPointsMap(pointsMap);

                userService.registerEnrollee(enrollee, userCredentials);

                if (AuthenticatedWebSession.exists() && AuthenticatedWebSession.get().isSignedIn()) {
                    AuthenticatedWebSession.get().signOut();
                }

                List<Sheet> sheetList = sheetDao.getAll();
                Sheet thisYearSheet = null;
                for (Sheet sheet : sheetList) {
                    if (sheet.getYearOfReceipt() == Calendar.getInstance().get(Calendar.YEAR)) {
                        thisYearSheet = sheet;
                        break;
                    }
                }
                if (thisYearSheet != null) {
                    thisYearSheet.getRegisteredEnrollee().add(enrollee);
                } else {
                    thisYearSheet = new Sheet();
                    thisYearSheet.setYearOfReceipt(Calendar.getInstance().get(Calendar.YEAR));
                    thisYearSheet.setFaculty(faculty);
                    thisYearSheet.setMaxMembersNumber(10);
                    thisYearSheet.setRegisteredEnrollee(Collections.singletonList(enrollee));
                }
                sheetDao.save(thisYearSheet);

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

        add(new FeedbackPanel("feedback"));
    }
}
