package fr.uga.l3miage.photonum.data.repo;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TirageRepository implements CRUDRepository<Long, Tirage>{
    private final EntityManager entityManager;

    @Autowired
    public TirageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tirage save(Tirage tirage){
        entityManager.persist(tirage);
        return tirage;
    }

    @Override
    public Tirage get(Long id){
        return entityManager.find(Tirage.class, id);
    }

    @Override
    public void delete(Tirage tirage){
        entityManager.remove(tirage);
    }

    @Override
    public List<Tirage> all() {
        String getAll = "SELECT t FROM Tirage t";
        return entityManager.createNamedQuery(getAll, Tirage.class).getResultList();
    }
}

