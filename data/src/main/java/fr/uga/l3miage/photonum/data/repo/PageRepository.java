package fr.uga.l3miage.photonum.data.repo;
import fr.uga.l3miage.photonum.data.domain.Page;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PageRepository implements CRUDRepository<Long, Page>{
    private final EntityManager entityManager;

    @Autowired
    public PageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page save(Page page){
        entityManager.persist(page);
        return page;
    }

    @Override
    public Page get(Long id){
        return entityManager.find(Page.class, id);
    }

    @Override
    public void delete(Page page){
        entityManager.remove(page);
    }

    @Override
    public List<Page> all() {
        String getAll = "SELECT p FROM Page p";
        return entityManager.createNamedQuery(getAll, Page.class).getResultList();
    }
}

