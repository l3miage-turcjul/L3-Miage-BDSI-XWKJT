package fr.uga.l3miage.photonum.Cadre;

import java.util.Collection;

import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record CadreDTO (
    @NotBlank
    String miseEnPage,

    @Null
    Collection<PhotoDTO> photosDeCadre
)
{}
