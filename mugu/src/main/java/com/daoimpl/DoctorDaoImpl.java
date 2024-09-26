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
import com.dao.DoctorDao;
import com.entity.Doctor;
import com.helper.Demo;

public class DoctorDaoImpl implements DoctorDao {
	Colour col =new Colour();
	Session session = Demo.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	Scanner sc = new Scanner(System.in);
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();

	@Override
	public void addDoctor() {
		try {
			// Get user input
			System.out.println(col.BOLD_PURPLE+"Enter your first name:");
			String firstName = sc.next();

			System.out.println("Enter your lastName:");
			String lastName = sc.next();

			System.out.println("Enter your specialty:");
			String specialty = sc.next();

			System.out.println("Enter your contactNumber:");
			String contactNumber = sc.next();

			System.out.println("Enter your email:");
			String email = sc.next();

			System.out.println("Enter your officeAddress:");
			String officeAddress = sc.next();

			// Create and set values for Doctor entity
			Doctor d = new Doctor();
			d.setFirstName(firstName);
			d.setLastName(lastName);
			d.setSpecialty(specialty);
			d.setContactNumber(contactNumber);
			d.setEmail(email);
			d.setOfficeAddress(officeAddress);
			// Validate the Doctor object
			Set<ConstraintViolation<Doctor>> vl = v.validate(d);
			if (vl.isEmpty()) {
				// Save and commit if no validation errors
				session.save(d);
				t.commit();
				System.out.println(col.BOLD_GREEN+"Doctor registered successfully.");
			} else {
				// Print validation errors
				for (ConstraintViolation<Doctor> violation : vl) {
					System.out.println(violation.getMessage());
				}
				t.rollback(); // Rollback transaction if validation fails
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewDoctorDetail() {
		System.out.println(col.BOLD_PURPLE+"Enter your Doctor ID: ");

		// Read the Doctor ID from the input
		int i = sc.nextInt();

		// Fetch the Doctor object from the database using the provided ID
		Doctor d1 = session.get(Doctor.class, i);

		// Check if the Doctor object is not null
		if (d1 != null) {
			// Display the Doctor details
			System.out.println( "doctor_id:"+d1.getId() + "\n" +
					"first_name:"+d1.getFirstName() + "\n" +
					"last_name:"+d1.getLastName() + "\n" +
					"specialty:"+  d1.getSpecialty() + "\n" +
					"contact_number:"+ d1.getContactNumber() + "\n" +
					"email:"+  d1.getEmail() + "\n" +
					"office_address:"+  d1.getOfficeAddress());

		} else {
			// Display a message if no Doctor is found with the given ID
			System.out.println(col.BOLD_RED+"No Doctor found with ID: " + d1);
		}
	}

	@Override
	public void updateDoctor() {

		System.out.println(col.BOLD_PURPLE+"Enter Doctor id");
		int did1 = sc.nextInt();

		Doctor d2 = session.get(Doctor.class, did1);

		if (d2 != null) {
			System.out.println(col.BOLD_YELLOW+"What details you want to modify");
			System.out.println("1. contactNumber ");
			System.out.println("2.  Email");
			System.out.println("3. officeAddress");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println(col.BOLD_CYAN+"Enter update contactNumber");
				String nwcontactNumber = sc.next();

				d2.setContactNumber(nwcontactNumber);
				break;

			case 2:
				System.out.println("Enter Email");
				String nwemail = sc.next();

				d2.setEmail(nwemail);
				break;

			case 3:
				System.out.println("Enter update officeAddress");
				String nwofficeAddress = sc.next();

				d2.setOfficeAddress(nwofficeAddress);
				break;

			default:
				System.out.println("invalid choice ");
				return;
			}
			session.update(d2);
			t.commit();
			System.out.println(col.BOLD_GREEN+"details updated ");
		} else {
			System.out.println(col.BOLD_RED+"No such Doctor exists ");
		}

	}

	@Override
	public List<Doctor> getAllDoctors() { 
		try {
		Query<Doctor> query = session.createQuery("FROM Doctor", Doctor.class);
		List<Doctor> doctor = query.list();

		if (doctor.isEmpty()) {
			System.out.println("No Doctor found.");
		} else {
			for (Doctor b1 : doctor) {
				System.out.println( "doctor_id:"+b1.getId() + "\n" +
						"first_name:"+b1.getFirstName() + "\n" +
						"last_name:"+b1.getLastName() + "\n" +
						"specialty:"+b1.getSpecialty() + "\n" +
						"contact_number:"+b1.getContactNumber() + "\n" +
						"email:"+b1.getEmail() + "\n" +
						"office_address:"+ b1.getOfficeAddress()+"\n");
			}
		}
		return doctor;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
}
