package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.repo.PhotoRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo save(Photo cli) throws EntityNotFoundException {
        return photoRepository.save(cli);
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

}
