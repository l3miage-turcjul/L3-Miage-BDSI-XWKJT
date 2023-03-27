package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.base.BaseService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.ImageRepository = imageRepository;
    }


    @Override
    public Image save(Image im) throws EntityNotFoundException {
        return imageRepository.save(im);
    }

    @Override
    public Image get(Long id) throws EntityNotFoundException {
        return imageRepository.get(id);
    }

    @Override
    public Image update(Image im) throws EntityNotFoundException {
        return imageRepository.save(im);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException{
        Image im = get(id);
        if (im == null) {
            throw new EntityNotFoundException("l'image avec id=%d n'a pas été trouvée".formatted(id));
        }

        imageRepository.delete(im);

    }

    @Override
    public Collection<Image> list() {
        return imageRepository.all();
    }

}

