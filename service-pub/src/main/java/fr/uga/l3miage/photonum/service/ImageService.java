package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface ImageService extends BaseService<Image, Long> {

    Image save(Image cli) throws EntityNotFoundException;
    Image get(Long id) throws EntityNotFoundException;
    Image update(Image cli) throws EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException;
    public Collection<Image> list();

}
