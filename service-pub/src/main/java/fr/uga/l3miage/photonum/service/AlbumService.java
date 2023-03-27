package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface AlbumService extends BaseService<Album,Long>{

    public Album save(Album album);

    public Album get(Long id) throws EntityNotFoundException;

    public Album update(Album album) throws EntityNotFoundException;

    public void delete(Long id) throws EntityNotFoundException;

    public Album addPage(Album album, long pageId);
  
}