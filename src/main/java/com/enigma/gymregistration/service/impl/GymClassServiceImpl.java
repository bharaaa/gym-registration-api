package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.GymClassResponse;
import com.enigma.gymregistration.repository.GymClassRepository;
import com.enigma.gymregistration.service.GymClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GymClassServiceImpl implements GymClassService {
    private final GymClassRepository gymClassRepository;

    @Override
    public GymClassResponse registerClass(GymClassRequest request) {
        gymClassRepository.saveClass(
                UUID.randomUUID().toString(),
                request.getClassName(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime()
                );

        return GymClassResponse.builder()
                .className(request.getClassName())
                .date(request.getDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();
    }

    @Override
    public GymClassResponse findClassById(String id) {
        return null;
    }

    @Override
    public List<GymClassResponse> findAllClass() {
        return null;
    }

    @Override
    public GymClassResponse updateClass(GymClassRequest request) {
        return null;
    }
}
