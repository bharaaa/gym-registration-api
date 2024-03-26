package com.enigma.gymregistration.repository;

import com.enigma.gymregistration.model.entity.ClassRegistration;
import com.enigma.gymregistration.model.entity.GymClass;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRegistrationRepository extends JpaRepository<ClassRegistration, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO t_class_registration (id, user_id, gym_class_id, registration_date) VALUES (?, ?, ?, ?)", nativeQuery = true)
    void saveRegistration(String id, String userId, String gymClassId, Date registrationDate);

    @Query(value = "SELECT * FROM t_class_registration WHERE id = ?", nativeQuery = true)
    Optional<ClassRegistration> findRegistrationById(String id);

    @Query(value = "SELECT * FROM t_class_registration", nativeQuery = true)
    List<ClassRegistration> findAllRegistration();

    @Transactional
    @Modifying
    @Query(value = "UPDATE t_class_registration SET user_id = ?, gym_class_id = ?, registration_date = ? WHERE id = ?", nativeQuery = true)
    void updateRegistration(String userId, String gymClassId, String registrationDate, String id);
}
