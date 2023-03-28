package fr.uga.l3miage.photonum.Page;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {
    PageDTO entityToDTO (Page page);
    Collection<PageDTO> entityToDTO(Iterable<Page> page);

    Page dtoToEntity(PageDTO page);
    Collection<Page> dtoToEntity(Iterable<PageDTO> pages);
}
