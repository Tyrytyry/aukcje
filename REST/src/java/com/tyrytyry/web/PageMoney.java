package com.tyrytyry.web;

import com.tyrytyry.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class PageMoney {

    private Order order;


    @GetMapping("/money")
    public String pageMoney(Model model) {
        model.addAttribute("order", new Order());
        return "money";
    }

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }


    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "money";
    }


    @GetMapping("/success")
    public String success() {
        return "success";
    }
}