package com.projet.msorder.redis;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
@Data
public class CartRedisRepositoryImpl implements CartRedisRepository {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Jedis jedis = new Jedis();

    @Override
    public void addItemToCart(String key, Object item) {
        try {
            String jsonObject = objectMapper.writeValueAsString(item);
            jedis.sadd(key, jsonObject);
            System.out.println(jsonObject);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Object> getCart(String key, Class type) {
        Collection<Object> cart = new ArrayList<>();
        for (String smember : jedis.smembers(key)) {
            try {
                cart.add(objectMapper.readValue(smember, type));
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cart;
    }

    @Override
    public void deleteItemFromCart(String key, Object item) {
        try {
            String itemCart = objectMapper.writeValueAsString(item);
            jedis.srem(key, itemCart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCart(String key) {
        jedis.del(key);
    }
}
