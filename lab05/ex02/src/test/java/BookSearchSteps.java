import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookSearchSteps {

    private List<Book> booksList;
    private List<Book> searchedBooks;

    @Given("the following books exist:")
    public void theFollowingBooksExist(io.cucumber.datatable.DataTable books) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        booksList = books.asMaps(String.class, String.class).stream()
                .map(row -> new Book(row.get("title"), row.get("author"), LocalDate.parse(row.get("published"), formatter)))
                .collect(Collectors.toList());
    }

    @When("the customer searches for books published between {string} and {string}")
    public void theCustomerSearchesForBooksPublishedBetween(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        searchedBooks = booksList.stream()
                .filter(book -> !book.getPublished().isBefore(start) && !book.getPublished().isAfter(end))
                .collect(Collectors.toList());
    }

    @Then("{int} books should have been found")
    public void booksShouldHaveBeenFound(int expectedCount) {
        assertEquals(expectedCount, searchedBooks.size());
    }

    @Then("Book {int} should have the title {string}")
    public void bookShouldHaveTheTitle(int bookIndex, String expectedTitle) {
        assertEquals(expectedTitle, searchedBooks.get(bookIndex - 1).getTitle());
    }
}
