package com.projet.msorder.fallbacks;


import com.projet.msorder.feignclient.ProductClient;
import com.projet.msorder.model.Product;

public class CatalogFallBack implements ProductClient {

    @Override
    public Product getProductById(Long productId) {
        return new Product();
    }
}
