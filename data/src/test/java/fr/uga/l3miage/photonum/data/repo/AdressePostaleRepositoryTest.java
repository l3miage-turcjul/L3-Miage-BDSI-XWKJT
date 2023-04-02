package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import static org.assertj.core.api.Assertions.assertThat;

class AdressePostaleRepositoryTest extends Base {

    @Autowired
    AdressePostaleRepository adressePostaleRepository;

    @Test
    void all() {
        AdressePostale a1 = Fixtures.newAdressePostale();
        a1.setAdresse("a");
        a1.setCodePostal("1111");
        a1.setPays("France");
        a1.setVille("SMH");

        entityManager.persist(a1);


        entityManager.flush();
        entityManager.detach(a1);
      

        List<AdressePostale> adressePostales = adressePostaleRepository.all();
        assertThat(adressePostales)
                .hasSize(1)
                .extracting("adresse")
                .containsExactly("a");
    }
}
