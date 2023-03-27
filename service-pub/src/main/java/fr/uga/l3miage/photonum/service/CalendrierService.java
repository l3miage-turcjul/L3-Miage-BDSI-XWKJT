package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.service.CalendrierService;
import fr.uga.l3miage.photonum.service.base.BaseService;


import java.util.Collection;


public interface CalendrierService extends BaseService<Calendrier,Long>{



    /**
     * Sauvegarde un objet calendrier
     *
     * @param le calendrier à sauvegarder
     * @return le calendrier avec un id
     */
    Calendrier save(Calendrier calendrier);

    /**
     * Trouve tous les calendriers
     * @return tous les calendriers existants
     */
    Collection<Calendrier> list();


    /**
     * Trouve le calendier d'id id
     * @param id
     * @return le calendrier avec l'id id 
     * @throws EntityNotFoundException
     */

    Calendrier getCalendarById(Long id) throws EntityNotFoundException;
    
    
    /**
     * Supprime un calendrier
     *
     * @param l'id du calendrier à supprimer
     * @throws EntityNotFoundException si le calendrier n'existe pas
     */
    void delete(Long id) throws EntityNotFoundException;

}

