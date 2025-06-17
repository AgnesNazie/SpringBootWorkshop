package se.lexicon.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors")

    private Set<Book> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
        book.getAuthors().add(this);
    }
    public void removeBook(Book book) {
        books.remove(book);
        book.getAuthors().remove(this);
    }
}
