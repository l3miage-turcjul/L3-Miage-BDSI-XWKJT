package fr.uga.l3miage.photonum.service;

import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.data.repo.ImpressionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class ImpressionServiceImpl implements ImpressionService {

    private final ImpressionRepository impressionRepository;

    @Autowired
    public ImpressionServiceImpl(@Lazy ImpressionRepository impressionRepository) {
        this.impressionRepository = impressionRepository;
    }

    @Override
    public Impression get(Long id) throws EntityNotFoundException {
        return impressionRepository.get(id);
    }

    @Override
    public Collection<Impression> list() {
        return impressionRepository.all();
    }

    @Override
    public Impression update(Impression impression) throws EntityNotFoundException {
        return impressionRepository.save(impression);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        Impression impression = get(id);
        if (impression == null) {
            throw new EntityNotFoundException("impression with id=%d not found".formatted(id));
        }
        impressionRepository.delete(impression);
    }
}
