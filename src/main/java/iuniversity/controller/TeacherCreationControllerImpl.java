package iuniversity.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.user.Teacher;
import iuniversity.model.user.TeacherImpl;
import iuniversity.model.user.User.Gender;
import iuniversity.model.user.User.UserType;
import iuniversity.view.users.StudentCreationView;
import iuniversity.view.users.TeacherCreationView;

public class TeacherCreationControllerImpl extends AbstractController implements TeacherCreationController {

    private AccountsManager accountManager = new AccountsManagerImpl();
    @Override
    public void createTeacher(String firstName, String lastName, LocalDate dateOfBirth, 
            Gender gender, String address, Set<Course> courses) {
        Set<Teacher> teachers = this.getModel().getArchive().getTeachers();
        int occurencies = 0;
        for (Teacher t : teachers) {
            if (t.getName().equals(firstName) && t.getLastName().equals(lastName)) {
                occurencies++;
            }
        }
        final String newUsername = accountManager.makeUsername(UserType.TEACHER, firstName, lastName, occurencies);
        final String newPassword = accountManager.createPassword();
        final Teacher teacher = new TeacherImpl(firstName, lastName, newUsername, dateOfBirth, gender, address, this.getModel().getArchive().getNewUserId(), 
                this.getModel().getArchive().getNewTeacherRegistrationNumber(), courses);
        this.getModel().getArchive().addTeacher(teacher);
        this.accountManager.registerTeacherAccount(teacher, newPassword, occurencies);
        ((TeacherCreationView) this.getView()).showTeacherCredentials(newUsername, newPassword);
    }
    @Override
    public void initializeChoices() {
        HashSet<Gender> genders = new HashSet<>();
        genders.addAll(Arrays.asList(Gender.values()));
        ((TeacherCreationView) this.getView()).setCourseChoices(this.getModel().getDidacticsManager().getCourse());
        ((TeacherCreationView) this.getView()).setGenderChoices(genders);
    }
    
}