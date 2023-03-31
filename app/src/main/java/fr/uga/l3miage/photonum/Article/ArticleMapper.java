package fr.uga.l3miage.photonum.Article;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.data.domain.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO entityToDTO(Object entity);

    Collection<ArticleDTO> entityToDTO(Iterable<Article> article);

    Article dtoToEntity(ArticleDTO article);

    Collection<Article> dtoToEntity(Iterable<ArticleDTO> articles);
}
