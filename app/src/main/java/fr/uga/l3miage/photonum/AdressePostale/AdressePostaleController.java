package fr.uga.l3miage.photonum.AdressePostale;

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

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.service.AdressePostaleService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class AdressePostaleController {

    private final AdressePostaleService adressePostaleService;
    private final AdressePostaleMapper adressePostaleMapper;

    @Autowired
    public AdressePostaleController(AdressePostaleService adressePostaleService,
            AdressePostaleMapper adressePostaleMapper) {
        this.adressePostaleService = adressePostaleService;
        this.adressePostaleMapper = adressePostaleMapper;
    }

    @GetMapping("/AdressePostale")
    public Collection<AdressePostaleDTO> adressePostales(@RequestParam(value = "p", required = false) String pays) {
        if (Strings.isBlank(pays)) {
            return adressePostaleMapper.entityToDTO(adressePostaleService.list());
        }
        return adressePostaleMapper.entityToDTO(adressePostaleService.findByCoundry(pays));
    }

    @GetMapping("/AdressePostale/{id}")
    public AdressePostaleDTO adressePostale(@PathVariable("id") @NotNull Long id) {
        try {
            return adressePostaleMapper.entityToDTO(adressePostaleService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("/Client/{id}/AdressePostale")
    @ResponseStatus(HttpStatus.CREATED)
    public AdressePostaleDTO newAdressePostale(@RequestBody @Valid AdressePostaleDTO adressePostale) {
        try {
            final var entity = adressePostaleService.save(adressePostaleMapper.dtoToEntity(adressePostale));
            return adressePostaleMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/AdressePostale/{id}")
    public AdressePostaleDTO updateAdressePostale(@PathVariable("id") @NotNull Long id,
            @RequestBody @Valid AdressePostaleDTO adressePostale) {
        try {
            if (adressePostale.id().equals(id)) {
                AdressePostale adressePostaleEntity = adressePostaleMapper.dtoToEntity(adressePostale);
                var updated = adressePostaleService.update(adressePostaleEntity);
                return adressePostaleMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/AdressePostale/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdressePostale(@PathVariable("id") @NotNull Long id) {
        try {
            adressePostaleService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

}
