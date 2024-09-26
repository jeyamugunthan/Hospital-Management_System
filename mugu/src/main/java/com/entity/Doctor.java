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
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 15, message = "First name should not exceed 15 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 15, message = "Last name should not exceed 15 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Specialty is mandatory")
    @Size(max = 20, message = "Specialty should not exceed 20 characters")
    @Column(name = "specialty")
    private String specialty;

    @NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    @Column(name = "contact_number")
    @Size(max = 12, message = "contactNumber should not exceed 12 characters")
    private String contactNumber;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    @Size(max = 15, message = "email should not exceed 15 characters")
    private String email;

    @NotBlank(message = "Office address is mandatory")
    @Size(max = 20, message = "Office address should not exceed 20 characters")
    @Column(name = "office_address")
    private String officeAddress;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
}
