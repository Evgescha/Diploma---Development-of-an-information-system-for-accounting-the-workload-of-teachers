package com.hescha.teacher_workload_accounting.controller;

import com.hescha.teacher_workload_accounting.entity.Group;
import com.hescha.teacher_workload_accounting.entity.TableRowDate;
import com.hescha.teacher_workload_accounting.service.TableRowDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/tableRowDates")
public class TableRowDateController {

    @Autowired
    private TableRowDateService service;

    @GetMapping
    public String getList(Model model) {
        model.addAttribute("list", service.repository.findAll());
        return "tableRowDates";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable(name = "id", required =
            false) Long id) {

        if (id != null) {
            TableRowDate entity = service.read(id);
            model.addAttribute("entity", entity);
        } else {
            model.addAttribute("entity", new TableRowDate());
        }
        return "tableRowDates-add-edit";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/tableRowDates";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(TableRowDate entity) {
        service.create(entity);
        return "redirect:/tableRowDates";
    }

    @RequestMapping(path = "/showWorkload/{id}")
    public String showWorkload(Model model, @PathVariable("id") Long id) {
        TableRowDate entity = service.read(id);
        model.addAttribute("list", entity.getTableRow());
        model.addAttribute("message","По выбранному периоду: "+entity);
        return "tableRows";
    }
}

