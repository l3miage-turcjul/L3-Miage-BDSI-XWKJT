package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Page;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class PageRepositoryTest extends Base {

    @Autowired
    PageRepository pageRepository;

    @Test
    void all() {
        Page p1 = Fixtures.newPage();
        p1.setId((long) 20001);
        Page p2 = Fixtures.newPage();
        p2.setId((long) 20002);
        Page p3 = Fixtures.newPage();
        p3.setId((long) 20003);
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);

        entityManager.flush();
        entityManager.detach(p1);
        entityManager.detach(p2);
        entityManager.detach(p3);

        List<Page> pages = pageRepository.all();
        assertThat(pages)
                .hasSize(3)
                .extracting("id")
                .containsExactly(20001, 20002, 20003);
    }
}
