
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import java.time.Duration;

public class HarrypottersearchTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void setUp() {
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get("https://fictitious-online-bookstore.com");
  }

  @Test
  public void testSearchHarryPotter() {
    WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='search-input']")));
    searchBox.sendKeys("Harry Potter");
    searchBox.sendKeys(Keys.RETURN);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='book-search-item']")));

    WebElement bookTitle = driver.findElement(By.cssSelector("[data-testid='book-title']"));
    WebElement bookAuthor = driver.findElement(By.cssSelector("[data-testid='book-author']"));

    assertEquals("Harry Potter and the Sorcerer's Stone", bookTitle.getText());
    assertEquals("J. K. Rowling", bookAuthor.getText());
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}
