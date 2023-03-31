package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.repo.AdressePostaleRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdressePostaleServiceImpl implements AdressePostaleService {

    private final AdressePostaleRepository adressePostaleRepository;
    private final ClientService clientService;

    @Autowired
    public AdressePostaleServiceImpl(AdressePostaleRepository adressePostaleRepository, ClientService clientService) {
        this.adressePostaleRepository = adressePostaleRepository;
        this.clientService = clientService;
    }

    @Override
    public AdressePostale save(AdressePostale adr) {
        return adressePostaleRepository.save(adr);
    }

    @Override
    public AdressePostale save(Long clientId, AdressePostale adr) throws EntityNotFoundException {
        adressePostaleRepository.save(adr);
        bind(clientId, adr);
        return adr;
    }

    @Override
    public AdressePostale get(Long id) throws EntityNotFoundException {
        return adressePostaleRepository.get(id);
    }

    @Override
    public AdressePostale update(AdressePostale adr) throws EntityNotFoundException {
        return adressePostaleRepository.save(adr);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        AdressePostale adressePostale = get(id);
        if (adressePostale == null) {
            throw new EntityNotFoundException("l'adresse avec id=%d n'a pas été trouvée".formatted(id));
        }

        adressePostaleRepository.delete(adressePostale);

    }

    @Override
    public Collection<AdressePostale> list() {
        return adressePostaleRepository.all();
    }

    private void bind(Long id, AdressePostale adr) throws EntityNotFoundException {
        Client client = clientService.get(id);
        client.addAdresse(adr);
    }

}
