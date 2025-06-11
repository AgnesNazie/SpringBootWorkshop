package se.lexicon.springbootworkshop.repositorytest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.springbootworkshop.entity.AppUser;
import se.lexicon.springbootworkshop.entity.Details;
import se.lexicon.springbootworkshop.repository.AppUserRepository;
import se.lexicon.springbootworkshop.repository.DetailsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@SpringBootTest(classes = AppUserRepository.class)
@SpringBootTest
public class AppUserRepositoryTest {
    //@Autowired
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    void testFindByUsername() {
        Details details = Details.builder()
                .email("agnes@gmail.com")
                .name("Agnes Nazie")
                .birthDate(LocalDate.of(1995, 12, 25))
                .build();
        detailsRepository.save(details);

        AppUser appUser = AppUser.builder()
                .username("agnes.nazie")
                .password("12345")
                .regDate(LocalDate.now().toString())
                .userDetails(details)
                .build();
        appUserRepository.save(appUser);

        Optional<AppUser> found = appUserRepository.findByUsername("agnes.nazie");
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("agnes.nazie");


    }
    @Test
    void testFindByRegDateBetween() {
        Details details = Details.builder()
                .email("fidelis@example.com")
                .name("fidelis che")
                .birthDate(LocalDate.of(1992, 3, 15))
                .build();
        detailsRepository.save(details);

        String today = LocalDate.now().toString();

        AppUser appUser = AppUser.builder()
                .username("fidelis.che")
                .password("root")
                .regDate(today)
                .userDetails(details)
                .build();
        appUserRepository.save(appUser);

        List<AppUser> users = appUserRepository.findByRegDateBetween(today, today);

        assertThat(users).isNotNull();
        assertThat(users.get(0).getUsername()).isEqualTo("fidelis.che");
    }
    @Test
    void testFindByUserDetailsId() {
        Details details = Details.builder()
                .email("lizzy@example.com")
                .name("lizzy neol")
                .birthDate(LocalDate.of(1985, 7, 20))
                .build();
        detailsRepository.save(details);

        AppUser appUser = AppUser.builder()
                .username("lizzy.neol")
                .password("secret")
                .regDate(LocalDate.now().toString())
                .userDetails(details)
                .build();
        appUserRepository.save(appUser);

        Optional<AppUser> found = appUserRepository.findByUserDetails_Id(details.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("lizzy.neol");
    }
    @Test
    void testFindByUserDetailsEmailIgnoreCase() {
        Details details = Details.builder()
                .email("edwin@example.com")
                .name("edwin che")
                .birthDate(LocalDate.of(2000, 1, 1))
                .build();
        detailsRepository.save(details);

        AppUser appUser = AppUser.builder()
                .username("edwin.che")
                .password("123abc")
                .regDate(LocalDate.now().toString())
                .userDetails(details)
                .build();
        appUserRepository.save(appUser);

        Optional<AppUser> found = appUserRepository.findByUserDetails_EmailIgnoreCase("edwin@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("edwin.che");
    }

}
