package fr.uga.l3miage.photonum.Tirage;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Tirage;

@Mapper(componentModel = "spring")
public interface TirageMapper {
    TirageDTO entityToDTO (Tirage tirage);
    Collection<TirageDTO> entityToDTO(Iterable<Tirage> tirages);

    Tirage dtoToEntity(TirageDTO tirage);
    Collection<Tirage> dtoToEntity(Iterable<TirageDTO> tirages);
}
