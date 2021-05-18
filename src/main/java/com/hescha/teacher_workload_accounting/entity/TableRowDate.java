package com.hescha.teacher_workload_accounting.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TableRowDate extends AbstractEntity {
    private String year;
    private String semester;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tableRowDate", cascade = CascadeType.REMOVE)
    private List<TableRow> tableRow = new ArrayList<>();

    public TableRowDate(String year, String semester) {
        this.year = year;
        this.semester = semester;
    }

    public TableRowDate() {
    }
    @Override
    public String toString() {
        return year+" - "+semester;
    }
}
