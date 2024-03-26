package com.enigma.gymregistration.model.entity;

import com.enigma.gymregistration.constant.MemberStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "member_status")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;

    //relational
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<ClassRegistration> classList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
        return simpleGrantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
