package com.enigma.gymregistration.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_trainer")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "trainer_name")
    private String trainerName;

    @ManyToMany(mappedBy = "trainerId", fetch = FetchType.EAGER)
    private List<GymClass> gymClass;

}
