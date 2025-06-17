package se.lexicon.springbootworkshop.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.lexicon.springbootworkshop.entity.Author;

import java.util.List;
@Repository

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findByFirstName(String firstName);

    List<Author> findByLastName(String lastName);

    List<Author> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

    @Query("SELECT a FROM Author a JOIN a.books b WHERE b.id = :bookId")
    List<Author> findByBookId(@Param("bookId") int bookId);


    @Modifying
    @Transactional
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName WHERE a.id = :id")
    int updateNameById(@Param("id") int id, @Param("firstName") String firstName, @Param("lastName") String lastName);

    //delete by id(inherited from JPA Repository
    @Modifying
    @Transactional
    @Query("DELETE FROM Author a WHERE a.id = :id")
    int deleteById(@Param("id") int id);
}
