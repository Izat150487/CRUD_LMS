package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    private String email;

    @Column(name = "last_name" )
    private String lastName;

    @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    private Course course;

    @Transient
    private Long courseId;
}
