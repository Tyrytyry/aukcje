package com.tyrytyry.web;

import com.tyrytyry.item.ItemService;
import com.tyrytyry.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static com.tyrytyry.item.ItemService.ProductsByCategory;
import static com.tyrytyry.item.ItemService.filterProductsByCategory;

//import static com.tyrytyry.product.Product.createProductList;
//import static com.tyrytyry.product.Product.filterProductsByCategory;

@Controller
public class pagecategory {



    @Autowired
    private ItemService itemService;


    @GetMapping("/category")
    public String getProductByCategory(Model model, @RequestParam("category") String category) {
        List<Item> allItems = itemService.getAllItems();
        List<Item> filteredProducts = filterProductsByCategory(allItems, category);
        model.addAttribute("products", filteredProducts);
        return "category.html";
    }

    @GetMapping("/itemsy")
    public String getAllItems(Model model) {
        List<Item> allItems = itemService.getAllItems();
        List<Item> item = ProductsByCategory(allItems);


        model.addAttribute("products", item);
        return "category.html";
    }


    @PostMapping("/cart/add-product")
    public String addProductToCart(@RequestParam("id") String id, @RequestParam("quantity") int quantity) {

        return "redirect:/basket";
    }












//    @PostMapping("/cart/add-product")
//    public String addProductToCart(Product product) {
//        Product productd = new Product("2", "Doniczki kwiatowe", 5.49, "produkty/linh-le-Ebwp2-6BG8E-unsplash%201.png", "Rośliny");
//        sessionService.addToCart("product");
//        return "redirect:/basket";
//    }



//    @PostMapping("/cart/add")
//    public String addToCart(@RequestParam("product") String product) {
//        sessionService.addToCart(product);
//        return "redirect:/cart";
//    }
//
//    @GetMapping("/cart")
//    public String viewCart(Model model) {
//        List<String> cart = sessionService.getCart();
//        model.addAttribute("cart", cart);
//        return "cart";
//    }


}

