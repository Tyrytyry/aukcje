package com.tyrytyry.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pagePay {

        @GetMapping("/pay")
        public String pay() {


            return "pay.html";
        }


}
