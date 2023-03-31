package fr.uga.l3miage.photonum.data.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.uga.l3miage.photonum.data.domain.Client;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

class ClientRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;

    @Autowired
    ClientRepository clientRepository;

    @Test
    void all() {
        Client c1 = Fixtures.newClient();
        c1.setNom("c");
        Client c2 = Fixtures.newClient();
        c2.setNom("b");
        Client c3 = Fixtures.newClient();
        c3.setNom("a");
        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);

        entityManager.flush();
        entityManager.detach(c1);
        entityManager.detach(c2);
        entityManager.detach(c3);

        List<Client> clients = clientRepository.all();
        assertThat(clients)
                .hasSize(3)
                .extracting("nom")
                .containsExactly("a", "b", "c");
    }
}
