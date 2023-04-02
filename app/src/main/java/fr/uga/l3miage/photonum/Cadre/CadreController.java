package fr.uga.l3miage.photonum.Cadre;

import java.util.Collection;
import java.util.List;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import fr.uga.l3miage.photonum.Photo.PhotoMapper;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.service.CadreService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

@RestController
@Transactional
@RequestMapping(value = "/api/v1", produces = "application/json")
public class CadreController {

    private final CadreService cadreService;
    private final CadreMapper cadreMapper;
    private final PhotoMapper photoMapper;

    public CadreController(CadreService cadreService, CadreMapper cadreMapper, PhotoMapper photoMapper) {
        this.cadreService = cadreService;
        this.cadreMapper = cadreMapper;
        this.photoMapper = photoMapper;
    }

    @GetMapping("/Cadre")
    public Collection<CadreDTO> cadres() {
        return cadreMapper.entityToDTO(cadreService.list());
    }

    @GetMapping("/Cadre/{id}")
    public CadreDTO cadre(@PathVariable("id") @NotNull Long id) {
        try {
            return cadreMapper.entityToDTO(cadreService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("/Article/{id}/Cadre")
    @ResponseStatus(HttpStatus.CREATED)
    public CadreDTO newCadre(@PathVariable("id") Long articleId, @RequestBody CadreDTO cadre) {
        try {
            Cadre cadreEntity = cadreService.save(articleId,
                    cadreMapper.dtoToEntity(cadre));
            return cadreMapper.entityToDTO(cadreEntity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Cadre/{id}")
    public CadreDTO updateCadre(@RequestBody CadreDTO cadreDTO, @PathVariable("id") Long id) {
        try {
            if (cadreDTO.id().equals(id)) {
                Cadre cadreEntity = cadreMapper.dtoToEntity(cadreDTO);
                var updated = cadreService.update(cadreEntity);
                return cadreMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Cadre/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCadre(@PathVariable("id") Long id) {
        try {
            cadreService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Cadre/{id}/Photos")
    public Collection<PhotoDTO> photos(@PathVariable("id") @NotNull Long id) {
        try {
            var cadre = cadreService.get(id);
            List<Photo> photos = cadre.getPhotosDeCadre();
            return photoMapper.entityToDTO(photos);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("/Cadre/{id}/Photos")
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoDTO newPhoto(@PathVariable("id") Long cadreId, @RequestBody PhotoDTO photo) {
        try {
            Photo photoEntity = photoMapper.dtoToEntity(photo);
            var cadre = cadreService.get(cadreId);
            cadre.addPhoto(photoEntity);
            cadreService.update(cadre);
            return photoMapper.entityToDTO(photoEntity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

}
