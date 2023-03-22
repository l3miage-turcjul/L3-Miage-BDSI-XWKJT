package fr.uga.l3miage.photonum.data.repo;

import fr.uga.l3miage.photonum.data.domain.Album;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumRepository implements CRUDRepository<Long,Album>{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Album save(Album album) {
        entityManager.persist(album);
        return album;
    }

    @Override
    public Album get(Long id) {
        return entityManager.find(Album.class, id);
    }


    @Override
    public void delete(Album album) {
        entityManager.remove(album);
    }

    @Override
    public List<Album> all() {
        String query = "SELECT a FROM Album a";
       return entityManager.createNamedQuery(query, Album.class).getResultList();
    }
    
}
