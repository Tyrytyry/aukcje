package com.tyrytyry.item;

import com.tyrytyry.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    public static enum Type {
        Ubrania, Meble, Akcesoria, Biżuteria, Inne
    }

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return (List<Item>) itemRepository.findAll();
    }


    public void deleteItem(Item itemToDelete) {
        itemRepository.delete(itemToDelete);
    }

    public Item getItemById(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        return itemOptional.orElse(null);
    }
    public void updateItemDetails(Long itemId, String buyer, double price) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();


            item.setBuyer(buyer);
            item.setPrice(price);


            itemRepository.save(item);
        } else {

            throw new IllegalArgumentException("Produkt o podanym ID nie istnieje");
        }
    }
    public void updateItemIfConditionsMet(Long itemId, String buyer, double price) {

        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();


            if (price > item.getPrice() && !item.getOwner().equals(buyer) && !item.getBuyer().equals(buyer) && item.getTime().isAfter(LocalDateTime.now())) {
                // Nadpisz wartości pól
                item.setBuyer(buyer);
                item.setPrice(price);

                // Zapisz zmienione dane w bazie danych
                itemRepository.save(item);
            } else {
                // Obsłuż błąd - warunki nie zostały spełnione
                throw new IllegalArgumentException("Nie spełniono warunków do aktualizacji danych produktu");
            }
        } else {
            // Obsłuż błąd - produkt o podanym itemId nie został znaleziony
            throw new IllegalArgumentException("Produkt o podanym ID nie istnieje");
        }
    }


    public static List<Item> filterProductsByCategory(List<Item> productList, String category) {
        List<Item> filteredList = new ArrayList<>();
        System.out.println(category);
        System.out.println("filterProductsByCategory");
        for (Item item : productList) {
            String productCategory = item.getCategory();
            if (productCategory != null && productCategory.equals(category)) {
                filteredList.add(item);
            }
        }

        for (Item product : filteredList) {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Image Path: " + product.getImageUrl());
            System.out.println("Category: " + product.getCategory());
            System.out.println("-----------------------");
        }

        return filteredList;
    }
    public static List<Item> ProductsByCategory(List<Item> productList) {
        List<Item> filteredList = new ArrayList<>();
        for (Item item : productList) {

                filteredList.add(item);
            }
        return filteredList;
    }

    public void updateItem(Item item) {
        // Tutaj umieść logikę aktualizacji rekordu w bazie danych
        // Na podstawie dostępnej implementacji dostępu do bazy danych

        // Przykładowa implementacja dla bazy danych SQL z wykorzystaniem JPA/Hibernate:
        itemRepository.save(item);
    }

    public static List<Item> createProductListFromCartItems(List<Item> productList, List<CartItem> cartItems) {
        List<Item> filteredList = new ArrayList<>();
        System.out.println("createProductListFromCartItems");
        for (CartItem cartItem : cartItems) {
            Long productId = cartItem.getId();
            for (Item product : productList) {
                if (product.getId().equals(productId)) {
                    filteredList.add(product);
                    break;
                }
            }
        }
        return filteredList;
    }
}