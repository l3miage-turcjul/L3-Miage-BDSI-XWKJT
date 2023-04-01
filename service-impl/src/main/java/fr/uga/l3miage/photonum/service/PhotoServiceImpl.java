package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.repo.PhotoRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ImageService imageService;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository, ImageService imageService) {
        this.photoRepository = photoRepository;
        this.imageService = imageService;
    }

    @Override
    public Photo save(Long id, Photo cli) throws EntityNotFoundException {
        photoRepository.save(cli);
        bind(id, cli);
        return cli;
    }

    @Override
    public Photo get(Long id) throws EntityNotFoundException {
        return photoRepository.get(id);
    }

    @Override
    public Photo update(Photo pho) throws EntityNotFoundException {
        return photoRepository.save(pho);
    }

    @Override
    public void delete(Photo photo) throws EntityNotFoundException {
        if (photo == null) {
            throw new EntityNotFoundException("la photo n'a pas été trouvé");
        }
        photoRepository.delete(photo);

    }

    @Override
    public Collection<Photo> list() {
        return photoRepository.all();
    }

    public void bind(Long id, Photo photo) throws EntityNotFoundException {
        Image image = imageService.get(id);
        image.addPhoto(photo);
    }
}
