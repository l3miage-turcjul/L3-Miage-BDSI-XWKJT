package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.repo.AlbumRepository;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final PageService pageService;
    private final PhotoService photoService;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository, PageService pageService, PhotoService photoService) {
        this.albumRepository = albumRepository;
        this.pageService = pageService;
        this.photoService = photoService;
    }

    @Override
    public Album save(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album get(Long id) throws EntityNotFoundException {
        return albumRepository.get(id);
    }

    @Override
    public Album update(Album album) throws EntityNotFoundException {
        return albumRepository.save(album);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Album album = get(id);
        if (album == null) {
            throw new EntityNotFoundException("album with id=%d not found".formatted(id));
        }
        Set<Page> pages = album.getPagesAlbum();
        Photo pageCouverture = album.getPhotoCouverture();
        for (Page page : pages) {
            pageService.delete(page.getId());
        }
        photoService.delete(pageCouverture);
        albumRepository.delete(album);
    }

    public Album addPage(Long albumId, long pageId) {
        Album album = get(albumId);
        Page page = pageService.get(pageId);
        Set<Page> pages = album.getPagesAlbum();
        pages.add(page);
        update(album);
        return album;
    }
}