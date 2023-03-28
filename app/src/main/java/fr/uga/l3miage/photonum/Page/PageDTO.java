package fr.uga.l3miage.photonum.Page;

import java.util.Collection;

import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record PageDTO (
    Long id,
    String miseEnPage,

    @NotBlank
    String numeroPage,

    @Null
    Collection<PhotoDTO> photos
)
{}
