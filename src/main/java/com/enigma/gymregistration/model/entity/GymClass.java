package com.enigma.gymregistration.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "m_gym_class")
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

    @ManyToMany
    @JoinTable(
            name = "t_trainer_class",
            joinColumns = @JoinColumn(name = "gym_class_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    private List<Trainer> trainerId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    //relational
    @OneToMany(mappedBy = "gymClassId", cascade = CascadeType.ALL)
    private List<ClassRegistration> classRegistrations;

}
