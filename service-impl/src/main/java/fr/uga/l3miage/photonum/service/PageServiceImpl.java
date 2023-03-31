package fr.uga.l3miage.photonum.service;

import java.util.Collection;
import java.util.Set;

import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.repo.PageRepository;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final PhotoService photoService;

    public PageServiceImpl(PageRepository pageRepository, PhotoService photoService) {
        this.pageRepository = pageRepository;
        this.photoService = photoService;
    }

    @Override
    public Page update(Page page) {
        return pageRepository.save(page);
    }

    @Override
    public Page save(Page page) {
        return pageRepository.save(page);
    }

    @Override
    public Page get(Long id) throws EntityNotFoundException {
        return pageRepository.get(id);
    }

    @Override
    public void delete(Page page) throws EntityNotFoundException {
        if (page == null) {
            throw new EntityNotFoundException("album not found");
        }
        pageRepository.delete(page);
    }

    public Page addPhoto(Long pageId, Long photoId) throws EntityNotFoundException {
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
