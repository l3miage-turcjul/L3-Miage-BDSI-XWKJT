package fr.uga.l3miage.photonum.Image;

import java.util.Collection;

import fr.uga.l3miage.photonum.Client.ClientDTO;
import fr.uga.l3miage.photonum.Page.PageDTO;
import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import jakarta.validation.constraints.Null;

public record ImageDTO (

    Long id,
    String cheminAcces,
    String info,
    float resolution,

    @Null
    ClientDTO proprietaire,
    
    boolean partage,

    @Null
    Collection<PhotoDTO> photos,

    @Null
    Collection<PageDTO> pagesCalendrier
)
{

  }
