package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Article;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void all() {
        Article a1 = Fixtures.newArticle();
        //a1.setId();
        Article a2 = Fixtures.newArticle();
        //a2.setId();
        Article a3 = Fixtures.newArticle();
        //a3.setId();
        entityManager.persist(a1);
        entityManager.persist(a2);
        entityManager.persist(a3);

        entityManager.flush();
        entityManager.detach(a1);
        entityManager.detach(a2);
        entityManager.detach(a3);

        List<Article> articles = articleRepository.all();
        assertThat(articles)
                .hasSize(3)
                .extracting("id")
                .containsExactly("a", "b", "c");
    }
}
