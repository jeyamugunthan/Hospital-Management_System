package com.daoimpl;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.colour.Colour;
import com.dao.AppointmentDao;
import com.entity.Appointment;
import com.entity.Doctor;
import com.entity.Patient;
import com.helper.Demo;

public class AppointmentDaoImpl implements AppointmentDao {
	Colour col =new Colour();
	Session session = Demo.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	Scanner sc = new Scanner(System.in);
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();

	@Override
	public void addAppointment() {
		// Transaction t = null;
		try {

			// Get user input
			System.out.println(col.BOLD_PURPLE+"Enter your appointmentDate:");
			String appointmentDate = sc.next();

			System.out.println("Enter your appointmentTime:");
			String appointmentTime = sc.next();

			System.out.println("Enter your status:");
			String status = sc.next();

			System.out.println("Enter Patient ID:");
			int paitentID = sc.nextInt();
			Patient patient = session.get(Patient.class, paitentID);

			System.out.println("Enter Doctor ID:");
			int doctorID = sc.nextInt();
			Doctor doctor = session.get(Doctor.class, doctorID);
			
			// Create and set values for Appointment entity
			Appointment a = new Appointment();
			a.setAppointmentDate(appointmentDate);
			a.setAppointmentTime(appointmentTime);
			a.setPatient(patient);
			a.setDoctor(doctor);
			a.setStatus(status);
			// Validate the Appointment object
			Set<ConstraintViolation<Appointment>> vl = v.validate(a);
			t.commit();
			if (vl.isEmpty()) {
				// Save and commit if no validation errors
				session.save(a);

				System.out.println(col.BOLD_GREEN+"Appointment registered successfully.");
			} else {
				// Print validation errors
				for (ConstraintViolation<Appointment> violation : vl) {
					System.out.println(violation.getMessage());
				}
				t.rollback(); // Rollback transaction if validation fails
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewAppointmentDetail() {
		System.out.println(col.BOLD_PURPLE+"Enter your Appointment ID: ");

		// Read the Appointment ID from the input
		int i = sc.nextInt();

		// Fetch the Appointment object from the database using the provided ID
		Appointment a1 = session.get(Appointment.class, i);

		// Check if the Appointment object is not null
		if (a1 != null) {
			// Display the Appointment details
			System.out.println(
					"Appointment_id:"+a1.getId() + "\n " +"AppointmentDate:"+ a1.getAppointmentDate() + "\n " +"Appointment_time:"+ a1.getAppointmentTime() + "\n " + "Status:"+a1.getStatus());
		} else {
			// Display a message if no Appointment is found with the given ID
			System.out.println(col.BOLD_RED+"No Appointment found with ID: " + a1);
		}
	}

	@Override
	public void updateAppointment() {
		System.out.println(col.BOLD_PURPLE+"Enter Appointment id");
		int aid1 = sc.nextInt();

		Appointment d2 = session.get(Appointment.class, aid1);

		if (d2 != null) {
			System.out.println(col.BOLD_YELLOW+"What details you want to modify");
			System.out.println("1. appointmentDate ");
			System.out.println("2.  appointmentTime");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println(col.BOLD_GREEN+"Enter update appointmentDate");
				String nwappointmentDate = sc.next();

				d2.setAppointmentDate(nwappointmentDate);
				break;

			case 2:
				System.out.println("Enter update appointmentTime");
				String nwappointmentTime = sc.next();

				d2.setAppointmentTime(nwappointmentTime);
				break;

			default:
				System.out.println("invalid choice ");
				return;
			}
			session.update(d2);
			t.commit();
			System.out.println("details updated ");
		} else {
			System.out.println(col.BOLD_RED+"No such Appointment exists ");
		}

	}

	@Override
	public List<Appointment> getAllAppointments() {

		try {
			Query<Appointment> query = session.createQuery("FROM Appointment", Appointment.class);
			List<Appointment> appointment = query.list();

			if (appointment.isEmpty()) {
				System.out.println("No appointment found.");
			} else {
				for (Appointment a3 : appointment) {
					System.out.println(
							col.BOLD_CYAN+"Appointment_id:"+a3.getId() + "\n " +"AppointmentDate:"+ a3.getAppointmentDate() + "\n " +"Appointment_time:"+ a3.getAppointmentTime() + "\n " + "Status:"+a3.getStatus()+"\n");
				}
			}
			return appointment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
