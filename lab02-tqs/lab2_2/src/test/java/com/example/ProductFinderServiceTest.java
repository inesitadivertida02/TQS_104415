package com.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ProductFinderServiceTest {
    @Mock
    private ISimpleHttpClient httpClient;  // Manually created mock
    @InjectMocks
    private ProductFinderService service;

    @Test
    void shouldReturnJsonResponseWhenIdIsValid() throws Exception {
        String jsonResponse = "{ \"id\":3, \"title\":\"Mens Cotton Jacket\", \"price\":55.99,"
                + "\"description\":\"Great jacket\", \"image\":\"url\", \"category\":\"men's clothing\" }";

        when(httpClient.doHttpGet("https://fakestoreapi.com/products/3")).thenReturn(jsonResponse);

        Optional<String> response = service.findProductDetails(3);

        assertThat(response).isPresent().contains(jsonResponse);
    }
}
