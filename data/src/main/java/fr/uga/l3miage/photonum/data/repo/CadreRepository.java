package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CadreRepository implements CRUDRepository<Long,Cadre>{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CadreRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Cadre save(Cadre cadre) {
        entityManager.persist(cadre);
        return cadre;
    }

    @Override
    public Cadre get(Long id) {
        return entityManager.find(Cadre.class, id);
    }

    @Override
    public void delete(Cadre cadre) {
        entityManager.remove(cadre);
    }

    @Override
    public List<Cadre> all() {
        String query = "SELECT c FROM Cadre c";
       return entityManager.createNamedQuery(query, Cadre.class).getResultList();
    }

    
    
}
