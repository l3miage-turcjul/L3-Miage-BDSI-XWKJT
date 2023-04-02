package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.repo.ClientRepository;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Impression;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ImageService imageService;
    private final ImpressionService impressionService;
    private final CommandeService commandeService;

    @Autowired
    public ClientServiceImpl(@Lazy ClientRepository clientRepository, @Lazy ImageService imageService,
    @Lazy ImpressionService impressionService, @Lazy CommandeService commandeService) {
        this.clientRepository = clientRepository;
        this.imageService = imageService;
        this.impressionService = impressionService;
        this.commandeService = commandeService;
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
    public void delete(Long id) throws Exception {
        Client cli = get(id);
        if (cli == null) {
            throw new EntityNotFoundException("le client avec id=%d n'a pas été trouvé".formatted(id));
        }
        SortedSet<Commande> commandes = cli.getCommandes();
        for (Commande commande : commandes) {
            commandeService.delete(commande);
        }
        Set<Image> images = cli.getImages();
        for (Image image : images) {
            imageService.delete(image.getId());
        }
        Set<Impression> impressions = cli.getImpressions();
        for (Impression impression : impressions) {
            impressionService.delete(impression.getId());
        }
        clientRepository.delete(cli);

    }

    @Override
    public Collection<Client> list() {
        return clientRepository.all();
    }

}
