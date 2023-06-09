package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface ImageService extends BaseService<Image, Long> {

    Image save(Long id, Image im) throws EntityNotFoundException;

    Image get(Long id) throws EntityNotFoundException;

    Image update(Image cli) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException, Exception;

    void bind(Long id, Image im) throws EntityNotFoundException;

    public Collection<Image> list();

    public Collection<Image> listByClient(Long id) throws EntityNotFoundException;
}
