package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface ClientService extends BaseService<Client, Long> {

    Client save(Client cli) throws EntityNotFoundException;

    Client save(Long id, Client cli) throws EntityNotFoundException;

    Client get(Long id) throws EntityNotFoundException;

    Client update(Client cli) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException, Exception;

    public Collection<Client> list();

}