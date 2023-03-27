package fr.uga.l3miage.photonum.service;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.service.base.BaseService;

import java.util.Collection;
public interface TirageService extends BaseService<Tirage, Long> {
    Tirage save(Tirage tirage);
    void delete(Long id) throws EntityNotFoundException;
    Tirage get(Long id) throws EntityNotFoundException;
    Collection<Tirage> list();
    Tirage update(Tirage tirage) throws EntityNotFoundException;

}