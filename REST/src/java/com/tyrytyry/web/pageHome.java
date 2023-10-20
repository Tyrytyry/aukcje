package com.tyrytyry.web;

import com.tyrytyry.item.ItemService;
import com.tyrytyry.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

import static com.tyrytyry.item.ItemService.ProductsByCategory;


@Controller
@RequestMapping("/")
public class pageHome {

    @Autowired
    private ItemService itemService;



    @GetMapping("/")
    public String orderForm(Model model) {
        List<Item> allItems = itemService.getAllItems();
        List<Item> items = ProductsByCategory(allItems);


        if (items.size() > 3) {
            items = items.subList(0, 4); // Pobierz tylko pierwsze 3 elementy
        }

        model.addAttribute("products", items);
        return "home";

    }

//    @GetMapping("/home")
//    public String home(Model model) {
//        List<Item> allItems = itemService.getAllItems();
//        List<Item> item = ProductsByCategory(allItems);
//
//
//        model.addAttribute("products", item);
//        return "home.html";
//    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Item> allItems = itemService.getAllItems();
        List<Item> items = ProductsByCategory(allItems);


        if (items.size() > 3) {
            items = items.subList(0, 4); // Pobierz tylko pierwsze 4 elementy
        }

        model.addAttribute("products", items);
        return "home.html";
    }
}


