package fr.uga.l3miage.photonum.data.repo;
import fr.uga.l3miage.photonum.data.domain.Commande;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CommandeRepository implements CRUDRepository<Long, Commande> {
    private final EntityManager entityManager;

    @Autowired
    public CommandeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Commande save(Commande commande) {
        entityManager.persist(commande);
        return commande;
    }

    @Override
    public Commande get(Long id) {
        return entityManager.find(Commande.class, id);
    }


    @Override
    public void delete(Commande commande) {
        entityManager.remove(commande);
    }

   @Override
    public List<Commande> all() {
        String getAll = "select c from Commande c";
        return entityManager.createNamedQuery(getAll, Commande.class).getResultList();
    }
}
