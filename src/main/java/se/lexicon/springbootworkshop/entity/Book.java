package se.lexicon.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    //Primary key auto incremented by the database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //ISBN is unique and not null
    @Column(nullable = false, unique = true)
    private String isbn;

    //book title can be null or empty
    private String title;

    // maximun number of days thes books can be loan out
    @Column(name = "max_loan_days")
    private int maxLoanDays;
}
