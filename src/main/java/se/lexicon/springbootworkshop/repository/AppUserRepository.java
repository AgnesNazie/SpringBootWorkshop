package se.lexicon.springbootworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springbootworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    List<AppUser> findByUsername(String username);

    List<AppUser> findByRegDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<AppUser> findByUserDetails_Id(int id);

    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);
}
