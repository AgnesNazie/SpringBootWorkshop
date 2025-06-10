package se.lexicon.springbootworkshop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import se.lexicon.springbootworkshop.entity.Details;

import java.util.List;
import java.util.Optional;

public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Optional<Details> findByEmail(String email);

    List<Details> findByNameContaining(String namePart);

    List<Details> findByNameIgnoreCase(String name);
}
