package fr.uga.l3miage.photonum.service;

import java.util.Set;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Commande;

public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final ArticleService articleService;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Commande update(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public Commande get(Long id) throws EntityNotFoundException {
        return commandeRepository.get(id);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Commande commande = get(id);
        if (commande == null) {
            throw new EntityNotFoundException("album with id=%d not found".formatted(id));
        }
        Set<Article> articles = commande.getArticles();
        for (Article article : articles) {
            articleService.delete(article.getid());
        }
        commandeRepository.delete(commande.getId());
    }

    public Commande addArticle(Long commandeId, Long articleId) {
        Commande commande = get(commandeId);
        Set<Article> articles = commande.getArticles();
        Article article = articleService.get(articleId);
        articles.add(article);
        update(commande);
        return commande;
    }

    @Override
    public Collection<Commande> list() {
        return commandeRepository.all();
    }
}
