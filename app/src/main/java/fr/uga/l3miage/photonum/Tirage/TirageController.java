package fr.uga.l3miage.photonum.Tirage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import fr.uga.l3miage.photonum.service.TirageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Transactional
@RequestMapping(value = "/api/v1", produces = "application/json")
public class TirageController {

    private final TirageService tirageService;
    private final TirageMapper tirageMapper;

    @Autowired
    public TirageController(TirageService tirageService, TirageMapper tirageMapper){
        this.tirageService = tirageService;
        this.tirageMapper = tirageMapper;
    }


    @GetMapping("/Tirage/{id}")
    public TirageDTO tirage(@PathVariable("id") @NotNull Long id) {
        try {
            return tirageMapper.entityToDTO(tirageService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Tirage")
    public Collection<TirageDTO> pages() {
        return tirageMapper.entityToDTO(tirageService.list());

    }

    @PostMapping("/Article/{id}/Tirage")
    @ResponseStatus(HttpStatus.CREATED)
    public TirageDTO newTirage(@PathVariable("id") @NotNull Long ImpressionId,
            @RequestBody @Valid TirageDTO tirage) {
        try {
            Tirage entity = tirageService.save(ImpressionId, tirageMapper.dtoToEntity(tirage));
            return tirageMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }


    @PutMapping("/Tirage/{id}")
    public TirageDTO updateTirage(@PathVariable("id") @NotNull Long id,
            @RequestBody @Valid TirageDTO tirage) {
        try {
            if (tirage.id().equals(id)) {
                Tirage tirageEntity = tirageMapper.dtoToEntity(tirage);
                var updated = tirageService.update(tirageEntity);
                return tirageMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Tirage/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTirage(@PathVariable("id") @NotNull Long id) throws Exception {
        try {
            tirageService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    
}
