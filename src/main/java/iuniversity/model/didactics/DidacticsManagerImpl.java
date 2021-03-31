package iuniversity.model.didactics;

import java.util.Collections;
import java.util.Set;

public class DidacticsManagerImpl implements DidacticsManager {

    private Set<DegreeProgramme> degreeProgrammes;
    private Set<Course> courses;
    private AcademicYear academicYear;
    
    public DidacticsManagerImpl(Set<DegreeProgramme> degreeProgrammes, Set<Course> courses,
            AcademicYear academicYear) {
        this.degreeProgrammes = degreeProgrammes;
        this.courses = courses;
        this.academicYear = academicYear;
    }

    @Override
    public Set<DegreeProgramme> getDegreeProgrammes() {
        return Collections.unmodifiableSet(degreeProgrammes);
    }

    @Override
    public Set<Course> getCourse() {
        return Collections.unmodifiableSet(courses);
    }

    @Override
    public AcademicYear currentAcademicYear() {
        return this.academicYear;
    }

}
