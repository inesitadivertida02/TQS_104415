package com.example.lab_6_1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

@SpringBootTest  // Loads the full Spring Boot context for integration testing
@TestMethodOrder(OrderAnnotation.class)  // Ensures tests run in order
public class CustomerRepositoryTest {

    // Start a PostgreSQL container using Testcontainers
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @BeforeAll
    static void startContainer() {
        postgres.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");  // Ensures schema creation
    }

    @Autowired
    private CustomerRepository repository;

    private static Long customerId;

    @Test
    @Order(1)
    void testInsertCustomer() {
        Customer customer = new Customer("John Doe");
        Customer savedCustomer = repository.save(customer);
        Assertions.assertNotNull(savedCustomer.getId());
        customerId = savedCustomer.getId();
    }

    @Test
    @Order(2)
    void testRetrieveCustomer() {
        Optional<Customer> retrieved = repository.findById(customerId);
        Assertions.assertTrue(retrieved.isPresent());
        Assertions.assertEquals("John Doe", retrieved.get().getName());
    }

    @Test
    @Order(3)
    void testUpdateCustomer() {
        Customer customer = repository.findById(customerId).orElseThrow();
        customer.setName("Jane Doe");
        repository.save(customer);
        Customer updatedCustomer = repository.findById(customerId).orElseThrow();
        Assertions.assertEquals("Jane Doe", updatedCustomer.getName());
    }

    @Test
    @Order(4)
    void testDeleteCustomer() {
        repository.deleteById(customerId);
        Assertions.assertFalse(repository.findById(customerId).isPresent());
    }
}
