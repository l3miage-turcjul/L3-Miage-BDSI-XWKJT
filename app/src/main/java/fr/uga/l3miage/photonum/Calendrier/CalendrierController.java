package fr.uga.l3miage.photonum.Calendrier;

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

import fr.uga.l3miage.photonum.Page.PageMapper;
import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import fr.uga.l3miage.photonum.service.CadreService;
import fr.uga.l3miage.photonum.service.CalendrierService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

public class CalendrierController {

    private final CalendrierService calendrierService;
    private final CalendrierMapper calendrierMapper;
    private final PageMapper pageMapper;

    public CalendrierController(CalendrierService calendrierService, CalendrierMapper calendrierMapper,
            PageMapper pageMapper) {
        this.calendrierService = calendrierService;
        this.calendrierMapper = calendrierMapper;
        this.pageMapper = pageMapper;
    }

    @GetMapping("/Calendrier")
    public Collection<CalendrierDTO> calendrier() {
        return calendrierMapper.entityToDTO(calendrierService.list());
    }

    @GetMapping("/Calendrier/{id}")
    public CalendrierDTO calendrier(@PathVariable("id") @NotNull Long id) {
        try {
            return calendrierMapper.entityToDTO(calendrierService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @DeleteMapping("/Calendrier/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCadre(@PathVariable("id") Long id) {
        try {
            calendrierService.delete(id);
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
