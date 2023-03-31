package fr.uga.l3miage.photonum.Commande;

import fr.uga.l3miage.photonum.Client.ClientDTO;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.service.CommandeService;
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
public class CommandeController{

    private final CommandeService commandeService;
    private final CommandeMapper commandeMapper;

    @Autowired
    public CommandeController(CommandeService commandeService, CommandeMapper commandeMapper){
        this.commandeService = commandeService;
        this.commandeMapper = commandeMapper;
    }


    @GetMapping("/Commande/{id}")
    public CommandeDTO commande(@PathVariable("id") @NotNull Long id) {
        try {
            return commandeMapper.entityToDTO(commandeService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Commande")
    public Collection<CommandeDTO> commandes() {
        return commandeMapper.entityToDTO(commandeService.list());

    }

    @PostMapping("/Client/{id}/Commande")
    @ResponseStatus(HttpStatus.CREATED)
    public CommandeDTO newCommande(@PathVariable("id") @NotNull Long clientId,
            @RequestBody @Valid ClientDTO commande) {
        try {
            final var entity = clientService.save(clientId, commandeMapper.dtoToEntity(commande));
            return commandeMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Commande/{id}")
    public CommandeDTO updateCommande(@PathVariable("id") @NotNull Long id,
            @RequestBody @Valid CommandeDTO commande) {
        try {
            if (commande.id().equals(id)) {
                Commande commandeEntity = commandeMapper.dtoToEntity(commande);
                var updated = commandeService.update(commandeEntity);
                return commandeMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Commande/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommande(@PathVariable("id") @NotNull Long id) {
        try {
            commandeService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }



}
    

