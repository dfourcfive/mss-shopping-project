package com.projet.msrecommendation.feignClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.projet.msrecommendation.fallbacks.CatalogFallBack;
import com.projet.msrecommendation.fallbacks.UserFallBack;
import com.projet.msrecommendation.model.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user",fallback = UserFallBack.class)
@RibbonClient(name = "ms-user")
public interface UserClient {

    @GetMapping (value = "/users/{id}")
    public User getUserById(@PathVariable ("id") Long id);

}
