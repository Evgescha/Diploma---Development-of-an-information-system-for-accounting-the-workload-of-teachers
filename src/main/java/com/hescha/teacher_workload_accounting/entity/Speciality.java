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
public class Speciality extends AbstractEntity {

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality", cascade = CascadeType.REMOVE)
    private List<Group> groups = new ArrayList<>();

    public Speciality(String name) {
        this.name = name;
    }

    public Speciality() {
    }
    @Override
    public String toString() {
        return name;
    }
}
