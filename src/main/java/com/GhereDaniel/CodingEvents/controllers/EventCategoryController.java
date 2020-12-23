package com.GhereDaniel.CodingEvents.controllers;

import com.GhereDaniel.CodingEvents.Data.EventCategoryRepository;
import com.GhereDaniel.CodingEvents.models.Event;
import com.GhereDaniel.CodingEvents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    //lives at /eventCategories/view
    @GetMapping("view")
    public String displayAllEventCategories(Model model){
        model.addAttribute("title","Event Categories List");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "category/viewEventCategoryListPage";
    }

    //lives at /eventCategories/create
    @GetMapping("create")
    public String displayCreateEventCategoryForm(Model model){
        model.addAttribute("title","Create Event Categories");
        model.addAttribute(new Event());
        model.addAttribute("error", "");
        return "category/createEventCategoryPage";
    }

    //lives at /eventCategories/create
    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event Categories");
            model.addAttribute(new Event());
            model.addAttribute("error", "Name must be between 3 and 50 characters");
            return "category/createEventCategoryPage";
        }
        eventCategoryRepository.save(newEventCategory);
        return "redirect:/";
    }


}
