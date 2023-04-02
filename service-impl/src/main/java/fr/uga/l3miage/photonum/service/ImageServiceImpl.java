package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.repo.ImageRepository;
import fr.uga.l3miage.photonum.data.domain.Photo;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ClientService clientService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ClientService clientService) {
        this.imageRepository = imageRepository;
        this.clientService = clientService;
    }

    @Override
    public Image save(Long id, Image im) throws EntityNotFoundException {
        imageRepository.save(im);
        bind(id, im);
        return im;
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
    public void delete(Long id) throws Exception {
        Image im = get(id);
        if (im == null) {
            throw new EntityNotFoundException("l'image avec id=%d n'a pas été trouvée".formatted(id));
        }
        Set<Photo> photos = im.getPhotos();
        if (photos.size() > 0) {
            throw new Exception("Il existe des photos de cette image, elle ne peut pas être supprimée");
        }
        imageRepository.delete(im);

    }

    @Override
    public Collection<Image> list() {
        return imageRepository.all();
    }

    @Override
    public void bind(Long id, Image image) throws EntityNotFoundException {
        Client client = clientService.get(id);
        client.addImage(image);
    }

    @Override
    public Collection<Image> listByClient(Long id) throws EntityNotFoundException {
        Client client = clientService.get(id);
        return client.getImages();
    }
}
