package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface PageService extends BaseService<Page, Long> {

    public Page save(Page page);

    public Page get(Long id) throws EntityNotFoundException;

    public Page update(Page page) throws EntityNotFoundException;

    public void delete(Long id) throws EntityNotFoundException;

    public Page addPhoto(Long pageId, Long photoId);

    public Collection<Page> list();

}
