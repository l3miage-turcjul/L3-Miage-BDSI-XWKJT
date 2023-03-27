package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.service.base.BaseService;

public interface ArticleService extends BaseService<Article, Long>{
    
    Article save(Article article);

    void delete(Long id) throws EntityNotFoundException;

    
}
