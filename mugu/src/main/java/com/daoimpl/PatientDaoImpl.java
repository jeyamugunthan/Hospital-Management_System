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
import org.hibernate.jpa.HibernateHints;

import com.colour.Colour;
import com.dao.PatientDao;
import com.entity.Patient;
import com.helper.Demo;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class PatientDaoImpl implements PatientDao {
	Colour col =new Colour();
	Session session = Demo.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	Scanner sc = new Scanner(System.in);
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();

	public void registerPatient() {
		try {
			// Get user input
			System.out.println(col.BOLD_PURPLE+"Enter your first name:");
			String firstName = sc.next();

			System.out.println("Enter your last_name:");
			String lastName = sc.next();

			System.out.println("Enter your dateOfBirth:");
			String dateOfBirth = sc.next();

			System.out.println("Enter your contactNumber");
			String contactNumber = sc.next();

			System.out.println("Enter your email");
			String email = sc.next();

			System.out.println("Enter your address");
			String address = sc.next();

			System.out.println("Enter your allergies");
			String allergies = sc.next();

			// Create and set values for Patient entity
			Patient p = new Patient();
			p.setFirstName(firstName);
			p.setLastName(lastName);
			p.setDateOfBirth(dateOfBirth);
			p.setContactNumber(contactNumber);
			p.setEmail(email);
			p.setAddress(address);
			p.setAllergies(allergies);

			// Validate the Patient object
			Set<ConstraintViolation<Patient>> vl = v.validate(p);
			if (vl.isEmpty()) {
				// Save and commit if no validation errors
				session.save(p);
				t.commit();
				System.out.println(col.BOLD_GREEN+"Patient registered successfully.");
			} else {
				// Print validation errors
				for (ConstraintViolation<Patient> violation : vl) {
					System.out.println(violation.getMessage());
				}
				t.rollback(); // Rollback transaction if validation fails
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewPatientDetail() {
		System.out.println(col.BOLD_PURPLE+"Enter your Patient ID: ");

		// Read the Patient ID from the input
		int i = sc.nextInt();

		// Fetch the Patient object from the database using the provided ID
		Patient p1 = session.get(Patient.class, i);

		// Check if the Patient object is not null
		if (p1 != null) {
			// Display the Patient details
			System.out.println("patient_id:"+p1.getId() + "\n" +
					 "first_name:"+ p1.getFirstName() + "\n" +
					 "last_name:" +p1.getLastName() + "\n" +
					 "date_of_birth:"+  p1.getDateOfBirth() + "\n" +
					 "contact_number:" + p1.getContactNumber() + "\n" +
					 "email:"+ p1.getEmail() + "\n" +
					 "address:"+ p1.getAddress() + "\n" +
					 "allergies:"+ p1.getAllergies()+ "\n");

		} else {
			// Display a message if no Patient is found with the given ID
			System.out.println(col.BOLD_RED+"No Patient found with ID: " + p1);
		}
	}

	@Override
	public void updatePatientDetail() {

		System.out.println(col.BOLD_PURPLE+"Enter Patient id");
		int pid1 = sc.nextInt();

		Patient s2 = session.get(Patient.class, pid1);

		if (s2 != null) {
			System.out.println(col.BOLD_CYAN+"What details you want to modify");
			System.out.println("1. PhoneNumber ");
			System.out.println("2. Address");
			System.out.println("3. Email ID");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter update phone number");
				String nwcontactNumber = sc.next();

				s2.setContactNumber(nwcontactNumber);

				break;

			case 2:
				System.out.println("Enter update address");
				String nwaddress = sc.next();

				s2.setAddress(nwaddress);

				break;

			case 3:
				System.out.println("Enter update email id");
				String nwemail = sc.next();

				s2.setEmail(nwemail);

				break;

			default:
				System.out.println("invalid choice ");
				return;
			}
			session.update(s2);
			t.commit();
			System.out.println(col.BOLD_GREEN+"details updated ");
		} else {
			System.out.println("No such Patient exists ");
		}

	}

	@Override
	public List<Patient> getAllPatient() {
		try {
			TypedQuery<Patient> query = session.createQuery("FROM Patient", Patient.class);
			List<Patient> patients = query.getResultList();

			if (patients.isEmpty()) {
				System.out.println("No Patient found.");
			} else {
				for (Patient b1 : patients) {
					System.out.println(col.BOLD_PURPLE+"patient_id:"+b1.getId() + "\n" +
							 "first_name:"+ b1.getFirstName() + "\n" +
							 "last_name:" +b1.getLastName() + "\n" +
							 "date_of_birth:"+b1.getDateOfBirth() + "\n" +
							 "contact_number:" +b1.getContactNumber() + "\n" +
							 "email:"+b1.getEmail() + "\n" +
							 "address:"+b1.getAddress() + "\n" +
							 "allergies:"+b1.getAllergies()+"\n");
				}
			}
			return patients;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}