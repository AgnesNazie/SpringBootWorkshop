package se.lexicon.springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootworkshop.entity.Book;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);


}

