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
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private int id;

    @NotBlank(message = "Record date is mandatory")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Record date must be in the format YYYY-MM-DD")
    @Column(name = "record_date")
    @Size(max = 10, message = "recordDate should not exceed 10 characters")
    private String recordDate;

    @NotBlank(message = "Diagnosis is mandatory")
    @Size(max = 30, message = "Diagnosis should not exceed 30 characters")
    @Column(name = "diagnosis")
    private String diagnosis;

    @Size(max = 30, message = "Treatment should not exceed 30 characters")
    @Column(name = "treatment")
    private String treatment;

    @Size(max = 50, message = "Notes should not exceed 50 characters")
    @Column(name = "notes")
    private String notes;

    @NotNull(message = "Patient is mandatory")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
