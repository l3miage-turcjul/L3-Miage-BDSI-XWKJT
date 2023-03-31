package fr.uga.l3miage.photonum.Album;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import java.util.Collection;

import fr.uga.l3miage.photonum.Page.PageDTO;
import fr.uga.l3miage.photonum.Photo.PhotoDTO;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;;

public record AlbumDTO(
        Long id,

        @NotBlank String titre,

        @Null Collection<PageDTO> pagesAlbum,

        @Null PhotoDTO photoCouverture) {
}
