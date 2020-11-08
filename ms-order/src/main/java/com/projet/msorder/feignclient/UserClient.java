package com.projet.msorder.feignclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.projet.msorder.fallbacks.CatalogFallBack;
import com.projet.msorder.model.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user",fallback = UserClient.class)
@RibbonClient(name = "ms-user")

public interface UserClient {
    @HystrixCommand
    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable("id") Long id);

}
