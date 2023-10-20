package com.tyrytyry.web;

import com.tyrytyry.item.CartItem;
import com.tyrytyry.item.ItemService;
import com.tyrytyry.item.*;
import com.tyrytyry.model.*;
import com.tyrytyry.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.tyrytyry.item.ItemService.ProductsByCategory;


@Controller
public class pageBasket {



    @Autowired
    private ItemService itemService;
    private List<Item> filteredProducts;

    @Autowired
    private SessionService sessionService;
    @GetMapping("/basket")
    public String pageBasket(Model model) {
        List<CartItem> cartItems = sessionService.getCart(); // lista wspólna
        List<Item> allItems = itemService.getAllItems();// lista wszystkiego
        List<Item> buyerItems = new ArrayList<>();
        List<Item> sellerItems = new ArrayList<>();
        List<Item> expiredItems = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        LocalDateTime currentTime = LocalDateTime.now();
        List<Item> items = ProductsByCategory(allItems);
        filteredProducts = ItemService.createProductListFromCartItems(items, cartItems);
        displayBasket(cartItems);



        for (Item item : allItems) {
            if (containsItem(filteredProducts, item.getId()) && item.getTime().isAfter(currentTime)) {
                buyerItems.add(item);
            } else if (item.getBuyer().equals(username) && item.getTime().isBefore(currentTime)) {
                expiredItems.add(item);
            } else if (item.getOwner().equals(username)) {
                sellerItems.add(item);
            }
        }

        System.out.println("Basket !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + cartItems );
        displayBasket(cartItems);
        model.addAttribute("buyerItems", buyerItems);
        model.addAttribute("sellerItems", sellerItems);
        model.addAttribute("expiredItems", expiredItems);

        return "basket";
    }

    public boolean containsItem(List<Item> filteredProducts, Long itemId) {
        for (Item item : filteredProducts) {
            if (item.getId().equals(itemId)) {
                return true;
            }
        }
        return false;
    }



    public void printItemIds(List<Long> itemList) {
        System.out.println("Item IDs:");
        for (Long itemId : itemList) {
            System.out.println(itemId);
        }
    }

    private boolean cartItemsChanged(List<CartItem> cartItems) {

        return cartItems.size() != filteredProducts.size();
    }
    @RequestMapping(value = "/removeProduct", method = RequestMethod.POST)
    @ResponseBody
    public String removeProduct(@RequestParam("productId") String productId) {
        // Wykonaj odpowiednie operacje w celu usunięcia produktu o podanym ID
        // Na przykład:
        System.out.println("Usunięto produkt o ID: " + productId);
      //  sessionService.removeFromCart(productId);
        return "Produkt został usunięty";
    }



    private void displayBasket(List<CartItem> cart) {
        System.out.println("Basket Contents:");
        for (CartItem item : cart) {
            System.out.println("ID: " + item.getId());
            System.out.println("Quantity: " + item.getQuantity());
        }
    }
}