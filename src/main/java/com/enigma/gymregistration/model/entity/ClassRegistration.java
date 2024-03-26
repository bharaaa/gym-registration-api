package com.enigma.gymregistration.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "t_class_registration")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ClassRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User userId;

    @ManyToOne
    @JoinColumn(name = "gym_class_id")
    @JsonBackReference
    private GymClass gymClassId;

    @Column(name = "registration_date")
    private Date registrationDate;

}
