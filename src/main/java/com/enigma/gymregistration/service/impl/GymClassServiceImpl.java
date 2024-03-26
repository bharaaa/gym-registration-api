package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.constant.MemberStatus;
import com.enigma.gymregistration.model.entity.GymClass;
import com.enigma.gymregistration.model.entity.User;
import com.enigma.gymregistration.model.request.GymClassRequest;
import com.enigma.gymregistration.model.response.GymClassResponse;
import com.enigma.gymregistration.model.response.UserResponse;
import com.enigma.gymregistration.repository.GymClassRepository;
import com.enigma.gymregistration.service.GymClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymClassServiceImpl implements GymClassService {
    private final GymClassRepository gymClassRepository;

    @Override
    public GymClassResponse registerClass(GymClassRequest request) {
        String dateString = request.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GymClass gymClass = GymClass.builder()
                .className(request.getClassName())
                .date(date)
                .startTime(LocalTime.parse(request.getStartTime()))
                .endTime(LocalTime.parse(request.getEndTime()))
                .build();

        gymClassRepository.saveClass(
                gymClass.getId(),
                gymClass.getClassName(),
                String.valueOf(gymClass.getDate()),
                String.valueOf(gymClass.getStartTime()),
                String.valueOf(gymClass.getEndTime())
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
        Optional<GymClass> optionalGymClass = gymClassRepository.findClassById(id);

        if (optionalGymClass.isPresent()){
            GymClass gymClass = optionalGymClass.get();

            return GymClassResponse.builder()
                    .id(gymClass.getId())
                    .className(gymClass.getClassName())
                    .date(String.valueOf(gymClass.getDate()))
                    .startTime(String.valueOf(gymClass.getStartTime()))
                    .endTime(String.valueOf(gymClass.getEndTime()))
                    .build();
        }
        return null;
    }

    @Override
    public List<GymClassResponse> findAllClass() {
        return gymClassRepository.findAllClass().stream()
                .map(gymClass -> GymClassResponse.builder()
                        .id(gymClass.getId())
                        .className(gymClass.getClassName())
                        .date(String.valueOf(gymClass.getDate()))
                        .startTime(String.valueOf(gymClass.getStartTime()))
                        .endTime(String.valueOf(gymClass.getEndTime()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public GymClassResponse updateClass(GymClassRequest request) {
        GymClassResponse existingClass = findClassById(request.getId());

        gymClassRepository.updateClass(
                request.getClassName(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime(),
                existingClass.getId()
        );

        return GymClassResponse.builder()
                .id(existingClass.getId())
                .className(request.getClassName())
                .date(String.valueOf(request.getDate()))
                .startTime(String.valueOf(request.getStartTime()))
                .endTime(String.valueOf(request.getEndTime()))
                .build();
    }
}
