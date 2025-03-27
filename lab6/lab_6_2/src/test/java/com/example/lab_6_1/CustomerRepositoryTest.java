package com.example.lab_6_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class CustomerRepositoryTest {

    // Start the PostgreSQL container with the specified init script
    @Container
    static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12")
            .withUsername("neorabbit")
            .withPassword("abc")
            .withDatabaseName("test")
            .withInitScript("db/migration/V001__INIT.sql");  // Path to your INIT script

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // Set the database connection properties dynamically based on the container
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testFindAllCustomers() {
        // Fetch all customers from the database
        List<Customer> customers = customerRepository.findAll();

        // Verify that the customers list is not empty and contains the expected entries
        assertThat(customers).isNotEmpty();
        assertThat(customers).hasSize(3);  // We inserted 3 customers in V001__INIT.sql
        assertThat(customers.stream().map(Customer::getName))
                .containsExactlyInAnyOrder("Max Verstappen", "Lando Norris", "Charles Leclerc");
    }
}