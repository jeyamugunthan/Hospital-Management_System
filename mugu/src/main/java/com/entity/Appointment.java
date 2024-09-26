package com.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private int id;

    @NotBlank(message = "Appointment date is mandatory")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Appointment date must be in the format YYYY-MM-DD")
    @Column(name = "appointment_date")
    @Size(max = 10, message = "appointment_date should not exceed 10 characters")
    private String appointmentDate;

    @NotBlank(message = "Appointment time is mandatory")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "Appointment time must be in the format HH:MM")
    @Column(name = "appointment_time")
    @Size(max = 6, message = "appointmentTime should not exceed 6 characters")
    private String appointmentTime;

    @NotBlank(message = "Status is mandatory")
    @Size(max = 20, message = "Status should not exceed 20 characters")
    @Column(name = "status")
    private String status;

    @NotNull(message = "Patient is mandatory")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotNull(message = "Doctor is mandatory")
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

	
}
