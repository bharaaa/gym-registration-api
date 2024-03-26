package com.enigma.gymregistration.repository;

import com.enigma.gymregistration.model.entity.GymClass;
import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.GymClassRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface GymClassRepository extends JpaRepository<GymClass, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO t_gym_class (id, class_name, date, start_time, end_time) VALUES (?, ?, ?, ?, ?)", nativeQuery = true)
    void saveClass(String id, String className, Date date, LocalTime startTime, LocalTime endTime);

    @Query(value = "SELECT * FROM t_gym_class WHERE id = ?", nativeQuery = true)
    Optional<GymClass> findClassById(String id);

    @Query(value = "SELECT * FROM t_gym_class", nativeQuery = true)
    List<GymClass> findAllClass();

    @Transactional
    @Modifying
    @Query(value = "UPDATE t_gym_class SET class_name = ?, date = ?, start_time = ?, end_time = ? WHERE id = ?", nativeQuery = true)
    void updateClass(String className, String date, String startTime, String endTime, String id);

}
