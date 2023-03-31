package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface PhotoService extends BaseService<Photo, Long> {

    public Photo save(Photo cli) throws EntityNotFoundException;

    Photo save(Long id, Photo cli) throws EntityNotFoundException;

    Photo get(Long id) throws EntityNotFoundException;

    Photo update(Photo cli) throws EntityNotFoundException;

    void delete(Photo cli) throws EntityNotFoundException;

    public Collection<Photo> list();

}
