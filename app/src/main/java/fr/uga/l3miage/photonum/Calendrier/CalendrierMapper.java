package fr.uga.l3miage.photonum.Calendrier;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Calendrier;

@Mapper(componentModel = "spring")
public interface CalendrierMapper {
    CalendrierDTO entityToDTO (Calendrier calendrier);
    Collection<CalendrierDTO> entityToDTO(Iterable<Calendrier> calendrier);

    Calendrier dtoToEntity(CalendrierDTO calendrier);
    Collection<Calendrier> dtoToEntity(Iterable<CalendrierDTO> calendriers);
}
