package com.tyrytyry.web;


import jakarta.validation.Valid;
import com.tyrytyry.model.UserDto;

import com.tyrytyry.model.User;
import com.tyrytyry.security.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class pageLogin {

    private UserService userService;

    public pageLogin(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "login";
    }


    @PostMapping("/login/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/login";
        }

        userService.saveUser(userDto);
        return "redirect:/login?success";
    }



    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


//    @GetMapping("/login")
//    public String login() {
//        UserDto user = new UserDto();
//        model.addAttribute("user", user);
//        return "login";
//    }

}