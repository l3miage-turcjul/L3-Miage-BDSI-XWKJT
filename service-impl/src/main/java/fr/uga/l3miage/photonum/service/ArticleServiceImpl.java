package fr.uga.l3miage.photonum.service;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import fr.uga.l3miage.photonum.data.domain.Article;

public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;



    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article save(Article article) throws EntityNotFoundException {
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
	public void delete(Long id) throws EntityNotFoundException {
		Article article = get(id);
        if (article == null) {
            throw new EntityNotFoundException("l'article avec id=%d n'a pas été trouvée".formatted(id));
        }

        articleRepository.delete(article);
	}
    
}
