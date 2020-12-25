package com.GhereDaniel.CodingEvents.controllers.event;

import com.GhereDaniel.CodingEvents.data.event.EventCategoryRepository;
import com.GhereDaniel.CodingEvents.data.event.EventRepository;
import com.GhereDaniel.CodingEvents.data.event.EventTagRepository;
import com.GhereDaniel.CodingEvents.data.user.UsersRepository;
import com.GhereDaniel.CodingEvents.models.dto.EventTagDTO;
import com.GhereDaniel.CodingEvents.models.event.Event;
import com.GhereDaniel.CodingEvents.models.event.EventCategory;
import com.GhereDaniel.CodingEvents.models.event.EventTag;
import com.GhereDaniel.CodingEvents.models.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private EventTagRepository eventTagRepository;

    @Autowired
    private UsersRepository usersRepository;



    //lives at /events/view
    @GetMapping("view")
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId, Model model){

        if(categoryId == null) {
            model.addAttribute("title", "Events List");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()){
                model.addAttribute("title", "Invalid Category ID: " + categoryId);

            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: "+ category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/viewEventListPage";
    }

    //lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/createEventPage";
    }

    //lives at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/createEventPage";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName="";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        Iterable<Users> users = usersRepository.findAll();
        for (Users users1: users) {
            if(users1.getUsername().equals(currentUserName)){
                newEvent.setUsers(users1);

                eventRepository.save(newEvent);
                return "redirect:/";
            }
        }
        eventRepository.save(newEvent);
        return "redirect:/";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName="";
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        Iterable<Users> users = usersRepository.findAll();
        Users user= new Users();
        for (Users users1: users) {
            if(users1.getUsername().equals(currentUserName)){
                user=users1;
            }
        }
        List<Event> myEvents = new ArrayList<>();
        Iterable<Event> events = eventRepository.findAll();
        if(user.getUserType().getAccessClarence()!=1) {


            for (Event event : events) {
                if (event.getUsers().getId() == user.getId()) {
                    myEvents.add(event);
                }
            }
        }
        else{
            myEvents = (List<Event>) events;
        }


        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", myEvents);
        return "events/deleteEventPage";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds !=null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/";
    }

    //lives at /events/view/details
    @GetMapping("view/details")
    public String displayEventDetails(@RequestParam Integer eventId, Model model){

        Optional<Event> result = eventRepository.findById(eventId);

        Event event = result.get();
        model.addAttribute("title", "Event: "+ event.getName());
        model.addAttribute("event", event);
        model.addAttribute("tags", event.getTags());

        return "events/viewEventDetails";
    }

    // responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", eventTagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag,Errors errors, Model model){
        if(!errors.hasErrors()){
            Event event = eventTag.getEvent();
            EventTag tag = eventTag.getTag();
            for (EventTag iTag : event.getTags()) {
                if(iTag.getId()==tag.getId()){
                    return "redirect:/";
                }
            }
            event.addTag(tag);
            eventRepository.save(event);
            return "redirect:/";
        }
        return "events/add-tag.html";
    }
}
