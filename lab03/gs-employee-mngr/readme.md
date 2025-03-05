
## a) Identify a couple of examples that use AssertJ expressive methods chaining.

Some examples of AssertJ expressive methods chaining are shown below:

```java
@Test
     void givenEmployees_whenGetEmployees_thenStatus200()  {
             createTestEmployee("bob", "bob@deti.com");
             createTestEmployee("alex", "alex@deti.com");


             ResponseEntity<List<Employee>> response = restTemplate
        .exchange("/api/employees", HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");

        }
```


## b) Take note of transitive annotations included in @DataJpaTest.


When you use `@DataJpaTest`, it automatically includes several transitive annotations that affect how your tests behave. Some of these key annotations are:

- **`@Transactional`**  
  Ensures that any database changes made during the test are **rolled back** after the test execution. This ensures that tests do not affect the actual data in the database.

- **`@AutoConfigureTestDatabase`**  
  By default, this annotation replaces your main database with an **embedded in-memory database** (like H2), unless explicitly configured otherwise. This is useful for testing but can be changed if needed.

- **`@ExtendWith(SpringExtension.class)`**  
  Integrates Spring Boot with JUnit 5, ensuring that the Spring context is properly set up during tests.


## c) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

In the provided test class `C_EmployeeController_WithMockServiceTest`, we mock the behavior of the `EmployeeService` which mocks the repository to avoid involving the actual database. This allows us to simulate the behavior of the service layer without accessing the database.

## Code Snippet:

```java
@Test
void givenManyEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {
    Employee alex = new Employee("alex", "alex@deti.com");
    Employee john = new Employee("john", "john@deti.com");
    Employee bob = new Employee("bob", "bob@deti.com");

    List<Employee> allEmployees = Arrays.asList(alex, john, bob);

    // Mocking the behavior of EmployeeService instead of involving a database
    when(service.getAllEmployees()).thenReturn(allEmployees);

    mvc.perform(
            get("/api/employees").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[0].name", is(alex.getName())))
            .andExpect(jsonPath("$[1].name", is(john.getName())))
            .andExpect(jsonPath("$[2].name", is(bob.getName())));

    verify(service, times(1)).getAllEmployees();
}
```

- **Mocking**: The behavior of the `EmployeeService` method `getAllEmployees()` is mocked to return a fixed list of employees.
- **No Database**: Since we mock the service layer, the test avoids any database interactions and instead works with the mock data.


## d) What is the difference between standard @Mock and @MockBean?
### Difference Between `@Mock` and `@MockBean`

- **`@Mock`**:
  - Provided by **Mockito** for unit tests.
  - Creates a mock object that doesn’t interact with the Spring context.
  - You need to manually inject the mock into the test class.

- **`@MockBean`**:
  - Provided by **Spring Boot** for integration tests.
  - Replaces a Spring bean with a mocked version within the Spring context.
  - Automatically injects the mock into Spring-managed beans.

### Key Differences:

1. **Context**:
  - `@Mock`: Used for standard unit tests.
  - `@MockBean`: Used for Spring-based integration tests.

2. **Scope**:
  - `@Mock`: Mock is only available within the test class.
  - `@MockBean`: Mock replaces the Spring bean in the entire context.

3. **Usage**:
  - `@Mock`: Typically for mocking service dependencies.
  - `@MockBean`: Replaces real Spring beans with mocks in integration tests.
## e) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
The **application-integrationtest.properties** file is used to define the configuration settings specifically for integration tests. It allows you to configure things like database connections, server settings, and other properties required for running integration tests. This file is only used during integration testing, where actual services, databases, and components are involved, unlike unit tests, which rely on mocks to simulate behavior and do not require real connections or services. It ensures the correct environment and configurations are in place for the tests to run accurately.

## f) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?
### Key Differences Between Test Strategies (C, D, and E)

- **C**:
  - Uses **mocking** (`@MockBean`) to isolate the controller and test the web layer with **MockMvc**. It doesn't interact with a real database.

- **D**:
  - Uses a **real database** (`@SpringBootTest` with `AutoConfigureTestDatabase`) to test both the web and persistence layers. It interacts with actual database entries and tests the integration of the controller and repository.

- **E**:
  - Similar to **D**, but uses **`TestRestTemplate`** for RESTful integration testing instead of `MockMvc`. This strategy also interacts with a real database and performs HTTP requests through a test-friendly REST client.