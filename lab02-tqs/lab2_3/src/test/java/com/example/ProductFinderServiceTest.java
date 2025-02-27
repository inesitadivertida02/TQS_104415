package com.example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductFinderServiceTest {

    @Test
    void shouldReturnProductResponseWhenIdIsValid() throws IOException {
        ISimpleHttpClient httpClient = new TqsBasicHttpClient();
        ProductFinderService service = new ProductFinderService(httpClient);
        Optional<Product> product = service.findProductDetails(3);
        assertThat(product).isPresent();
        assertThat(product.get().getId()).isEqualTo(3);
        assertThat(product.get().getTitle()).isEqualTo("Mens Cotton Jacket");

    }

    @Test
    void shouldReturnProductIdInvalid() throws Exception{
        ISimpleHttpClient httpClient = new TqsBasicHttpClient();
        ProductFinderService service = new ProductFinderService(httpClient);
        Optional<Product> product = service.findProductDetails(300);
        assertThat(product).isEmpty();
    }
}
