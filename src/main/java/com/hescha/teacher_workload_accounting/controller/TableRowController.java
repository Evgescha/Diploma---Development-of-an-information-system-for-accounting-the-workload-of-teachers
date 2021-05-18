package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.TableRow;
import com.hescha.teacher_workload_accounting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/tableRows")
public class TableRowController {

    @Autowired
    private TableRowService service;
    @Autowired
    private TableRowDateService serviceTableRowDate;
    @Autowired
    private TeacherService serviceTeacher;
    @Autowired
    private DisciplineService serviceDiscipline;
    @Autowired
    private GroupService serviceGroup;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "tableRows";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            TableRow entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new TableRow());
        }
        model.addAttribute("dates", serviceTableRowDate.repository.findAll());
        model.addAttribute("teachers", serviceTeacher.repository.findAll());
        model.addAttribute("disciplines", serviceDiscipline.repository.findAll());
        model.addAttribute("groups", serviceGroup.repository.findAll());
        return "tableRows-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/tableRows";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(TableRow entity,
                                 @Param("table_row_date_id") Long table_row_date_id,
                                 @Param("teacher_id") Long teacher_id,
                                 @Param("discipline_id") Long discipline_id,
                                 @Param("group_id") Long group_id) {
        entity.setTableRowDate(serviceTableRowDate.read(table_row_date_id));
        entity.setTeacher(serviceTeacher.read(teacher_id));
        entity.setDiscipline(serviceDiscipline.read(discipline_id));
        entity.setGroup(serviceGroup.read(group_id));
        service.create(entity);
        return "redirect:/tableRows";
    }
}

