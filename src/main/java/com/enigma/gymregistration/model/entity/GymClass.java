package com.enigma.gymregistration.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_gym_class")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "class_name")
    private String className;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_trainer_class",
            joinColumns = @JoinColumn(name = "gym_class_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    @JsonBackReference
    private List<Trainer> trainerId;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    //relational
    @OneToMany(mappedBy = "gymClassId", cascade = CascadeType.ALL)
    private List<ClassRegistration> classRegistrations;

}
