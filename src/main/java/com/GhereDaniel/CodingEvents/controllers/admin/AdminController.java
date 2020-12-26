package com.GhereDaniel.CodingEvents.controllers.admin;

import com.GhereDaniel.CodingEvents.data.event.EventRepository;
import com.GhereDaniel.CodingEvents.data.user.UserTypeRepository;
import com.GhereDaniel.CodingEvents.data.user.UsersRepository;
import com.GhereDaniel.CodingEvents.models.event.Event;
import com.GhereDaniel.CodingEvents.models.user.UserType;
import com.GhereDaniel.CodingEvents.models.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String displayMainAdminPage(Model model){
        model.addAttribute("title","Admin Page");

        return "admin/adminMainPage";
    }

    @GetMapping("view")
    public String displayAllUsers(Model model){

            model.addAttribute("title", "Users List");
            model.addAttribute("users", usersRepository.findAll());

        return "admin/adminViewUsers";
    }

    private Integer toBeModifyUser=0;

    @GetMapping("view/details")
    public String displayUsersDetails(@RequestParam Integer userId, Model model){

        Optional<Users> result = usersRepository.findById(userId);
        toBeModifyUser=userId;
        Users user = result.get();
        model.addAttribute("title", "User: "+ user.getName());
        model.addAttribute("user", user);

        return "admin/adminViewUserDetails";
    }

    @GetMapping("delete")
    public String displayDeleteUsersForm(Model model){

        model.addAttribute("title", "Delete Users");
        model.addAttribute("users", usersRepository.findAll());
        return "admin/adminDeleteUsers";
    }

    @PostMapping("delete")
    public String processDeleteUsersForm(@RequestParam(required = false) int[] usersIds){
        if(usersIds !=null) {
            for (int id : usersIds) {
                Iterable<Event> events = eventRepository.findAll();
                List<Integer> eventsIds = new ArrayList<>();
                for (Event event: events) {
                    if(event.getUsers().getId()==id)
                        eventsIds.add(event.getId());
                }
                for(Integer idE: eventsIds){
                    eventRepository.deleteById(idE);
                }
                usersRepository.deleteById(id);
            }
        }
        return "redirect:/";
    }

    @GetMapping("updateUser")
    public String displayUpdateUserForm(Model model){
        model.addAttribute("title","Register a new account");
        model.addAttribute("user", new Users());
        model.addAttribute("error", "");
        return "admin/adminUpdateUser";
    }

    @PostMapping("updateUser")
    public String processUpdateUserForm(@ModelAttribute @Valid Users user, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Register a new account");
            model.addAttribute("user", new Users());
            model.addAttribute("error", "Name must be between 3 and 50 characters");
            return "security/register";
        }

        Optional<UserType> userType = userTypeRepository.findById(2);
        user.setUserType(userType.get());
        String encodedPassword  = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);

        if(!user.getName().equals(""))
            usersRepository.updateName(toBeModifyUser,user.getName());
        if(!user.getUsername().equals(""))
            usersRepository.updateUsername(toBeModifyUser,user.getUsername());
        if(!user.getPassword().equals(""))
            usersRepository.updatePassword(toBeModifyUser,user.getPassword());
        if(user.getUserType().getId()==2)
            usersRepository.updateUserType(toBeModifyUser,user.getUserType());
        if(!user.getEmail().equals(""))
            usersRepository.updateEmail(toBeModifyUser,user.getEmail());
        return "redirect:/";
    }


}
