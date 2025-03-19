import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(final LocalDate from, final LocalDate to) {
        return store.stream().filter(book -> {
                    return !book.getPublished().isBefore(from) && !book.getPublished().isAfter(to);
                }).sorted((book1, book2) -> book2.getPublished().compareTo(book1.getPublished()))
                .collect(Collectors.toList());
    }
}
