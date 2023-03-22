package fr.uga.l3miage.photonum.data.repo;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CalendrierRepository implements CRUDRepository<Long, Calendrier>{
    private final EntityManager entityManager;

    @Autowired
    public CalendrierRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Calendrier save(Calendrier calendrier){
        entityManager.persist(calendrier);
        return calendrier;
    }

    @Override
    public Calendrier get(Long id){
        return entityManager.find(Calendrier.class, id);
    }

    @Override
    public void delete(Calendrier calendrier){
        entityManager.remove(calendrier);
    }

    @Override
    public List<Calendrier> all() {
        String getAll = "SELECT c FROM Calendier c";
        return entityManager.createNamedQuery(getAll, Calendrier.class).getResultList();
    }
}
