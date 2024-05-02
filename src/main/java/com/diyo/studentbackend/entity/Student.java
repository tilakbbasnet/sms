package com.diyo.studentbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.generator.Generator;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(sequenceName = "student_id_generator", name="student_generator")
    private Long sid;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private String email;
    private String phone;
    private String status;
    private String active;
    private String level;
}
