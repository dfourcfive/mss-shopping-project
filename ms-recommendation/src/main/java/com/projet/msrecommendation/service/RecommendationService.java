package com.projet.msrecommendation.service;

import com.projet.msrecommendation.model.Recommendation;

import java.util.List;

public interface RecommendationService {
    public Recommendation saveRecommendation(Recommendation recommendation);
    public List<Recommendation> getAllRecommendationByProductName(String productName);
    public void deleteRecommendation(Long id);

}
