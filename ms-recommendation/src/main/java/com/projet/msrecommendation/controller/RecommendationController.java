package com.projet.msrecommendation.controller;

import com.projet.msrecommendation.feignClient.ProductClient;
import com.projet.msrecommendation.feignClient.UserClient;
import com.projet.msrecommendation.http.HeaderGenerator;
import com.projet.msrecommendation.model.Product;
import com.projet.msrecommendation.model.Recommendation;
import com.projet.msrecommendation.model.User;
import com.projet.msrecommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class RecommendationController {


    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping(value = "/recommendations")
    private ResponseEntity<List<Recommendation>> getAllRating(@RequestParam("name") String productName){
        List<Recommendation> recommendations = recommendationService.getAllRecommendationByProductName(productName);
        if(!recommendations.isEmpty()) {
            return new ResponseEntity<List<Recommendation>>(
                    recommendations,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<List<Recommendation>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/{userId}/recommendations/{productId}")
    private ResponseEntity<Recommendation> saveRecommendations(
            @PathVariable ("userId") Long userId,
            @PathVariable ("productId") Long productId,
            @RequestParam ("rating") int rating,
            HttpServletRequest request){

        Product product = productClient.getProductById(productId);
        User user = userClient.getUserById(userId);

        if(product != null && user != null) {
            try {
                Recommendation recommendation = new Recommendation();
                recommendation.setProduct(product);
                recommendation.setUser(user);
                recommendation.setRating(rating);
                recommendationService.saveRecommendation(recommendation);
                return new ResponseEntity<Recommendation>(
                        recommendation,
                        headerGenerator.getHeadersForSuccessPostMethod(request, recommendation.getId()),
                        HttpStatus.CREATED);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Recommendation>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Recommendation>(
                headerGenerator.getHeadersForError(),
                HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/recommendations/{id}")
    private ResponseEntity<Void> deleteRecommendations(@PathVariable("id") Long id){
        Recommendation recommendation = recommendationService.getRecommendationById(id);
        if(recommendation != null) {
            try {
                recommendationService.deleteRecommendation(id);
                return new ResponseEntity<Void>(
                        headerGenerator.getHeadersForSuccessGetMethod(),
                        HttpStatus.OK);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Void>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Void>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }
}
