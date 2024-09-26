package com.dao;

import java.util.List;

import com.entity.Doctor;

public interface DoctorDao {

	public void addDoctor();

	public void viewDoctorDetail();

	public void updateDoctor();

	List<Doctor> getAllDoctors();

}