package se.lexicon.springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.springbootworkshop.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);


}

