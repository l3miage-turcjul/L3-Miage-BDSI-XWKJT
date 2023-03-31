package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Image;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class ImageRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    ImageRepository imageRepository;

    @Test
    void all() {
        Image i1 = Fixtures.newImage();
        i1.setId((long) 40001);
        Image i2 = Fixtures.newImage();
        i2.setId((long) 40002);
        Image i3 = Fixtures.newImage();
        i3.setId((long) 40003);
        entityManager.persist(i1);
        entityManager.persist(i2);
        entityManager.persist(i3);

        entityManager.flush();
        entityManager.detach(i1);
        entityManager.detach(i2);
        entityManager.detach(i3);

        List<Image> images = imageRepository.all();
        assertThat(images)
                .hasSize(3)
                .extracting("id")
                .containsExactly(40001,40002,40003);
    }
}
