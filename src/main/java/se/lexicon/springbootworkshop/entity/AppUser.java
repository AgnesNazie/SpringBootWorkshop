package se.lexicon.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

//@Entity marks the class as a JPA entity (mapped a table)
@Entity
//@Getter generates getters for all fields
@Getter
//@Setter generates setters for all fields
@Setter
//@ToString generates a toString method that prints all fields
@ToString(exclude = "password")
//@EqualsAndHashCode generates an equals and hashcode method that compares all fields
@EqualsAndHashCode(exclude = "password")
//@NoArgsConstructor - lombok annotation to generate a no-args constructor
@NoArgsConstructor
//@AllArgsConstructor - lombok annotation to generate an all-args constructor
@AllArgsConstructor
//@Builder - lombok annotation to generate a builder
@Builder
public class AppUser {
    //@Id marks the field as a primary key
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) - auto increment id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Column marks the field as a column in the table (must be unique and not null)
    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    private String regDate;

    //@OneToOne marks the field as a one-to-one relationship
    @OneToOne()
    //@JoinColumn marks the field as a foreign key
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private Details userDetails;
}
