package fr.uga.l3miage.photonum.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.repo.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CommandeService commandeService;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, CommandeService commandeService)
            throws EntityNotFoundException {
        this.articleRepository = articleRepository;
        this.commandeService = commandeService;
    }

    @Override
    public Article save(Long id, Article article) throws EntityNotFoundException {
        articleRepository.save(article);
        bind(id, article);
        return article;
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

    private void bind(Long id, Article article) throws EntityNotFoundException {
        Commande commande = commandeService.get(id);
        commande.addArticle(article);
    }
}
