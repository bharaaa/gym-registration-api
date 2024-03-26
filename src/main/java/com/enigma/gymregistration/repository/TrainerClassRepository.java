package com.enigma.gymregistration.repository;

public interface TrainerClassRepository {
    void assignTrainerToClass(String trainerId, String gymClassId);
}
