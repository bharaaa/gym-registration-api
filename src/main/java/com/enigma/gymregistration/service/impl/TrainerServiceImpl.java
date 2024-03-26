package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.model.entity.Trainer;
import com.enigma.gymregistration.model.request.TrainerRequest;
import com.enigma.gymregistration.model.response.TrainerResponse;
import com.enigma.gymregistration.repository.TrainerRepository;
import com.enigma.gymregistration.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;

    @Override
    public TrainerResponse findTrainerById(String id) {
        Optional<Trainer> optionalTrainer = trainerRepository.findTrainerById(id);

        if (optionalTrainer.isPresent()){
            Trainer trainer = optionalTrainer.get();

            return TrainerResponse.builder()
                    .id(trainer.getId())
                    .trainerName(trainer.getTrainerName())
                    .build();
        }
        return null;
    }

    @Override
    public List<TrainerResponse> findAllTrainer() {
        return trainerRepository.findAllTrainer().stream()
                .map(trainer -> TrainerResponse.builder()
                        .id(trainer.getId())
                        .trainerName(trainer.getTrainerName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public TrainerResponse updateTrainer(TrainerRequest request) {
        TrainerResponse existingTrainer = findTrainerById(request.getId());

        trainerRepository.updateTrainer(
                request.getTrainerName(),
                existingTrainer.getId()
        );

        return TrainerResponse.builder()
                .id(existingTrainer.getId())
                .trainerName(request.getTrainerName())
                .build();
    }
}
