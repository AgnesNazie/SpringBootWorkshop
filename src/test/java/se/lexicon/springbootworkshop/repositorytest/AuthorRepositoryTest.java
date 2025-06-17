package se.lexicon.springbootworkshop.repositorytest;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import se.lexicon.springbootworkshop.entity.Author;
import se.lexicon.springbootworkshop.entity.Book;
import se.lexicon.springbootworkshop.repository.AuthorRepository;
import se.lexicon.springbootworkshop.repository.BookRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest

@Transactional

public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    private Author author;
    private Author author2;
    private Book book;

    @BeforeEach
    void setup() {
        author = authorRepository.save(Author.builder()
                .firstName("Agnes")
                .lastName("Nazie")
                .build());

        author2 = authorRepository.save(Author.builder()
                .firstName("Fidelis")
                .lastName("Che")
                .build());

        book = bookRepository.save(Book.builder()
                .isbn("1234567893")
                .title("Spring in Action")
                .maxLoanDays(30)
                .authors(Set.of(author, author2))
                .build());

    }

    @Test
    void testFindByFirstName() {
        List<Author> foundList = authorRepository.findByFirstName("Agnes");
        assertEquals(1, foundList.size());
        assertEquals("Agnes", foundList.get(0).getFirstName());
    }

    @Test
    void testFindByLastName() {
        List<Author> foundList = authorRepository.findByLastName("Nazie");
        assertEquals(1, foundList.size());
        assertEquals("Nazie", foundList.get(0).getLastName());
    }

    @Test
    void testFindByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase() {
        List<Author> foundList = authorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("agnes", "nazie");
        assertEquals(1, foundList.size());
        assertEquals("Agnes", foundList.get(0).getFirstName());
    }

    @Test
    void testFindByBookId() {
        List<Author> foundList = authorRepository.findByBookId(book.getId());
        assertEquals(2, foundList.size());
    }

    @Test
    void testUpdateNameById() {
        int updated = authorRepository.updateNameById(author.getId(), "Fidelis", "Che");
        assertEquals(1, updated);

    }


}
