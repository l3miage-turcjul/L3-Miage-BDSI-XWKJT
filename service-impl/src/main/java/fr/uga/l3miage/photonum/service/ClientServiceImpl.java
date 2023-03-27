package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.data.repo.ClientRepository;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.service.base.BaseService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client save(Client cli) throws EntityNotFoundException {
        return clientRepository.save(cli);
    }

    @Override
    public Client get(Long id) throws EntityNotFoundException {
        return clientRepository.get(id);
    }

    @Override
    public Client update(Client cli) throws EntityNotFoundException {
        return clientRepository.save(cli);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException{
        Client cli = get(id);
        if (cli == null) {
            throw new EntityNotFoundException("le client avec id=%d n'a pas été trouvé".formatted(id));
        }
        SortedSet<Commande> commandes = cli.getCommandes();
        for (Commande commande : commandes) {
            commandeService.delete(commande.getId());
        }
        Set<Image> images = cli.getImages();
        for (Image image : images) {
            imageService.delete(image.getId());
        }
        Set<Impression> Impressions = cli.getImpressions();
        for (Impression impression : Impressions) {
            impressionService.delete(impression.getId());
        }
        clientRepository.delete(cli);


    }

    @Override
    public Collection<Client> list() {
        return clientRepository.all();
    }

}

