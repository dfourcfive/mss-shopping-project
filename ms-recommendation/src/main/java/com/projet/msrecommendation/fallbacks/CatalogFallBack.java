package com.projet.msrecommendation.fallbacks;

import com.projet.msrecommendation.feignClient.ProductClient;
import com.projet.msrecommendation.model.Product;
import org.springframework.stereotype.Component;


@Component
public class CatalogFallBack implements ProductClient {

    @Override
    public Product getProductById(Long productId) {
        return new Product();
    }
}
