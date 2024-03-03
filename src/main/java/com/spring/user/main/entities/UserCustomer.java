package com.spring.user.main.entities;

import jakarta.persistence.*;

@Entity
public class UserCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "isactive")
    private int isactive;
    @Column(name = "token")
    private String token;
}
