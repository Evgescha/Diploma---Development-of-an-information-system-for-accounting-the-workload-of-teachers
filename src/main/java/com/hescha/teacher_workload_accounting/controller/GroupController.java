package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.Group;
import com.hescha.teacher_workload_accounting.entity.Teacher;
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
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    @Autowired
    private DepartmentService serviceDepartment;

    @Autowired
    private FacultyService serviceFaculty;

    @Autowired
    private TrainingFormService serviceTrainingForm;

    @Autowired
    private SpecialityService serviceSpeciality;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "groups";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            Group entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Group());
        }
        model.addAttribute("departaments", serviceDepartment.repository.findAll());
        model.addAttribute("faculties", serviceFaculty.repository.findAll());
        model.addAttribute("forms", serviceTrainingForm.repository.findAll());
        model.addAttribute("specialities", serviceSpeciality.repository.findAll());
        return "groups-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/groups";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Group entity,
                                 @Param("department_id") Long department_id,
                                 @Param("faculty_id") Long faculty_id,
                                 @Param("training_form_id") Long training_form_id,
                                 @Param("speciality_id") Long speciality_id) {
        entity.setDepartment(serviceDepartment.read(department_id));
        entity.setFaculty(serviceFaculty.read(faculty_id));
        entity.setTrainingForm(serviceTrainingForm.read(training_form_id));
        entity.setSpeciality(serviceSpeciality.read(speciality_id));
        service.create(entity);
        return "redirect:/groups";
    }

    @RequestMapping(path = "/showWorkload/{id}")
    public String showWorkload(Model model, @PathVariable("id") Long id) {
        Group entity = service.read(id);
        model.addAttribute("list", entity.getTableRows());
        model.addAttribute("message","По выбранной группе: "+entity);
        return "tableRows";
    }
}

