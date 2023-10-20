package com.tyrytyry.item;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SessionService {


    public void addToCart(Long id, int quantity) {
        List<CartItem> cart = getCart();
        for (int i = 0; i < quantity; i++) {
            CartItem item = new CartItem(id, quantity);
            cart.add(item);
            System.out.println("Jestem w removeFromCart/if");
        }
        updateCart(cart);
    }

    public List<CartItem> getCart() {
        HttpSession session = getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private void updateCart(List<CartItem> cart) {
        HttpSession session = getSession();
        session.setAttribute("cart", cart);
    }

    private HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        return request.getSession();
    }

    public void removeFromCart(String id) {
        List<CartItem> cart = getCart();
        Iterator<CartItem> iterator = cart.iterator();
        System.out.println("Jestem w removeFromCart");
        while (iterator.hasNext()) {
            System.out.println("Jestem w removeFromCart/while");
            CartItem item = iterator.next();
            if (item.getId().equals(id)) {
                System.out.println("Jestem w removeFromCart/if");
                iterator.remove();
            }
        }
        updateCart(cart);
    }

    public void addToCartItem(Long id, int quantity) {
        List<CartItem> cart = getCart();
        for (CartItem item : cart) {
            if (item.getId().equals(id)) {
                item.setQuantity(item.getQuantity() + quantity);
                updateCart(cart);
                return;
            }
        }


        CartItem newItem = new CartItem(id, quantity);
        cart.add(newItem);
        updateCart(cart);
    }

}


