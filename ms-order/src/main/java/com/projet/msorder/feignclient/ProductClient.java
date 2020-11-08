package com.projet.msorder.feignclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.projet.msorder.fallbacks.CatalogFallBack;
import com.projet.msorder.model.Product;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-catalog",fallback = CatalogFallBack.class)
@RibbonClient(name = "ms-catalog")

public interface ProductClient {
    @HystrixCommand
    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable(value = "id") Long productId);

}
