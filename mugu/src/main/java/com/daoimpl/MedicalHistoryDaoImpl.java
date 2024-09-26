package com.daoimpl;

import java.util.InputMismatchException;
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
import com.dao.MedicalHistoryDao;
import com.entity.MedicalHistory;
import com.entity.Patient;
import com.helper.Demo;

public class MedicalHistoryDaoImpl implements MedicalHistoryDao {
	Colour col =new Colour();
	Session session = Demo.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	Scanner sc = new Scanner(System.in);
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();

	@Override
	public void addMedicalHistory() {
		try {
			System.out.println(col.BOLD_PURPLE+"Enter your recordDate:");
			String recordDate = sc.next();

			System.out.println("Enter your diagnosis:");
			String diagnosis = sc.next();

			System.out.println("Enter your treatment:");
			String treatment = sc.next();

			System.out.println("Enter your notes:");
			String notes = sc.next();

			System.out.println("Enter Patient ID:");
			int patientID = getIntInput(); // Safely get Patient ID
			Patient patient = session.get(Patient.class, patientID);

			if (patient == null) {
				System.out.println("No patient found with ID: " + patientID);
				return;
			}

			MedicalHistory m = new MedicalHistory();
			m.setRecordDate(recordDate);
			m.setDiagnosis(diagnosis);
			m.setTreatment(treatment);
			m.setNotes(notes);
			m.setPatient(patient);

			Set<ConstraintViolation<MedicalHistory>> vl = v.validate(m);
			if (vl.isEmpty()) {
				session.save(m);
				t.commit();
				System.out.println(col.BOLD_GREEN+"MedicalHistory registered successfully.");
			} else {
				for (ConstraintViolation<MedicalHistory> violation : vl) {
					System.out.println(violation.getMessage());
				}
				t.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		}
	}

	@Override
	public void viewMedicalHistoryDetail() {
		System.out.println(col.BOLD_PURPLE+"Enter your MedicalHistory ID: ");
		int id = getIntInput(); // Safely get MedicalHistory ID

		MedicalHistory m1 = session.get(MedicalHistory.class, id);
		if (m1 != null) {
			System.out.println( "History_id:"+m1.getId() + "\n " +"Record_date:"+ m1.getRecordDate() + "\n " +"Diagnosis:"+ m1.getDiagnosis() + "\n " +"Treatment:"+ m1.getTreatment()
					+ "\n " +"Notes:"+ m1.getNotes());
		} else {
			System.out.println(col.BOLD_RED+"No MedicalHistory found with ID: " + id);
		}
	}

	@Override
	public void updateMedicalHistory() {
		System.out.println(col.BOLD_PURPLE+"Enter MedicalHistory ID:");
		int did1 = getIntInput(); // Safely get MedicalHistory ID

		MedicalHistory m2 = session.get(MedicalHistory.class, did1);
		if (m2 != null) {
			System.out.println(col.BOLD_YELLOW+"What details do you want to modify?");
			System.out.println("1. Record Date");
			System.out.println("2. Notes");

			int choice = getIntInput(); // Safely get choice
			switch (choice) {
			case 1:
				System.out.println(col.BOLD_GREEN+"Enter new recordDate:");
				String nwRecordDate = sc.next();
				m2.setRecordDate(nwRecordDate);
				break;

			case 2:
				System.out.println("Enter new notes:");
				String nwNotes = sc.next();
				m2.setNotes(nwNotes);
				break;

			default:
				System.out.println(col.BOLD_RED+"Invalid choice.");
				return;
			}
			session.update(m2);
			t.commit();
			System.out.println(col.BOLD_GREEN+"Details updated.");
		} else {
			System.out.println(col.BOLD_RED+"No such MedicalHistory exists.");
		}
	}

	@Override
	public List<MedicalHistory> getAllMedicalHistories() {
		try {
			Query<MedicalHistory> query = session.createQuery("FROM MedicalHistory", MedicalHistory.class);
			List<MedicalHistory> medicalHistoryList = query.list();

			if (medicalHistoryList.isEmpty()) {
				System.out.println("No MedicalHistory found.");
			} else {
				for (MedicalHistory m4 : medicalHistoryList) {
					System.out.println(col.BOLD_CYAN+ "History_id:"+m4.getId() + "\n " +"Record_date:"+ m4.getRecordDate() + "\n " +"Diagnosis:"+ m4.getDiagnosis() + "\n " +"Treatment:"+ m4.getTreatment()
					+ "\n " +"Notes:"+ m4.getNotes()+"\n");
				}
			}
			return medicalHistoryList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private int getIntInput() {
		while (true) {
			try {
				return sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer.");
				sc.next(); // Clear the invalid input
			}
		}
	}
}
