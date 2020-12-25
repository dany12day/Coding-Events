package com.GhereDaniel.CodingEvents.controllers.event;

import com.GhereDaniel.CodingEvents.data.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public String displayAllEventCategories(Model model){
        model.addAttribute("title","Admin Page");

        return "admin/adminMainPage";
    }

    @GetMapping("view")
    public String displayAllEvents(Model model){

            model.addAttribute("title", "Events List");
            model.addAttribute("users", usersRepository.findAll());

        return "admin/adminViewUsers";
    }



}
