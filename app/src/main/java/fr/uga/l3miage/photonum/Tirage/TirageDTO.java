package fr.uga.l3miage.photonum.Tirage;

import java.util.Collection;

import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import jakarta.validation.constraints.Null;

public record TirageDTO (
    @Null
    Collection<PhotoDTO> photosDeTirage
)
{}
