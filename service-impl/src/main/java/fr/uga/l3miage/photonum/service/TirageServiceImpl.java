package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Tirage;
import fr.uga.l3miage.photonum.data.repo.TirageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Transactional
public class TirageServiceImpl implements TirageService {
    private final TirageRepository tirageRepository;
    private final ArticleService articleService;

    @Autowired
    public TirageServiceImpl(TirageRepository tirageRepository, ArticleService articleService) {
        this.tirageRepository = tirageRepository;
        this.articleService = articleService;
    }

    @Override
    public Tirage save(Tirage tirage) {
        return tirageRepository.save(tirage);
    }

    @Override
    public Tirage save(Long id, Tirage tirage) throws EntityNotFoundException {
        tirageRepository.save(tirage);
        bind(id, tirage);
        return tirage;
    }

    @Override
    public Tirage get(Long id) throws EntityNotFoundException {
        return tirageRepository.get(id);
    }

    @Override
    public Collection<Tirage> list() {
        return tirageRepository.all();
    }

    @Override
    public Tirage update(Tirage tirage) throws EntityNotFoundException {
        return tirageRepository.save(tirage);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Tirage tirage = get(id);
        if (tirage == null) {
            throw new EntityNotFoundException("tirage with id=%d not found".formatted(id));
        }
        tirageRepository.delete(tirage);
    }

    public void bind(Long id, Tirage tirage) throws EntityNotFoundException {
        Article article = articleService.get(id);
        article.setImpression(tirage);
    }
}