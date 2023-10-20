package com.tyrytyry.item;

import com.tyrytyry.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {


    Item findByName(String name);

    @Query("SELECT i FROM Item i WHERE i.category = ?1")
    List<Item> findByCategory(String category);
}