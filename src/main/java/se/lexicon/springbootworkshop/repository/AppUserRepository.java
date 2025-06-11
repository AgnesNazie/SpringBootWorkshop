package se.lexicon.springbootworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.springbootworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

    List<AppUser> findByRegDateBetween(String regDate, String regDate2);

    Optional<AppUser> findByUserDetails_Id(int id);

    Optional<AppUser> findByUserDetails_EmailIgnoreCase(String email);
}
