package com.finanzas.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstname", nullable = false, length = 50)
    private String firstName;

    @Column(name="lastname", nullable = false, length = 50)
    private String lastName;

    @Column(name="email", nullable = false, length = 50)
    private String email;

    @Column(name="password", nullable = false, length = 50)
    private String password;

    @Column(name="phone", nullable = true, length = 20)
    private String phone;
}
