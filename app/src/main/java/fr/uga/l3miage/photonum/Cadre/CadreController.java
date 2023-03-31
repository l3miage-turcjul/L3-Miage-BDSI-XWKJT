package fr.uga.l3miage.photonum.Cadre;

import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import fr.uga.l3miage.photonum.Photo.PhotoMapper;
import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.service.CadreService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

@RestController
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

    /*
     * @PostMapping("/Article/{id}/Cadre")
     * 
     * @ResponseStatus(HttpStatus.CREATED)
     * public CadreDTO newCadre(@PathVariable("id") Long articleId, @RequestBody
     * CadreDTO cadre) {
     * try {
     * Cadre cadreEntity = cadreService.save(articleId,
     * cadreMapper.dtoToEntity(cadre));
     * return cadreMapper.entityToDTO(cadreEntity);
     * } catch (EntityNotFoundException e) {
     * throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
     * } catch (IllegalArgumentException e) {
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
     * }
     * }
     */

    /*
     * @PutMapping("/Cadre/{id}")
     * public CadreDTO updateCadre(@RequestBody CadreDTO
     * cadreDTO, @PathVariable("id") Long id){
     * try {
     * if (cadreDTO.id().equals(id)) {
     * Cadre cadreEntity = cadreMapper.dtoToEntity(cadreDTO);
     * cadreEntity.setAuthors(storedAuthors);
     * var updated = bookService.update(bookEntity);
     * return booksMapper.entityToDTO(updated);
     * }
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
     * } catch (EntityNotFoundException e) {
     * throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
     * } catch (IllegalArgumentException e) {
     * throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
     * }
     */

    @DeleteMapping("/Cadre/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCadre(@PathVariable("id") Long id) {
        try {
            cadreService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Cadre/{id}/Photo")
    public Collection<PhotoDTO> photos(@PathVariable("id") @NotNull Long id) {
        try {
            var cadre = cadreService.get(id);
            List<Photo> photos = cadre.getPhotosDeCadre();
            return photoMapper.entityToDTO(photos);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

}
