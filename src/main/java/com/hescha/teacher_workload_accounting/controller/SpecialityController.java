package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.*;
import com.hescha.teacher_workload_accounting.service.SpecialityService;
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
@RequestMapping("/specialitys")
public class SpecialityController {

    @Autowired
    private SpecialityService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "specialitys";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            Speciality entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new Speciality());
        }
        return "specialitys-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/specialitys";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Speciality entity) {
        service.create(entity);
        return "redirect:/specialitys";
    }

    @RequestMapping(path = "/showWorkload/{id}")
    public String showWorkload(Model model, @PathVariable("id") Long id) {
        Speciality entity = service.read(id);
        List<TableRow> list = new ArrayList<>();
        for(Group g:entity.getGroups()){
            list.addAll(g.getTableRows());
        }
        model.addAttribute("list", list);
        model.addAttribute("message","По выбранной специальности: "+entity);
        return "tableRows";
    }
}

