package com.GhereDaniel.CodingEvents.controllers.event;

import com.GhereDaniel.CodingEvents.data.event.EventTagRepository;
import com.GhereDaniel.CodingEvents.models.event.Event;
import com.GhereDaniel.CodingEvents.models.event.EventTag;
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
@RequestMapping("eventTag")
public class EventTagController {

    @Autowired
    private EventTagRepository eventTagRepository;

    @GetMapping("view")
    public String displayAllTags(Model model){
        model.addAttribute("title","Event Tag List");
        model.addAttribute("eventTags", eventTagRepository.findAll());
        return "tag/viewEventTagListPage";
    }

    @GetMapping("create")
    public String displayCreateTagForm(Model model){
        model.addAttribute("title","Create Event Tag");
        model.addAttribute(new Event());
        model.addAttribute("error", "");
        return "tag/createEventTagPage";
    }

    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid EventTag newEventTag, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event Tag");
            model.addAttribute(new Event());
            model.addAttribute("error", "Name must be between 3 and 50 characters");
            return "tag/createEventTagPage";
        }
        eventTagRepository.save(newEventTag);
        return "redirect:/";
    }


}
