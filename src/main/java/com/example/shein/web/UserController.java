package com.example.shein.web;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {


    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/error-page")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                              RedirectAttributes attributes){

        attributes.addFlashAttribute("bad_credentials",true);
        attributes.addFlashAttribute("username",username);

        return "redirect:/users/login";
    }




}
