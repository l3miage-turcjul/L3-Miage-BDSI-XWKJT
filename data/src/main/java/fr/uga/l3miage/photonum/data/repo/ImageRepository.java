package fr.uga.l3miage.photonum.data.repo;
import fr.uga.l3miage.photonum.data.domain.Image;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ImageRepository implements CRUDRepository<Long, Image>{
    private final EntityManager entityManager;

    @Autowired
    public ImageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Image save(Image image){
        entityManager.persist(image);
        return image;
    }

    @Override
    public Image get(Long id){
        return entityManager.find(Image.class, id);
    }

    @Override
    public void delete(Image image){
        entityManager.remove(image);
    }

    @Override
    public List<Image> all() {
        String getAll = "SELECT i FROM Image i";
        return entityManager.createNamedQuery(getAll, Image.class).getResultList();
    }
}
