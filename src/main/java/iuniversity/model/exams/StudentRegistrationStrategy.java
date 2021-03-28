package iuniversity.model.exams;

import java.util.List;

import iuniversity.model.user.Student;

@FunctionalInterface
public interface StudentRegistrationStrategy {

    /**
     * The student registration Strategy.
     * @param list the current list to the student must be added
     * @param student the student to add to the list
     */
    void register(List<Student> list, Student student);
}
