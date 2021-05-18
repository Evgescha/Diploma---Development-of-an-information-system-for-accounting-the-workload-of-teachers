package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.Department;
import com.hescha.teacher_workload_accounting.entity.Group;
import com.hescha.teacher_workload_accounting.entity.TableRow;
import com.hescha.teacher_workload_accounting.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "departments";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            Department entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Department());
        }
        return "departments-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/departments";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Department entity) {
        service.create(entity);
        return "redirect:/departments";
    }

    @RequestMapping(path = "/showWorkload/{id}")
    public String showWorkload(Model model, @PathVariable("id") Long id) {
        Department entity = service.read(id);
        List<TableRow> list = new ArrayList<>();
        for(Group g:entity.getGroups()){
            list.addAll(g.getTableRows());
        }
        model.addAttribute("list", list);
        model.addAttribute("message","По выбранной кафедре: "+entity.getName());
        return "tableRows";
    }
}

