package com.projet.msorder.feignclient;

import com.projet.msorder.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user", url = "http://localhost:8080/")
public interface UserClient {
    @GetMapping(value = "/users/{id}")
    public User getOneUser(@PathVariable("id") Long id);

}
