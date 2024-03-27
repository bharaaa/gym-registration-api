package com.enigma.gymregistration.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GymClassRequest {
    private String id;
    private String className;
    private String trainerId;
    private String date;
    private String startTime;
    private String endTime;
}
