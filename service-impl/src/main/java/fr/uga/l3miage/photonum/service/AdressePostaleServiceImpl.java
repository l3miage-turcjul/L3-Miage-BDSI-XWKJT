package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.data.repo.AdressePostaleRepository;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.service.base.BaseService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class AdressePostaleServiceImpl implements AdressePostaleService{

    private final AdressePostaleRepository adressePostaleRepository;

    @Autowired
    public AdressePostaleServiceImpl(AdressePostaleRepository adressePostaleRepository) {
        this.adressePostaleRepository = adressePostaleRepository;
    }


    @Override
    public AdressePostale save(AdressePostale adr) {
        return adressePostaleRepository.save(adr);
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
    public void delete(Long id) throws EntityNotFoundException{
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

}

