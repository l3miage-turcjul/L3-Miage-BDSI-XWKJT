package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.repo.CadreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class CadreServiceImpl implements CadreService {

    private final CadreRepository cadreRepository;
    private final ArticleService articleService;

    @Autowired
    public CadreServiceImpl(@Lazy CadreRepository cadreRepository, @Lazy ArticleService articleService) {
        this.cadreRepository = cadreRepository;
        this.articleService = articleService;
    }

    @Override
    public Cadre save(Long id, Cadre cadre) throws EntityNotFoundException {
        cadreRepository.save(cadre);
        bind(id, cadre);
        return cadre;
    }

    @Override
    public Cadre get(Long id) throws EntityNotFoundException {
        return cadreRepository.get(id);
    }

    @Override
    public Collection<Cadre> list() {
        return cadreRepository.all();
    }

    @Override
    public Cadre update(Cadre cadre) throws EntityNotFoundException {
        return cadreRepository.save(cadre);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Cadre cadre = get(id);
        if (cadre == null) {
            throw new EntityNotFoundException("cadre with id=%d not found".formatted(id));
        }
        cadreRepository.delete(cadre);
    }

    private void bind(Long id, Cadre cadre) throws EntityNotFoundException {
        Article article = articleService.get(id);
        article.setImpression(cadre);
    }
}