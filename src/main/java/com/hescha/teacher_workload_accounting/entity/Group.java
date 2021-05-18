package com.hescha.teacher_workload_accounting.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "my_group")
public class Group extends AbstractEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @ManyToOne
    @JoinColumn(name = "training_form_id")
    private TrainingForm trainingForm;
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    private String course;
    private String studentCount;
    private String groupCount;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group", cascade = CascadeType.REMOVE)
    private List<TableRow> tableRows = new ArrayList<>();

    public Group() {
    }

    public Group(String name, TrainingForm trainingForm) {
        this.name = name;
        this.trainingForm = trainingForm;
    }

    public Group(String name, Department department, Faculty faculty,
                 TrainingForm trainingForm, Speciality speciality,
                 String course, String studentCount, String groupCount) {
        this.name = name;
        this.department = department;
        this.faculty = faculty;
        this.trainingForm = trainingForm;
        this.speciality = speciality;
        this.course = course;
        this.studentCount = studentCount;
        this.groupCount = groupCount;
    }

    @Override
    public String toString() {
        return
                name +
                ", " + department +
                ", " + faculty +
                ", " + trainingForm +
                ", " + speciality +
                ", курс: " + course +
                ", студентов: " + studentCount +
                ", групп: " + groupCount
                ;
    }
}
