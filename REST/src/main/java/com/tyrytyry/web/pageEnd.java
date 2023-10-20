package com.tyrytyry.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class pageEnd {


    @GetMapping("/end")
    public String pageEnd() {

        return "End.html";
    }


}