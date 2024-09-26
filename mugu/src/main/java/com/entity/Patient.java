package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 15, message = "First name should not exceed 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 15, message = "Last name should not exceed 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Date of birth is mandatory")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date of birth must be in the format YYYY-MM-DD")
    @Column(name = "date_of_birth")
    @Size(max = 10, message = "dateOfBirth should not exceed 10 characters")
    private String dateOfBirth;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(name = "contact_number")
    @Size(max = 12, message = "contactNumber should not exceed 12 characters")
    private String contactNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    @Size(max = 25, message = "email should not exceed 25 characters")
    
    private String email;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 15, message = "Address should not exceed 15 characters")
    @Column(name = "address")
    private String address;

    @Size(max = 30, message = "Allergies should not exceed 30 characters")
    @Column(name = "allergies")
    private String allergies;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "patient")
    private Set<MedicalHistory> medicalHistories;

}
