package com.projet.msorder.fallbacks;


import com.projet.msorder.feignclient.UserClient;
import com.projet.msorder.model.User;

public class UserFallBack implements UserClient {
    @Override
    public User getUserById(Long id) {
        return new User();
    }
}
