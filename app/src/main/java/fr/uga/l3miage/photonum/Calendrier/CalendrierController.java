package fr.uga.l3miage.photonum.Calendrier;

import java.util.Collection;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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

import fr.uga.l3miage.photonum.Page.PageDTO;
import fr.uga.l3miage.photonum.Page.PageMapper;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.service.CalendrierService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Transactional
@RequestMapping(value = "/api/v1", produces = "application/json")
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
    public Collection<CalendrierDTO> calendriers() {
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

    @PostMapping("/Article/{id}/Calendrier")
    @ResponseStatus(HttpStatus.CREATED)
    public CalendrierDTO newCalendrier(@RequestBody CalendrierDTO calendrier, @PathVariable("id") @NotNull Long calendrierId)
            throws EntityNotFoundException {
        try {
            Calendrier entity = calendrierService.save(calendrierId, calendrierMapper.dtoToEntity(calendrier));
            return calendrierMapper.entityToDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Calendrier/{id}")
    public CalendrierDTO updateCalendrier(@PathVariable("id") @NotNull Long id,
                                @RequestBody @Valid CalendrierDTO calendrier) {
        try {
            if (calendrierMapper.dtoToEntity(calendrier).getId().equals(id)) {
                Calendrier calendrierEntity = calendrierMapper.dtoToEntity(calendrier);
                var updated = calendrierService.update(calendrierEntity);
                return calendrierMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Calendrier/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCalendrier(@PathVariable("id") Long id) {
        try {
            calendrierService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Calendrier/{id}/Page")
    public Collection<PageDTO> calendrierPages(@PathVariable("id") @NotNull Long CalendrierId) {
        try {
            var Calendrier = calendrierService.get(CalendrierId);
            Set<Page> pages = Calendrier.getPagesCalendrier();
            return pageMapper.entityToDTO(pages);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }

    }
}
