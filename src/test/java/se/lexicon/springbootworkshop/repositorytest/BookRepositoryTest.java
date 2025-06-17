package se.lexicon.springbootworkshop.repositorytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springbootworkshop.entity.Book;
import se.lexicon.springbootworkshop.repository.BookRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    private Book book2;

    @BeforeEach
    public void setup() {
        book = bookRepository.save(Book.builder()
                .isbn("1234567890")
                .title("Clean Code")
                .maxLoanDays(30)
                .build());

        book2 = bookRepository.save(Book.builder()
                .isbn("abcdef")
                .title("Java Programming")
                .maxLoanDays(15)
                .build());
    }

    @Test
    void testFindByIsbnIgnoreCase() {
        List<Book> foundList = bookRepository.findByIsbnIgnoreCase("1234567890");
        assertThat(foundList).contains(book);
    }
    @Test
    void testFindByTitleContainingIgnoreCase() {
        List<Book> foundList = bookRepository.findByTitleContainingIgnoreCase("clean code");
        assertThat(foundList).contains(book);
    }
    @Test
    void testFindByMaxLoanDaysLessThan() {
        List<Book> foundList = bookRepository.findByMaxLoanDaysLessThan(20);
        assertThat(foundList).contains(book2);
        assertThat(foundList).doesNotContain(book);
    }

}
