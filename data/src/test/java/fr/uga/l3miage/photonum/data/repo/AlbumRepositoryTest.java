package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Album;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class AlbumRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    AlbumRepository albumRepository;

    @Test
    void all() {
        Album a1 = Fixtures.newAlbum();
        a1.setTitre("c");
        Album a2 = Fixtures.newAlbum();
        a2.setTitre("b");
        Album a3 = Fixtures.newAlbum();
        a3.setTitre("a");
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);

        entityManager.flush();
        entityManager.detach(a1);
        entityManager.detach(a2);
        entityManager.detach(a3);

        List<Album> albums = albumRepository.all();
        assertThat(albums)
                .hasSize(3)
                .extracting("titre")
                .containsExactly("a", "b", "c");
    }
}
