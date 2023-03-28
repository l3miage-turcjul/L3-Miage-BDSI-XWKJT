package fr.uga.l3miage.photonum.Photo;

import fr.uga.l3miage.photonum.Image.ImageDTO;
import jakarta.validation.constraints.Null;

public record PhotoDTO (
    Long id,
    String parametresRetouche,
    String description,
    @Null
    ImageDTO image
)
{}
