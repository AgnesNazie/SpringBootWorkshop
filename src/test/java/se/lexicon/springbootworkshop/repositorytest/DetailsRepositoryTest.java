package se.lexicon.springbootworkshop.repositorytest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.springbootworkshop.entity.Details;
import se.lexicon.springbootworkshop.repository.AppUserRepository;
import se.lexicon.springbootworkshop.repository.DetailsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest(classes = AppUserRepository.class)
@SpringBootTest
public class DetailsRepositoryTest {
    //@Autowired
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    void testFindByEmail() {
        Details details = Details.builder()
                .email("agnes@example.com")
                .name("agnes nazie")
                .birthDate(LocalDate.of(1995, 12, 25))
                .build();
        detailsRepository.save(details);

        Optional<Details> found = detailsRepository.findByEmail("agnes@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("agnes nazie");
    }

    @Test
    void testFindByNameContaining() {
        Details details1 = Details.builder()
                .email("fidelis@example.com")
                .name("fidelis che")
                .birthDate(LocalDate.of(1994, 2, 18))
                .build();

        Details details2 = Details.builder()
                .email("fidelis.che@example.com")
                .name("fuche che")
                .birthDate(LocalDate.of(1991, 6, 23))
                .build();

        detailsRepository.save(details1);
        detailsRepository.save(details2);

        List<Details> foundList = detailsRepository.findByNameContaining("che");

        assertThat(foundList).hasSize(2);
        assertThat(foundList.get(0).getName()).contains("che");
    }

    @Test
    void testFindByNameIgnoreCase() {
        Details details = Details.builder()
                .email("lizzy@example.com")
                .name("lizzy neol")
                .birthDate(LocalDate.of(1988, 11, 5))
                .build();
        detailsRepository.save(details);

        List<Details> foundList = detailsRepository.findByNameIgnoreCase("lizzy neol");

        assertThat(foundList).isNotNull();
        assertThat(foundList.get(0).getEmail()).isEqualTo("lizzy@example.com");
    }


}
