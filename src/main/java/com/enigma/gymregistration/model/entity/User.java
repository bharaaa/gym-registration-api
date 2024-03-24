package com.enigma.gymregistration.model.entity;

import com.enigma.gymregistration.constant.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "member_status")
    private MemberStatus memberStatus;

    @OneToOne
    private Role roleId;

    //relational
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<ClassRegistration> classList;

}
