package se.lexicon.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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

    private boolean available = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);

    }
    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getBooks().remove(this);
    }
}
