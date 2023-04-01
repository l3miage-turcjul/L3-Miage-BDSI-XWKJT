package fr.uga.l3miage.photonum.Article;

import fr.uga.l3miage.photonum.data.domain.Format;
import java.util.Collection;

import org.mapstruct.EnumMapping;
import org.mapstruct.*;

import fr.uga.l3miage.photonum.data.domain.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleDTO entityToDTO(Object entity);

    Collection<ArticleDTO> entityToDTO(Iterable<Article> article);

    Article dtoToEntity(ArticleDTO article);

    Collection<Article> dtoToEntity(Iterable<ArticleDTO> articles);

    @EnumMapping(nameTransformationStrategy = "stripPrefix",configuration = "_")
    String enumToString(Format format);

    @InheritInverseConfiguration
    @ValueMapping(source = MappingConstants.NULL, target = "_10X15MAT")
    @ValueMapping(source = "", target = "_10X15MAT")
    Format stringToEnum(String format);


}
