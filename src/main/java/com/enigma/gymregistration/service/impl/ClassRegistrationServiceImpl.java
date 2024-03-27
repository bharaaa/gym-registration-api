package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.model.entity.ClassRegistration;
import com.enigma.gymregistration.model.entity.Trainer;
import com.enigma.gymregistration.model.request.ClassRegistrationRequest;
import com.enigma.gymregistration.model.response.ClassRegistrationResponse;
import com.enigma.gymregistration.model.response.GymClassResponse;
import com.enigma.gymregistration.model.response.UpdateRegistrationResponse;
import com.enigma.gymregistration.repository.ClassRegistrationRepository;
import com.enigma.gymregistration.service.ClassRegistrationService;
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
public class ClassRegistrationServiceImpl implements ClassRegistrationService {
    private final ClassRegistrationRepository classRegistrationRepository;

    @Override
    public UpdateRegistrationResponse registerClass(ClassRegistrationRequest request) {
        String dateString = request.getRegistrationDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        classRegistrationRepository.saveRegistration(
                UUID.randomUUID().toString(),
                request.getUserId(),
                request.getGymClassId(),
                date
        );

        return UpdateRegistrationResponse.builder()
                .userId(request.getUserId())
                .gymClassId(request.getGymClassId())
                .registrationDate(date)
                .build();
    }

    @Override
    public ClassRegistrationResponse findRegistrationById(String id) {
        Optional<ClassRegistration> optionalClassRegistration = classRegistrationRepository.findRegistrationById(id);

        if (optionalClassRegistration.isPresent()){
            ClassRegistration classRegistration = optionalClassRegistration.get();

            return ClassRegistrationResponse.builder()
                    .id(classRegistration.getId())
                    .userId(classRegistration.getUserId())
                    .gymClassId(classRegistration.getGymClassId())
                    .registrationDate(classRegistration.getRegistrationDate())
                    .build();
        }
        return null;
    }

    @Override
    public List<ClassRegistrationResponse> findAllRegistration() {
        return classRegistrationRepository.findAllRegistration().stream()
                .map(classRegistration -> ClassRegistrationResponse.builder()
                        .id(classRegistration.getId())
                        .userId(classRegistration.getUserId())
                        .gymClassId(classRegistration.getGymClassId())
                        .registrationDate(classRegistration.getRegistrationDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UpdateRegistrationResponse updateRegistration(ClassRegistrationRequest request) {
        ClassRegistrationResponse existingRegistration = findRegistrationById(request.getId());

        String dateString = request.getRegistrationDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        classRegistrationRepository.updateRegistration(
                request.getUserId(),
                request.getGymClassId(),
                date,
                existingRegistration.getId()
        );

        return UpdateRegistrationResponse.builder()
                .userId(request.getUserId())
                .gymClassId(request.getGymClassId())
                .registrationDate(date)
                .build();
    }
}
