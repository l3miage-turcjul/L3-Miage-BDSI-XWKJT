package fr.uga.l3miage.photonum.Article;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import fr.uga.l3miage.photonum.data.domain.Article;

import fr.uga.l3miage.photonum.service.ArticleService;
import fr.uga.l3miage.photonum.service.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;

@RestController
@Transactional
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleController(ArticleService articleService, ArticleMapper articleMapper) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/Article")
    public Collection<ArticleDTO> articles() {
        return articleMapper.entityToDTO(articleService.list());

    }

    @GetMapping("/Article/{id}")
    public ArticleDTO article(@PathVariable("id") @NotNull Long id) {
        try {
            return articleMapper.entityToDTO(articleService.get(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    @PostMapping("Commande/{id}/Article")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO newArticle(@PathVariable("id") Long commandeId, @RequestBody ArticleDTO article) {
        try {
            final var entity = articleService.save(commandeId, articleMapper.dtoToEntity(article));
            return articleMapper.entityToDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @PutMapping("/Article/{id}")
    public ArticleDTO updateArticle(@RequestBody ArticleDTO article, @PathVariable("id") Long id) {
        try {
            if (article.id().equals(id)) {
                // we have to get authors as book DTO does not have some
                // var storedAuthors = articleService.get(id).Authors();
                Article bookEntity = articleMapper.dtoToEntity(article);
                // bookEntity.setAuthors(storedAuthors);
                var updated = articleService.update(bookEntity);
                return articleMapper.entityToDTO(updated);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, null, e);
        }
    }

    @DeleteMapping("/Article/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable("id") Long id) {
        try {
            ArticleDTO articleDTO = this.article(id);
            articleService.delete(articleMapper.dtoToEntity(articleDTO));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
        }
    }

    /*
     * @GetMapping("/Article/{id}/impression")
     * public ImpressionDTO impression(@PathVariable("id") @NotNull Long id) {
     * try {
     * var article = articleService.get(id);
     * Impression impression = article.getImpression();
     * return impressionMapper.entityToDTO(pages);
     * } catch (EntityNotFoundException e) {
     * throw new ResponseStatusException(HttpStatus.NOT_FOUND, null, e);
     * }
     * }
     */
}
