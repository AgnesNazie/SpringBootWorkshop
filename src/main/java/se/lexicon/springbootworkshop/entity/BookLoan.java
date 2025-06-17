package se.lexicon.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookLoan {
    //Primary key auto incremented by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //date of when the book was loan(cannot be null)
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;
    // date when the book is to be returned, cannot be null
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    // returned flag, default is false
    private boolean returned;

    //many bookloans can be associated  with one borrower
    @ManyToOne(optional = false)
    @JoinColumn(name = "borrower_id", nullable = false)
    private AppUser borrower;

    //many book loans can associated with one book
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @PrePersist
    public void calculateDueDate() {
        if (loanDate != null && this.book.getMaxLoanDays() > 0) {
            this.dueDate = this.loanDate != null
                    ? this.loanDate.plusDays(book.getMaxLoanDays())
                    : LocalDate.now().plusDays(book.getMaxLoanDays());
        }
    }
}
