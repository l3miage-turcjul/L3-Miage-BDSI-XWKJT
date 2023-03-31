package fr.uga.l3miage.photonum.Album;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Album;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumDTO entityToDTO(Album album);

    Collection<AlbumDTO> entityToDTO(Iterable<Album> album);

    Album dtoToEntity(AlbumDTO album);

    Collection<Album> dtoToEntity(Iterable<AlbumDTO> albums);
}
