package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.TrainingForm;
import com.hescha.teacher_workload_accounting.service.TrainingFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/trainingForms")
public class TrainingFormController {

    @Autowired
    private TrainingFormService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "trainingForms";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            TrainingForm entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new TrainingForm());
        }
        return "trainingForms-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/trainingForms";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(TrainingForm entity) {
        service.create(entity);
        return "redirect:/trainingForms";
    }
}

