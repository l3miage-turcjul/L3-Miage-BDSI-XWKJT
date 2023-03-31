package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class AdressePostaleRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    AdressePostaleRepository adressePostaleRepository;

    @Test
    void all() {
        AdressePostale a1 = Fixtures.newAdressePostale();
        a1.setAdresse("c");
        AdressePostale a2 = Fixtures.newAdressePostale();
        a2.setAdresse("b");
        AdressePostale a3 = Fixtures.newAdressePostale();
        a3.setAdresse("a");
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);

        entityManager.flush();
        entityManager.detach(a1);
        entityManager.detach(a2);
        entityManager.detach(a3);

        List<AdressePostale> adressePostales = adressePostaleRepository.all();
        assertThat(adressePostales)
                .hasSize(3)
                .extracting("adresse")
                .containsExactly("a", "b", "c");
    }
}
