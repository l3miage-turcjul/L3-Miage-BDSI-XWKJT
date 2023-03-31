package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class CalendrierRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    CalendrierRepository calendrierRepository;

    @Test
    void all() {
        Calendrier c1 = Fixtures.newCalendrier();
        c1.setId((long) 00114);
        Calendrier c2 = Fixtures.newCalendrier();
        c2.setId((long) 00115);
        Calendrier c3 = Fixtures.newCalendrier();
        c3.setId((long) 00116);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);

        entityManager.flush();
        entityManager.detach(c1);
        entityManager.detach(c2);
        entityManager.detach(c3);

        List<Calendrier> calendriers = calendrierRepository.all();
        assertThat(calendriers)
                .hasSize(3)
                .extracting("id")
                .containsExactly(00114, 00115, 00116);
    }
}
