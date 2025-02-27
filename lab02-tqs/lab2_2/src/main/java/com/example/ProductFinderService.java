package com.example;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductFinderService {
    private static final String API_PRODUCTS = "https://fakestoreapi.com/products/";
    private final ISimpleHttpClient httpClient;

    private final ObjectMapper response = new ObjectMapper();

    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(Integer id) {
        try {
            String response = httpClient.doHttpGet(API_PRODUCTS + id);
            return (response == null || response.isEmpty()) ? Optional.empty() : Optional.of(response);
        } catch (Exception e) {
            return Optional.empty(); // Return empty Optional if an error occurs
        }
    }

}
