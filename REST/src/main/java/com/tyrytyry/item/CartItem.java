package com.tyrytyry.item;

public class CartItem {
    private Long id;
    private int Quantity;

    public CartItem(Long id, int quantity) {
        this.id = id;
        this.Quantity = quantity;
    }
[l;l]
    public Long getId() {
        return id;
    }



    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
        System.out.println("Quantity na stronce: " + Quantity);
    }
}
