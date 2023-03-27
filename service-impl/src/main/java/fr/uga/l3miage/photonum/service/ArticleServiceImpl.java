package fr.uga.l3miage.photonum.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.repo.ArticleRepository;



public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) throws EntityNotFoundException{
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article){
        return articleRepository.save(article);
    }

    @Override
    public Article get(Long id) throws EntityNotFoundException {
        return articleRepository.get(id);
    }

    @Override
    public Collection<Article> list() {
        return articleRepository.all();
    }

    @Override
    public Article update(Article article) throws EntityNotFoundException {
        return articleRepository.save(article);
    }

	@Override
	public void delete(Article article) throws EntityNotFoundException {
        if (article == null) {
            throw new EntityNotFoundException("l'article n'a pas été trouvée");
        }

        articleRepository.delete(article);
	}
    
}
