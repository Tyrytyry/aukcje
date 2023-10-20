package com.tyrytyry.model;

import java.util.ArrayList;
import java.util.List;

public class UserSession {
    private String sessionId;
    private List<Long> itemList;

    public UserSession(String sessionId) {
        this.sessionId = sessionId;
        this.itemList = new ArrayList<>();
    }

    public String getSessionId() {
        return sessionId;
    }

    public List<Long> getItemList() {
        return itemList;
    }

    public void addItem(Long itemId) {
        itemList.add(itemId);
    }

    public void removeItem(Long itemId) {
        itemList.remove(itemId);
    }
}