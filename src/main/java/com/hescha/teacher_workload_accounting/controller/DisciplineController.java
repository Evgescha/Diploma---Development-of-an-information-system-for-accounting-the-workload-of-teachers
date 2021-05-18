package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.Discipline;
import com.hescha.teacher_workload_accounting.entity.Group;
import com.hescha.teacher_workload_accounting.entity.Speciality;
import com.hescha.teacher_workload_accounting.entity.TableRow;
import com.hescha.teacher_workload_accounting.service.DisciplineService;
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
@RequestMapping("/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "disciplines";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            Discipline entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Discipline());
        }
        return "disciplines-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/disciplines";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Discipline entity) {
        service.create(entity);
        return "redirect:/disciplines";
    }

    @RequestMapping(path = "/showWorkload/{id}")
    public String showWorkload(Model model, @PathVariable("id") Long id) {
        Discipline entity = service.read(id);
        model.addAttribute("list", entity.getTableRows());
        model.addAttribute("message","По выбранной дисиплине: "+entity);
        return "tableRows";
    }
}

