package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.repo.AlbumRepository;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final PageService pageService;
    private final PhotoService photoService;
    private final ArticleService articleService;

    @Autowired
    public AlbumServiceImpl(@Lazy AlbumRepository albumRepository, @Lazy PageService pageService, @Lazy PhotoService photoService,
            ArticleService articleService) {
        this.albumRepository = albumRepository;
        this.pageService = pageService;
        this.photoService = photoService;
        this.articleService = articleService;
    }

    public Album save(Long id, Album album) throws EntityNotFoundException {
        albumRepository.save(album);
        bind(id, album);
        return album;
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
    public void delete(Album album) throws EntityNotFoundException {
        if (album == null) {
            throw new EntityNotFoundException("album not found");
        }
        Set<Page> pages = album.getPagesAlbum();
        Photo photoCouverture = album.getPhotoCouverture();
        for (Page page : pages) {
            pageService.delete(page);
        }
        photoService.delete(photoCouverture);
        albumRepository.delete(album);
    }

    public Album addPage(Long albumId, Long pageId) throws EntityNotFoundException {
        Album album = get(albumId);
        Page page = pageService.get(pageId);
        Set<Page> pages = album.getPagesAlbum();
        pages.add(page);
        update(album);
        return album;
    }

    public Collection<Album> list() {
        return albumRepository.all();
    }

    private void bind(Long id, Album album) throws EntityNotFoundException {
        Article article = articleService.get(id);
        article.setImpression(album);
    }

    @Override
    public Collection<Album> findBytitle(String title) {
        return albumRepository.findByContainingTitle(title);
    }
}
