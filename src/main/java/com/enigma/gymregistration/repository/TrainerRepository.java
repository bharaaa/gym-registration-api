package com.enigma.gymregistration.repository;

import com.enigma.gymregistration.model.entity.Trainer;
import com.enigma.gymregistration.model.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, String> {
    @Modifying
    @Query(value = "INSERT INTO t_trainer (id, trainer_name) VALUES (?, ?)", nativeQuery = true)
    void saveTrainer(String id, String trainerName);

    @Query(value = "SELECT * FROM t_trainer WHERE id = ?", nativeQuery = true)
    Optional<Trainer> findTrainerById(String id);

    @Query(value = "SELECT * FROM t_trainer", nativeQuery = true)
    List<Trainer> findAllTrainer();

    @Transactional
    @Modifying
    @Query(value = "UPDATE t_trainer SET trainer_name = ? WHERE id = ?", nativeQuery = true)
    void updateTrainer(String trainerName, String id);

}
