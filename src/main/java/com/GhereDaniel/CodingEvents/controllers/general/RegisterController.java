package com.GhereDaniel.CodingEvents.controllers.general;

import com.GhereDaniel.CodingEvents.data.user.UserTypeRepository;
import com.GhereDaniel.CodingEvents.data.user.UsersRepository;
import com.GhereDaniel.CodingEvents.models.user.UserType;
import com.GhereDaniel.CodingEvents.models.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @GetMapping("")
    public String displayCreateEventCategoryForm(Model model){
        model.addAttribute("title","Register a new account");
        model.addAttribute("user", new Users());
        model.addAttribute("error", "");
        return "security/register";
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid Users user, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title","Register a new account");
            model.addAttribute("user", new Users());
            model.addAttribute("error", "Name must be between 3 and 50 characters");
            return "security/register";
        }
        Iterable<Users> users = usersRepository.findAll();
        for (Users users1: users) {
            if(users1.getName().equals(user.getName())){
                model.addAttribute("title","Register a new account");
                model.addAttribute("user", new Users());
                model.addAttribute("error", "Name must be between 3 and 50 characters");
                return "security/register";
            }
        }

        Optional<UserType> userType = userTypeRepository.findById(2);
        user.setUserType(userType.get());
        String encodedPassword  = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        usersRepository.save(user);
        return "redirect:/";
    }


}
