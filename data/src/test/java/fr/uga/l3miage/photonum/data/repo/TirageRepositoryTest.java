package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class TirageRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    TirageRepository tirageRepository;

    @Test
    void all() {
        Tirage c1 = Fixtures.newTirage();
        c1.setId((long) 00117);
        Tirage c2 = Fixtures.newTirage();
        c2.setId((long)01000);
        Tirage c3 = Fixtures.newTirage();
        c3.setId((long) 01001);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);

        entityManager.flush();
        entityManager.detach(c1);
        entityManager.detach(c2);
        entityManager.detach(c3);

        List<Tirage> tirages = tirageRepository.all();
        assertThat(tirages)
                .hasSize(3)
                .extracting("id")
                .containsExactly(00117, 01000, 01001);
    }
}
