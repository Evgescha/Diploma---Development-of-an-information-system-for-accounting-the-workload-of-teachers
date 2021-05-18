package com.hescha.teacher_workload_accounting.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class TableRow extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "table_row_date_id")
    private TableRowDate tableRowDate;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String threadNumber;
    private String threadCount;
    private int lectureCount;
    private int practicalCount;
    private int laboratoryCount;
    private float consultationCount;
    private float exam;
    private float watching;
    private float differencialZachet;
    private float zachet;
    private float rgr;
    private float courseWork;
    private float courseProject;
    private float controlWork;
    private float individualWork;
    private float otherWork;

    public TableRow() {
    }

    public TableRow(TableRowDate tableRowDate, Teacher teacher,
                    Discipline discipline, Group group) {
        this.tableRowDate = tableRowDate;
        this.teacher = teacher;
        this.discipline = discipline;
        this.group = group;
    }

    public TableRow(TableRowDate tableRowDate, Teacher teacher,
                    Discipline discipline, String threadNumber, Group group,
                    String threadCount, int lectureCount, int practicalCount,
                    int laboratoryCount, float consultationCount, float exam,
                    float watching, float differencialZachet, float zachet,
                    float rgr, float courseWork, float courseProject,
                    float controlWork, float individualWork, float otherWork) {
        this.tableRowDate = tableRowDate;
        this.teacher = teacher;
        this.discipline = discipline;
        this.threadNumber = threadNumber;
        this.group = group;
        this.threadCount = threadCount;
        this.lectureCount = lectureCount;
        this.practicalCount = practicalCount;
        this.laboratoryCount = laboratoryCount;
        this.consultationCount = consultationCount;
        this.exam = exam;
        this.watching = watching;
        this.differencialZachet = differencialZachet;
        this.zachet = zachet;
        this.rgr = rgr;
        this.courseWork = courseWork;
        this.courseProject = courseProject;
        this.controlWork = controlWork;
        this.individualWork = individualWork;
        this.otherWork = otherWork;
    }


    public void update(String threadCount, int lectureCount, int practicalCount,
                    int laboratoryCount, float consultationCount, float exam,
                    float watching, float differencialZachet, float zachet,
                    float rgr, float courseWork, float courseProject,
                    float controlWork, float individualWork, float otherWork) {
        this.threadCount = threadCount;
        this.lectureCount = lectureCount;
        this.practicalCount = practicalCount;
        this.laboratoryCount = laboratoryCount;
        this.consultationCount = consultationCount;
        this.exam = exam;
        this.watching = watching;
        this.differencialZachet = differencialZachet;
        this.zachet = zachet;
        this.rgr = rgr;
        this.courseWork = courseWork;
        this.courseProject = courseProject;
        this.controlWork = controlWork;
        this.individualWork = individualWork;
        this.otherWork = otherWork;
    }
}
