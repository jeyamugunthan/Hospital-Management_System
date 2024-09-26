package com.dao;

import java.util.List;

import com.entity.Patient;

public interface PatientDao {
	// register Patient details
	public void registerPatient();

	// fetch Patient detail
	public void viewPatientDetail();

	// edit Patient detail
	public void updatePatientDetail();

	List<Patient> getAllPatient();

}
