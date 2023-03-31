package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.repo.ImageRepository;
import fr.uga.l3miage.photonum.data.domain.Photo;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
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
    public void delete(Long id) throws Exception{
        Image im = get(id);
        if (im == null) {
            throw new EntityNotFoundException("l'image avec id=%d n'a pas été trouvée".formatted(id));
        }
        Set<Photo> photos = im.getPhotos();
        if (photos.size() > 0){
            throw new Exception("Il existe des photos de cette image, elle ne peut pas être supprimée");
        }
        imageRepository.delete(im);

    }

    @Override
    public Collection<Image> list() {
        return imageRepository.all();
    }

}

