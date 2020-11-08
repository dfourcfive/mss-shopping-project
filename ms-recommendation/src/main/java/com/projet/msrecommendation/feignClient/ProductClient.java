package com.projet.msrecommendation.feignClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.projet.msrecommendation.fallbacks.CatalogFallBack;
import com.projet.msrecommendation.model.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalog",fallback = CatalogFallBack.class)
@RibbonClient(name = "ms-catalog")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId);
}
