package fr.uga.l3miage.photonum.Page;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {
    PageDTO entityToDTO (Object entity);
    Collection<PageDTO> entityToDTO(Iterable<Page> page);

    static Page dtoToEntity(PageDTO page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dtoToEntity'");
    }
    Collection<Page> dtoToEntity(Iterable<PageDTO> pages);
}
