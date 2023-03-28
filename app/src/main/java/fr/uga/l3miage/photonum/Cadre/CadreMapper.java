package fr.uga.l3miage.photonum.Cadre;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Cadre;

@Mapper(componentModel = "spring")
public interface CadreMapper {
    CadreDTO entityToDTO (Cadre cadre);
    Collection<CadreDTO> entityToDTO(Iterable<Cadre> cadre);

    Cadre dtoToEntity(CadreDTO cadre);
    Collection<Cadre> dtoToEntity(Iterable<CadreDTO> cadres);
}
