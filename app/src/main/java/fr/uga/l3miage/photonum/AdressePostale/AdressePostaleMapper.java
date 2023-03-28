package fr.uga.l3miage.photonum.AdressePostale;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;

@Mapper(componentModel = "spring")
public interface AdressePostaleMapper {
    AdressePostaleDTO entityToDTO (AdressePostale adressePostale);
    Collection<AdressePostaleDTO> entityToDTO(Iterable<AdressePostale> adressePostale);

    AdressePostale dtoToEntity(AdressePostaleDTO adressePostale);
    Collection<AdressePostale> dtoToEntity(Iterable<AdressePostaleDTO> adressePostales);
}
