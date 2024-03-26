package com.enigma.gymregistration.service.impl;

import com.enigma.gymregistration.model.entity.ClassRegistration;
import com.enigma.gymregistration.model.entity.GymClass;
import com.enigma.gymregistration.model.request.ClassRegistrationRequest;
import com.enigma.gymregistration.model.response.ClassRegistrationResponse;
import com.enigma.gymregistration.model.response.GymClassResponse;
import com.enigma.gymregistration.repository.ClassRegistrationRepository;
import com.enigma.gymregistration.service.ClassRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassRegistrationServiceImpl implements ClassRegistrationService {
    private final ClassRegistrationRepository classRegistrationRepository;

    @Override
    public ClassRegistrationResponse registerClass(ClassRegistrationRequest request) {
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

        return ClassRegistrationResponse.builder()
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
        return null;
    }

    @Override
    public ClassRegistrationResponse updateClass(ClassRegistrationResponse request) {
        return null;
    }
}
