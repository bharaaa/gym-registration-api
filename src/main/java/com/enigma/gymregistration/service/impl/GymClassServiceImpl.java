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
import java.time.format.DateTimeFormatter;
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
    public GymClassResponse addClass(GymClassRequest request) {
        String dateString = request.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String startTimeString = request.getStartTime();
        LocalTime startTime = LocalTime.parse(startTimeString, formatter);
        String endTimeString = request.getEndTime();
        LocalTime endTime = LocalTime.parse(endTimeString, formatter);

        gymClassRepository.saveClass(
                UUID.randomUUID().toString(),
                request.getClassName(),
                date,
                startTime,
                endTime
                );

        return GymClassResponse.builder()
                .className(request.getClassName())
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
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
                    .date(gymClass.getDate())
                    .startTime(gymClass.getStartTime())
                    .endTime(gymClass.getEndTime())
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
                        .date(gymClass.getDate())
                        .startTime(gymClass.getStartTime())
                        .endTime(gymClass.getEndTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public GymClassResponse updateClass(GymClassRequest request) {
        GymClassResponse existingClass = findClassById(request.getId());

        String dateString = request.getDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String startTimeString = request.getStartTime();
        LocalTime startTime = LocalTime.parse(startTimeString, formatter);
        String endTimeString = request.getEndTime();
        LocalTime endTime = LocalTime.parse(endTimeString, formatter);

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
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
