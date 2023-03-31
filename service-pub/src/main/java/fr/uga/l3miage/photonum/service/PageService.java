package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface PageService extends BaseService<Page, Long> {

    public Page save(Page page);

    public Page save(Long id, Page page) throws EntityNotFoundException;

    public Page get(Long id) throws EntityNotFoundException;

    public Page update(Page page) throws EntityNotFoundException;

    public void delete(Page page) throws EntityNotFoundException;

    public Page addPhoto(Long pageId, Long photoId) throws EntityNotFoundException;

    public Collection<Page> list();

}
