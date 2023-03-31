package fr.uga.l3miage.photonum.Image;

import fr.uga.l3miage.photonum.service.ImageService;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ImageController {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @Autowired
    public ImageController(ImageService imageService, ImageMapper imageMapper){
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }


    @GetMapping("/Image/{id}")
    public ImageDTO image(@PathVariable("id") @NotNull Long id) {
        try {
            return imageMapper.entityToDTO(imageService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Image")
    public Collection<ImageDTO> images() {
        return imageMapper.entityToDTO(imageService.list());

    }

    @PostMapping("/Client/{id}/Image")
    @ResponseStatus(HttpStatus.CREATED)
    public ImageDTO newImage(@PathVariable("id") @NotNull Long clientId,
            @RequestBody @Valid ImageDTO image) {
        try {
            final var entity = ImageService.save(clientId, imageMapper.dtoToEntity(image));
            return imageMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Image/{id}")
    public ImageDTO updateImage(@PathVariable("id") @NotNull Long id,
            @RequestBody @Valid ImageDTO image) {
        try {
            if (image.id().equals(id)) {
                Image imageEntity = imageMapper.dtoToEntity(image);
                var updated = imageService.update(imageEntity);
                return imageMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Image/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteImage(@PathVariable("id") @NotNull Long id) throws Exception {
        try {
            imageService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }


    
}
