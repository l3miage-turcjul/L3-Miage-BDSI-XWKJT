package fr.uga.l3miage.photonum.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.repo.CommandeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final ArticleService articleService;
    private final ClientService clientService;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository, ArticleService articleService,
            ClientService clientService) {
        this.commandeRepository = commandeRepository;
        this.articleService = articleService;
        this.clientService = clientService;
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
    public Commande save(Long id, Commande commande) throws EntityNotFoundException {
        commandeRepository.save(commande);
        bind(id, commande);
        return commande;
    }

    @Override
    public Commande get(Long id) throws EntityNotFoundException {
        return commandeRepository.get(id);
    }

    @Override
    public void delete(Commande commande) throws EntityNotFoundException {
        if (commande == null) {
            throw new EntityNotFoundException("album not found");
        }
        Set<Article> articles = commande.getArticles();
        for (Article article : articles) {
            articleService.delete(article);
        }
        commandeRepository.delete(commande);
    }

    public Commande addArticle(Long commandeId, Long articleId) throws EntityNotFoundException {
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

    private void bind(Long id, Commande commande) throws EntityNotFoundException {
        Client client = clientService.get(id);
        client.addCommande(commande);
    }

}
