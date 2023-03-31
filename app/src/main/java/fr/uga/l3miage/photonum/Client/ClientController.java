package fr.uga.l3miage.photonum.Client;


import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.service.CadreService;
import fr.uga.l3miage.photonum.service.CalendrierService;
import fr.uga.l3miage.photonum.service.ClientService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ClientController {
    
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientController(ClientService clientService, ClientMapper clientMapper){
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PostMapping("/Client/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO newClient(@RequestBody ClientDTO client, @PathVariable("id") @NotNull Long clientId) throws EntityNotFoundException {
        try {
            Client entity = clientService.save(clientId, clientMapper.dtoToEntity(client));
            return clientMapper.entityToDTO(entity);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }



    @GetMapping("/Client/{id}")
    public ClientDTO client(@PathVariable("id") @NotNull Long id){
        try {
            return clientMapper.entityToDTO(clientService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @GetMapping("/Client")
    public Collection<ClientDTO> client() {
        return clientMapper.entityToDTO(clientService.list());
    }

    @DeleteMapping("/Client/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") Long id) throws Exception {
        try {
            clientService.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    
    @PutMapping("/Client/{id}")
    public ClientDTO updateClient(@PathVariable("id") @NotNull Long id,
            @RequestBody @Valid ClientDTO client) {
        try {
            if (client.id().equals(id)) {
                Client clientEntity = (Client) clientMapper.dtoToEntity(client);
                var updated = clientService.update(clientEntity);
                return clientMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }
    
    

}
