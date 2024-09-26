package com.dao;

import java.util.List;

import com.entity.Appointment;

public interface AppointmentDao {
	public void addAppointment();

	public void viewAppointmentDetail();

	public void updateAppointment();

	List<Appointment> getAllAppointments();

}