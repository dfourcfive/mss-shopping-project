package com.projet.msorder.service;

import com.projet.msorder.model.Item;

import java.util.List;
public interface CartService {

    public void addItemToCart(String cartId, Long productId, Integer quantity);
    public List<Object> getCart(String cartId);
    public void changeItemQuantity(String cartId, Long productId, Integer quantity);
    public void deleteItemFromCart(String cartId, Long productId);
    public boolean checkIfItemIsExist(String cartId, Long productId);
    public List<Item> getAllItemsFromCart(String cartId);
    public void deleteCart(String cartId);
}
