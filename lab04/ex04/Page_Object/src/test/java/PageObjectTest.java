
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

// Page Object Model para a página de pesquisa
public class PageObjectTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "[data-testid='search-input']")
    private WebElement searchBox;

    @FindBy(css = "[data-testid='book-title']")
    private WebElement bookTitle;

    @FindBy(css = "[data-testid='book-author']")
    private WebElement bookAuthor;

    public PageObjectTest(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void searchForBook(String bookName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(bookName);
        searchBox.submit();
    }

    public boolean isBookDisplayed(String expectedTitle, String expectedAuthor) {
        wait.until(ExpectedConditions.visibilityOf(bookTitle));
        return bookTitle.getText().equals(expectedTitle) && bookAuthor.getText().equals(expectedAuthor);
    }
}