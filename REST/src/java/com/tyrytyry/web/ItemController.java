package com.tyrytyry.web;
import com.tyrytyry.item.ItemService;
import com.tyrytyry.item.SessionService;
import com.tyrytyry.model.Item;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

@Autowired
    private SessionService sessionService;




    private static final String UPLOAD_DIR = "/home/tyrytyry/obszarRoboczy/gity/REST/src/main/resources/static/produkty"; // Określ ścieżkę do folderu docelowego
    @PostMapping("/delete-item")
    public String deleteItem(@RequestParam("itemId") Long itemId) {
        Item itemToDelete = itemService.getItemById(itemId);
        if (itemToDelete != null) {
            itemService.deleteItem(itemToDelete);
        }

        return "item-list";
    }

//    @PostMapping("/add-items")
//    public String addItem(@RequestBody ItemForm itemForm) {
//        // Tworzenie nowego obiektu Item na podstawie danych z formularza
//        Item item = new Item();
//        item.setName(itemForm.getName());
//        item.setPrice(itemForm.getPrice());
//        item.setImageUrl(itemForm.getImageUrl());
//        item.setCategory(itemForm.getCategory());
//   //     item.setOwner(itemForm.getOwner());
//        item.setBuyer(itemForm.getBuyer());
//        item.setTime(LocalDateTime.now());
//
//        System.out.println("zapisuję produkt w bazie");
//        // Zapisywanie produktu do bazy danych
//        Item createdItem = itemService.createItem(item);
//
//        // Przekierowanie na inną stronę po dodaniu produktu (np. szczegóły produktu)
//        return "item-list";
//    }

    @GetMapping("/add-item")
    public String addItem(Model model) {

        Item item = new Item();

        model.addAttribute("item", item);
        return "add-item";
    }


    @GetMapping("/showItems")
    public String getAllItems(Model model) {

        List<Item> items = itemService.getAllItems();



        model.addAttribute("items", items);
        return "item-list";
    }




//    @PostMapping("/updateItem")
//    public String updateItem(@RequestParam("id") Long id,
//                             @RequestParam("sum") double sum,
//                             Model model) {
//        Item item = itemService.getItemById(id);
//        String owner = item.getOwner();
//        String buyer = item.getBuyer();
//        double currentPrice = item.getPrice();
//        LocalDateTime itemTime = item.getTime();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LocalDateTime currentTime = LocalDateTime.now();
//        String username = authentication.getName();
//
//
//        if (sum > currentPrice && !username.equals(owner) && !username.equals(buyer) && currentTime.isBefore(itemTime)) {
//            item.setPrice(sum);
//            item.setBuyer(username);
//            itemService.updateItem(item);
//
//            model.addAttribute("message", "Dane zostały zaktualizowane.");
//        } else {
//            model.addAttribute("error", "Nie można zaktualizować danych.");
//        }
//
//        return "basket.html";
//    }

@PostMapping("/add-item")
public String createItem(@ModelAttribute("item") Item item,
                         @RequestParam("image") MultipartFile file,
                         @RequestParam("days") int days,
                         Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    item.setOwner(username);
    item.setBuyer("Kupujący");

    if (!file.isEmpty()) {
        try {

            String fileName = UUID.randomUUID().toString() + "-" + StringUtils.cleanPath(file.getOriginalFilename());


            String imageUrl = "produkty/" + fileName;
            Path targetPath = Path.of(UPLOAD_DIR, fileName);


            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(imageUrl);
            item.setImageUrl(imageUrl);
        } catch (IOException e) {
            return "Wystąpił błąd podczas przesyłania i zapisywania zdjęcia: " + e.getMessage();
        }
    }

    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expirationTime = currentTime.plusDays(days);
    item.setTime(expirationTime);

    System.out.println("Zapisuję produkt w bazie");

    Item createdItem = itemService.createItem(item);

    model.addAttribute("createdItem", createdItem);
    return "redirect:/basket";
}


    @PostMapping("/updateItem")
    public RedirectView updateItem(@RequestParam("id") Long id,
                                   @RequestParam("sum") double sum,
                                   HttpSession session,
                                   Model model) {
        Item item = itemService.getItemById(id);
        String owner = item.getOwner();
        String buyer = item.getBuyer();
        double currentPrice = item.getPrice();
        LocalDateTime itemTime = item.getTime();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LocalDateTime currentTime = LocalDateTime.now();
        String username = authentication.getName();

        if (sum > currentPrice && !username.equals(owner) && !username.equals(buyer) && currentTime.isBefore(itemTime)) {
            item.setPrice(sum);
            item.setBuyer(username);
            itemService.updateItem(item);

           int quantity=1 ;
            sessionService.addToCart(id, quantity);


            model.addAttribute("message", "Dane zostały zaktualizowane.");
        } else {
            model.addAttribute("error", "Nie można zaktualizować danych.");
        }

        RedirectView redirectView = new RedirectView("/basket");
        return redirectView;
    }

}