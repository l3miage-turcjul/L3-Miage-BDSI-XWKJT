package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.data.domain.Commande;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class CommandRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    CommandeRepository commandeRepository;

    @Test
    void all() {
        Commande c1 = Fixtures.newCommande();
        AdressePostale a1 = new AdressePostale();
        c1.setAdressePostale(a1);
        Commande c2 = Fixtures.newCommande();
        AdressePostale a2 = new AdressePostale();
        c2.setAdressePostale(a2);
        Commande c3 = Fixtures.newCommande();
        AdressePostale a3 = new AdressePostale();
        c3.setAdressePostale(a3);
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);

        entityManager.flush();
        entityManager.detach(c1);
        entityManager.detach(c2);
        entityManager.detach(c3);

        List<Commande> commandes = commandeRepository.all();
        assertThat(commandes)
                .hasSize(3)
                .extracting("adressePostale")
                .containsExactly(a1,a2,a3);
    }
}
