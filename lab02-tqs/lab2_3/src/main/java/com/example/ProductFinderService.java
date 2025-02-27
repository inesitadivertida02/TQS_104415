package com.example;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductFinderService {
    private static final String API_PRODUCTS = "https://fakestoreapi.com/products/";
    private final ISimpleHttpClient httpClient;

    private final ObjectMapper response_org = new ObjectMapper();

    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Optional<Product> findProductDetails(Integer id) {
        try {
            String response = httpClient.doHttpGet(API_PRODUCTS + id);
            JsonNode json = response_org.readTree(response);
            if (json == null || json.get("id") == null) {
                System.err.println("Invalid JSON response: " + response);
                return Optional.empty();
            }

            return Optional.of(new Product(json.get("id").asInt(), json.get("image").asText(), json.get("description").asText(), json.get("price").asDouble(), json.get("title").asText(), json.get("category").asText()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();

    }
}
