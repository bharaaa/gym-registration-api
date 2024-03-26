package com.enigma.gymregistration.repository.impl;

import com.enigma.gymregistration.repository.TrainerClassRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerClassRepositoryImpl implements TrainerClassRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void assignTrainerToClass(String trainerId, String gymClassId) {
        entityManager.createNativeQuery("INSERT INTO trainer_class (trainer_id, gym_class_id) VALUES (:trainerId, :gymClassId)")
                .setParameter("trainerId", trainerId)
                .setParameter("gymClassId", gymClassId)
                .executeUpdate();
    }
}
