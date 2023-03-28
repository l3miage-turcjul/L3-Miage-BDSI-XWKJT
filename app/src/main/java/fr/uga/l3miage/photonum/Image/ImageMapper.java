package fr.uga.l3miage.photonum.Image;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO entityToDTO (Image image);
    Collection<ImageDTO> entityToDTO(Iterable<Image> image);

    Image dtoToEntity(ImageDTO image);
    Collection<Image> dtoToEntity(Iterable<ImageDTO> images);
}
