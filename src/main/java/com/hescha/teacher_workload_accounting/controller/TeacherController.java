package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.Teacher;
import com.hescha.teacher_workload_accounting.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "teachers";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            Teacher entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Teacher());
        }
        return "teachers-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/teachers";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Teacher entity) {
        service.create(entity);
        return "redirect:/teachers";
    }

    @RequestMapping(path = "/showWorkload/{id}")
    public String showWorkload(Model model, @PathVariable("id") Long id) {
        Teacher entity = service.read(id);
        model.addAttribute("list", entity.getTableRows());
        model.addAttribute("message","По выбранному преподователю: "+entity);
        return "tableRows";
    }
}

