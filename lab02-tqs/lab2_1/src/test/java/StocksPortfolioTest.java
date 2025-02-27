import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Required for Mockito + JUnit 5 integration
public class StocksPortfolioTest {

    @Mock
    private IStockmarketService stockmarketService; // Mock the dependency

    @InjectMocks
    private StocksPortfolio portfolio; // System Under Test (SuT)

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetTotalValue() {
        // Arrange: Create test stocks and define mock behavior
        Stock tesla = new Stock("Tesla", 10);
        Stock apple = new Stock("Apple", 20);
        portfolio.addStock(tesla);
        portfolio.addStock(apple);

        when(stockmarketService.lookUpPrice("Tesla")).thenReturn(3.0);
        when(stockmarketService.lookUpPrice("Apple")).thenReturn(4.0);

        // Act: Compute total value
        double totalValue = portfolio.getTotalValue();

        // Assert: Verify expected output
        double expectedValue = (10 * 3.0) + (20 * 4.0);
        assertEquals(expectedValue, totalValue, "Total portfolio value should match expected calculation");

        // Verify that the mock method was called twice
        verify(stockmarketService, times(2)).lookUpPrice(anyString());
    }
    //ai tests
    @Test
    void testMostValuableStocks() {
        // Arrange: Create test stocks
        Stock tesla = new Stock("Tesla", 10);  // 10 * 3.0 = 30
        Stock apple = new Stock("Apple", 20);  // 20 * 4.0 = 80
        Stock google = new Stock("Google", 5); // 5 * 10.0 = 50
        portfolio.addStock(tesla);
        portfolio.addStock(apple);
        portfolio.addStock(google);

        // Define stock prices
        when(stockmarketService.lookUpPrice("Tesla")).thenReturn(3.0);
        when(stockmarketService.lookUpPrice("Apple")).thenReturn(4.0);
        when(stockmarketService.lookUpPrice("Google")).thenReturn(10.0);

        // Act: Get top 2 most valuable stocks
        List<Stock> topStocks = portfolio.mostValuableStocks(2);

        // Assert: Verify the order is correct
        assertEquals(2, topStocks.size(), "Should return exactly 2 stocks");
        assertEquals("Apple", topStocks.get(0).getLabel(), "Most valuable stock should be Apple");
        assertEquals("Google", topStocks.get(1).getLabel(), "Second most valuable stock should be Google");

        // Verify that the mock was called 3 times (once per stock)
        verify(stockmarketService, times(3)).lookUpPrice(anyString());
    }

    @Test
}