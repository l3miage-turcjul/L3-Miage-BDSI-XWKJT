package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdressePostaleRepository implements CRUDRepository<Long, AdressePostale> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AdressePostaleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public AdressePostale save(AdressePostale adressePostale) {
        entityManager.persist(adressePostale);
        return adressePostale;
    }

    @Override
    public AdressePostale get(Long id) {
        return entityManager.find(AdressePostale.class, id);
    }

    @Override
    public void delete(AdressePostale adressePostale) {
        entityManager.remove(adressePostale);
    }

    @Override
    public List<AdressePostale> all() {
        String query = "SELECT ad FROM AdressePostale ad";
        return entityManager.createNamedQuery(query, AdressePostale.class).getResultList();
    }

    public List<AdressePostale> findByContainingCountry(String pays) {
        return entityManager.createNamedQuery("find-adresse-by-country", AdressePostale.class)
                .setParameter("Pays", "%" + pays + "%").getResultList();
    }

}
