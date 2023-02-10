package com.peaksoft.entity;

import com.peaksoft.enams.StudyFormat;
import lombok.*;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    private String email;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @Transient
    private Long groupId;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.REMOVE})
    private Group group;

}
