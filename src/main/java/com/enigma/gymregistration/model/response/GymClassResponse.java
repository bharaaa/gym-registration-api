package com.enigma.gymregistration.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GymClassResponse {
    private String className;
    private String date;
    private String startTime;
    private String endTime;
}
