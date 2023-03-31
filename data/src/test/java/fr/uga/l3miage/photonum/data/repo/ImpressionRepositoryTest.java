package fr.uga.l3miage.photonum.data.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;


class ImpressionRepositoryTest extends Base {
    @Autowired
    EntityManager entityManager;
    
    @Autowired
    ImpressionRepository impressionRepository;

    @Test
    void all() {

    }

}
