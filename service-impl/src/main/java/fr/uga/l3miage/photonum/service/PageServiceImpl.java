package fr.uga.l3miage.photonum.service;

import java.util.Collection;
import java.util.Set;

import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;

public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final PhotoService photoService;

    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public Page update(Page page) {
        return pageRepository.save(page);
    }

    @Override
    public Page save(Page Page) {
        return pageRepository.save(page);
    }

    @Override
    public Page get(Long id) throws EntityNotFoundException {
        return pageRepository.get(id);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Page page = get(id);
        if (page == null) {
            throw new EntityNotFoundException("album with id=%d not found".formatted(id));
        }
        pageRepository.delete(page.getId());
    }

    public Page addPhoto(Long pageId, Long photoId) {
        Page page = get(pageId);
        Set<Photo> photos = page.getPhotos();
        Photo photo = photoService.get(photoId);
        photos.add(photo);
        update(page);
        return page;
    }

    @Override
    public Collection<Page> list() {
        return pageRepository.all();
    }

}
