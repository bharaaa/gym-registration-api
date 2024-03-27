package com.enigma.gymregistration.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AddClassResponse {
    private String className;
    private String trainerId;
    private Date date;
    private LocalTime startTime;
    private LocalTime endTime;
}
