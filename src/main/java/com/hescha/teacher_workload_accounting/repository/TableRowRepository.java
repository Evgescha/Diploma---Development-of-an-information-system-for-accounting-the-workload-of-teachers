package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRowRepository extends JpaRepository<TableRow, Long> {
    TableRow findByTableRowDateAndTeacherAndDisciplineAndGroup(TableRowDate tableRowDate, Teacher teacher, Discipline discipline, Group group);
}