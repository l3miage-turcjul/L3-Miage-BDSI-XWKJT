package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface AdressePostaleService extends BaseService<AdressePostale, Long> {

    AdressePostale save(AdressePostale adressePostale) throws EntityNotFoundException;

    AdressePostale save(Long clientId, AdressePostale adressePostale) throws EntityNotFoundException;

    AdressePostale get(Long id) throws EntityNotFoundException;

    AdressePostale update(AdressePostale adr) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    public Collection<AdressePostale> list();

    public Collection<AdressePostale> findByCountry(String pays);

}