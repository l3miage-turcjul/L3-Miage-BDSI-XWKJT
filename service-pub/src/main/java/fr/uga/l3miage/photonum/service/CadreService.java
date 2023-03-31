package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.Collection;

public interface CadreService extends BaseService<Cadre, Long> {
    Cadre save(Cadre cadre);

    Cadre save(Long id, Cadre cadre) throws EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException;

    Cadre get(Long id) throws EntityNotFoundException;

    Collection<Cadre> list();

    Cadre update(Cadre cadre) throws EntityNotFoundException;

}