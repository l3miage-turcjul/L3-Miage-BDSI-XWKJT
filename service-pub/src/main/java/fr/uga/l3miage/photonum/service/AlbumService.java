package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface AlbumService extends BaseService<Album, Long> {

    public Album save(Album album);

    public Album get(Long id) throws EntityNotFoundException;

    public Album update(Album album) throws EntityNotFoundException;

    public void delete(Album album) throws EntityNotFoundException;

    public Album addPage(Long albumId, Long pageId) throws EntityNotFoundException;

    public Collection<Album> list();

}
