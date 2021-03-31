package iuniversity.model.exams;

import java.time.LocalDateTime;

import iuniversity.model.didactics.AcademicYear;
import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall.ExamType;

public interface ExamCallBuilder {

    /**
     * Sets the academic.
     * 
     * @param academicYear the academic year of the exam call
     * @return instance of builder
     */
    ExamCallBuilder academicYear(AcademicYear academicYear);

    /**
     * 
     * @param callStart the start datetime of the exam call
     * @return instance of builder
     */
    ExamCallBuilder callStart(LocalDateTime callStart);

    /**
     * Sets the Exam call type.
     * 
     * @param examType the exam type of the exam call. See {@link ExamType}
     * @return instance of builder
     */
    ExamCallBuilder examType(ExamType examType);

    /**
     * Set the number of student that can register to the call.
     * 
     * @param maximumStudents the maximum number of students
     * @return instance of Builder
     */
    ExamCallBuilder maximumStudents(int maximumStudents);

    /**
     * Set the course of Exam call.
     * 
     * @param course the course of the exam call
     * @return instance of Builder
     */
    ExamCallBuilder course(Course course);

    /**
     * Build the exam call.
     * 
     * @return instance of ExamCall
     */
    ExamCall build();

}