package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Cadre;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class CadreRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    CadreRepository cadreRepository;

    @Test
    void all() {
        Cadre c1 = Fixtures.newCadre();
        c1.setId((long) 00111);
        Cadre c2 = Fixtures.newCadre();
        c2.setId((long) 00112);
        Cadre c3 = Fixtures.newCadre();
        c3.setId((long) 00113);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);

        entityManager.flush();
        entityManager.detach(c1);
        entityManager.detach(c2);
        entityManager.detach(c3);

        List<Cadre> cadres = cadreRepository.all();
        assertThat(cadres)
                .hasSize(3)
                .extracting("id")
                .containsExactly(00111, 00112, 00113);
    }
}
