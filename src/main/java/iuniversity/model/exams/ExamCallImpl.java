package iuniversity.model.exams;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import iuniversity.model.didactics.AcademicYear;
import iuniversity.model.didactics.Course;
import iuniversity.model.user.Student;
import iuniversity.model.user.Teacher;

public final class ExamCallImpl implements ExamCall {

    private static final int DAYS_BEFORE_CALL = 2;

    private final Optional<Integer> maxStudents;
    private final AcademicYear academicYear;
    private final Set<Student> registeredStudents;
    private final LocalDateTime callStart;
    private final LocalDateTime registrationStart;
    private final LocalDateTime registrationEnd;
    private final ExamType examType;
    private final Course course;

    public ExamCallImpl(final Course course, final LocalDateTime callStart, final AcademicYear academicYear,
            final ExamType examType, final Optional<Integer> maxStudents) {
        this.course = course;
        this.callStart = callStart;
        this.academicYear = academicYear;
        this.examType = examType;
        this.maxStudents = maxStudents;
        this.registeredStudents = new HashSet<>();
        this.registrationStart = LocalDateTime.now();
        this.registrationEnd = callStart.minusDays(1);
    }

    @Override
    public Teacher getTeacher() {
        return null;
    }

    @Override
    public AcademicYear getAcademicYear() {
        return this.academicYear;
    }

    @Override
    public Set<Student> getRegisteredStudents() {
        return Collections.unmodifiableSet(this.registeredStudents);
    }

    @Override
    public LocalDateTime getStart() {
        return this.callStart;
    }

    @Override
    public ExamType getExamType() {
        return this.examType;
    }

    @Override
    public CallStatus getStatus() {
        final LocalDateTime now = LocalDateTime.now();
        return now.isAfter(registrationStart) && now.isBefore(registrationEnd) ? CallStatus.OPEN : CallStatus.CLOSED;
    }

    @Override
    public Course getCourse() {
        return this.course;
    }

    @Override
    public Optional<Integer> maxStudents() {
        return this.maxStudents;
    }

    @Override
    public void registerStudent(final Student student) {
        this.registeredStudents.add(student);
    }

    public static class Builder implements ExamCallBuilder {

        private Optional<Integer> maximumStudents;
        private AcademicYear academicYear;
        private LocalDateTime start;
        private ExamType type;
        private Course course;

        public Builder() {
            this.maximumStudents = Optional.empty();
        }

        @Override
        public final ExamCallBuilder academicYear(final AcademicYear academicYear) {
            this.academicYear = academicYear;
            return this;
        }

        @Override
        public final ExamCallBuilder callStart(final LocalDateTime callStart) {
            this.start = callStart;
            return this;
        }

        @Override
        public final ExamCallBuilder examType(final ExamType examType) {
            this.type = examType;
            return this;
        }

        @Override
        public final ExamCallBuilder course(final Course course) {
            this.course = course;
            return this;
        }

        @Override
        public final ExamCallBuilder maximumStudents(final int maximumStudents) {
            this.maximumStudents = Optional.of(maximumStudents);
            return this;
        }

        @Override
        public final ExamCall build() {
            if (Objects.isNull(course) || Objects.isNull(start) || Objects.isNull(academicYear) || Objects.isNull(type)) {
                throw new IllegalStateException();
            } else if (start.isBefore(LocalDateTime.now().plusDays(DAYS_BEFORE_CALL))) {
                throw new IllegalStateException("ExamCall must be at least " + DAYS_BEFORE_CALL + " days after today");
            }
            return new ExamCallImpl(course, start, academicYear, type, maximumStudents);
        }

        @Override
        public final int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((academicYear == null) ? 0 : academicYear.hashCode());
            result = prime * result + ((course == null) ? 0 : course.hashCode());
            result = prime * result + ((start == null) ? 0 : start.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }

        @Override
        public final boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Builder other = (Builder) obj;
            if (academicYear == null) {
                if (other.academicYear != null) {
                    return false;
                }
            } else if (!academicYear.equals(other.academicYear)) {
                return false;
            }
            if (course == null) {
                if (other.course != null) {
                    return false;
                }
            } else if (!course.equals(other.course)) {
                return false;
            }
            if (start == null) {
                if (other.start != null) {
                    return false;
                }
            } else if (!start.equals(other.start)) {
                return false;
            }
            if (type != other.type) {
                return false;
            }
            return true;
        }

    }

}
