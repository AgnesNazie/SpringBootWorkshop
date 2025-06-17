package se.lexicon.springbootworkshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootworkshop.entity.BookLoan;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Integer> {
    List<BookLoan> findByBorrowerId(Integer borrowerId);

    List<BookLoan> findByBookId(Integer bookId);

    List<BookLoan> findByReturnedFalse();

    List<BookLoan> findByDueDateBeforeAndReturnedFalse(LocalDate dueDate);

    List<BookLoan> findByLoanDateBetween(LocalDate start, LocalDate end);


}
