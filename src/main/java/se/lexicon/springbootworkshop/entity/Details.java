package se.lexicon.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Entity marks the class as a JPA entity (mapped a table)
@Entity
//@Data - lombok annotation to generate getters, setters, equals, hashcode, toString etc.
@Data
//@NoArgsConstructor - lombok annotation to generate a no-args constructor
@NoArgsConstructor
//@AllArgsConstructor - lombok annotation to generate an all-args constructor
@AllArgsConstructor
//@Builder - lombok annotation to generate a builder
@Builder

public class Details {

    //@Id marks the field as a primary key
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) - auto increment id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Column marks the field as a column in the table (must be unique and not null)
    @Column(unique = true, nullable = false)
    private String email;

    private String name;

    private LocalDate birthDate;


}
