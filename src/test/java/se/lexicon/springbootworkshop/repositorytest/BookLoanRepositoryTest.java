package se.lexicon.springbootworkshop.repositorytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.springbootworkshop.entity.AppUser;
import se.lexicon.springbootworkshop.entity.Book;
import se.lexicon.springbootworkshop.entity.BookLoan;
import se.lexicon.springbootworkshop.entity.Details;
import se.lexicon.springbootworkshop.repository.AppUserRepository;
import se.lexicon.springbootworkshop.repository.BookLoanRepository;
import se.lexicon.springbootworkshop.repository.BookRepository;
import se.lexicon.springbootworkshop.repository.DetailsRepository;

import java.time.LocalDate;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookLoanRepositoryTest {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BookRepository bookRepository;

    private AppUser appUser;
    private Book book;
    private BookLoan bookLoan;
    @Autowired
    private DetailsRepository detailsRepository;

    @BeforeEach
    public void setup() {

        // Create and save Details
        Details details = Details.builder()
                .name("agnes nazie")
                .birthDate(LocalDate.of(1995, 12, 26))
                .email("agnes@gmail.com")
                .build();
        details = detailsRepository.save(details);
        // create and save appuser
        appUser = appUserRepository.save(AppUser.builder()
                .username("agnes.nazie")
                .password("password")
                .regDate(LocalDate.now().toString())
                .userDetails(details)
                .build());

        //create and save book
        book = bookRepository.save(Book.builder()
                .isbn("1234567890")
                .title("The Hobbit")
                .maxLoanDays(10)
                .build());

        //create and save bookloan
        bookLoan = bookLoanRepository.save(BookLoan.builder()
                .loanDate(LocalDate.now().minusDays(10))
                .dueDate(LocalDate.now().minusDays(1))
                .borrower(appUser)
                .book(book)
                .returned(false)
                .build());
    }

    @Test
    void testFindByBorrowerId() {
        //find bookloans by borrower id
        List<BookLoan> result = bookLoanRepository.findByBorrowerId(appUser.getId());
        //assert that the found bookloans contains the bookloan we created
        assertThat(result).contains(bookLoan);
    }

    @Test
    void testFindByBookId() {
        //find bookloans by book id
        List<BookLoan> result = bookLoanRepository.findByBookId(book.getId());
        assertThat(result).contains(bookLoan);
    }

    @Test
    void testFindByReturnedFalse() {
        //find bookloans where returned is false
        List<BookLoan> result = bookLoanRepository.findByReturnedFalse();
        assertThat(result).contains(bookLoan);
    }
    @Test
    void testFindByDueDateBeforeAndReturnedFalse() {
        List<BookLoan> result = bookLoanRepository.findByDueDateBeforeAndReturnedFalse(LocalDate.now());
        assertThat(result).contains(bookLoan);
    }

    @Test
    void testFindByLoanDateBetween() {
        LocalDate start = LocalDate.now().minusDays(15);
        LocalDate end = LocalDate.now();
        //find bookloans where returned is false and due date is before today
        List<BookLoan> result = bookLoanRepository.findByLoanDateBetween(start, end);
        assertThat(result).contains(bookLoan);
    }
}
