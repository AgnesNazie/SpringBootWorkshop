package se.lexicon.springbootworkshop;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.springbootworkshop.entity.*;
import se.lexicon.springbootworkshop.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;



@Component
public class MyCommandLineRunner implements CommandLineRunner {
    private DetailsRepository detailsRepository;
    private BookRepository bookRepository;
    private AppUserRepository appUserRepository;
    public BookLoanRepository bookLoanRepository;
    public AuthorRepository authorRepository;

    @Autowired
    public MyCommandLineRunner(BookRepository bookRepository, AppUserRepository appUserRepository, BookLoanRepository bookLoanRepository, DetailsRepository detailsRepository, AuthorRepository authorRepository) {
        this.detailsRepository = detailsRepository;
        this.bookRepository = bookRepository;
        this.appUserRepository = appUserRepository;
        this.bookLoanRepository = bookLoanRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(String... args) throws Exception {

        // Create and save Details
        Details details = Details.builder()
                .name("agnes nazie")
                .birthDate(LocalDate.of(1995, 12, 26))
                .email("agnes@gmail.com")
                .build();
        details = detailsRepository.save(details);

        //create and save author
        Author author = authorRepository.save(Author.builder()
                .firstName("Agnes")
                .lastName("Nazie")
                .build());
        Author author2 = authorRepository.save(Author.builder()
                .firstName("Lizzy")
                .lastName("Neol")
                .build());


        //create and save book
        Book book = bookRepository.save(Book.builder()
                .isbn("1234567890")
                .title("Clean code")
                .maxLoanDays(10)
                .authors(new HashSet<>(Arrays.asList(author, author2)))
                .build());

        //create and save appuser
        AppUser appUser = appUserRepository.save(AppUser.builder()
                .username("agnes.nazie")
                .password("password")
                .regDate(LocalDate.now().toString())
                .userDetails(details)
                .build());

        //create and save bookloan
        bookLoanRepository.save(BookLoan.builder()
                .loanDate(LocalDate.now().minusDays(3))
                .dueDate(LocalDate.now().plusDays(7))
                .borrower(appUser)
                .book(book)
                .returned(false)
                .build());


        System.out.printf("User created with detials: %s%n", details);
        System.out.printf("Book saved: %s%n", book);
        System.out.println("BookLoan saved:" + bookLoanRepository);

        System.out.printf("Books with max loan days < 15: %s%n", bookRepository.findByMaxLoanDaysLessThan(15));
        System.out.printf("Books with title containing 'clean': %s%n", bookRepository.findByTitleContainingIgnoreCase("clean"));
        System.out.printf("Book loan by User ID: %s%n", bookLoanRepository.findByBorrowerId(appUser.getId()));
        System.out.printf("Book loan by Book ID: %s%n", bookLoanRepository.findByBookId(book.getId()));
        System.out.printf("Book loan by returned false: %s%n", bookLoanRepository.findByReturnedFalse());
        System.out.printf("Book loan by due date before today: %s%n", bookLoanRepository.findByDueDateBeforeAndReturnedFalse(LocalDate.now()));
        System.out.printf("Book loan by loan date between today and 10 days from now: %s%n", bookLoanRepository.findByLoanDateBetween(LocalDate.now(), LocalDate.now().plusDays(10)));

        //test part 4
        System.out.println("Author created: ");
        authorRepository.findAll().forEach(System.out::println);
        System.out.println("Book created: ");
        bookRepository.findAll().forEach(b -> {
            System.out.println(b);
            System.out.println("Authors of book: ");
            b.getAuthors().forEach(a -> System.out.println(" - " + a.getFirstName() + " " + a.getLastName()));
        });

        //test find by book id
        System.out.println("\nAuthors of Book with Id:" + book.getId() + ":");
        authorRepository.findByBookId(book.getId()).forEach(System.out::println);
    }
}
