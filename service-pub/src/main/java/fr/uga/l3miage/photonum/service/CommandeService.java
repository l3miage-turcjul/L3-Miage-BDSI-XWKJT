package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface CommandeService extends BaseService<Commande, Long> {

    public Commande save(Long id, Commande commande) throws EntityNotFoundException;

    public Commande get(Long id) throws EntityNotFoundException;

    public Commande update(Commande commande) throws EntityNotFoundException;

    public void delete(Commande commande) throws EntityNotFoundException;

    public Commande addArticle(Long commandeId, Long articleId) throws EntityNotFoundException;

    public Collection<Commande> list();

}
