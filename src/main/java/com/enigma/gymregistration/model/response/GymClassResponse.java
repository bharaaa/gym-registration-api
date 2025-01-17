package com.enigma.gymregistration.model.response;

import com.enigma.gymregistration.model.entity.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GymClassResponse {
    private String id;
    private String className;
    private String trainerId;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
}
