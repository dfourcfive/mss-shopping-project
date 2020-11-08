package com.projet.msrecommendation.fallbacks;

import com.projet.msrecommendation.feignClient.UserClient;
import com.projet.msrecommendation.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFallBack implements UserClient {
    @Override
    public User getUserById(Long id) {
        return new User();
    }
}
