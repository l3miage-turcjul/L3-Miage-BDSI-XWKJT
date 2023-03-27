package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.service.base.BaseService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class PhotoServiceImpl implements PhotoService{

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
    public void delete(Long id) throws EntityNotFoundException{
        Photo pho = get(id);
        if (pho == null) {
            throw new EntityNotFoundException("la photo avec id=%d n'a pas été trouvé".formatted(id));
        }

        photoRepository.delete(pho);

    }

    @Override
    public Collection<Photo> list() {
        return photoRepository.all();
    }

}

