package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Photo;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class PhotoRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    PhotoRepository photoRepository;

    @Test
    void all() {
        Photo p1 = Fixtures.newPhoto();
        p1.setId((long) 30001);
        Photo p2 = Fixtures.newPhoto();
        p2.setId((long) 30002);
        Photo p3 = Fixtures.newPhoto();
        p3.setId((long) 30003);
        entityManager.persist(p1);
        entityManager.persist(p2);
        entityManager.persist(p3);

        entityManager.flush();
        entityManager.detach(p1);
        entityManager.detach(p2);
        entityManager.detach(p3);

        List<Photo> photos = photoRepository.all();
        assertThat(photos)
                .hasSize(3)
                .extracting("id")
                .containsExactly(30001, 30002, 30003);
    }
}
