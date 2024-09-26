package com.healthcare.mugu;

import java.util.Scanner;

import com.colour.Colour;
import com.dao.MedicalHistoryDao;
import com.daoimpl.AdminDaoImpl;
import com.daoimpl.AppointmentDaoImpl;
import com.daoimpl.DoctorDaoImpl;
import com.daoimpl.MedicalHistoryDaoImpl;
import com.daoimpl.PatientDaoImpl;


public class App {
	public static void main(String[] args) {
		Colour col =new Colour();
		AdminDaoImpl adminDao = new AdminDaoImpl();
		PatientDaoImpl pimpl = new PatientDaoImpl();
		DoctorDaoImpl dimpl = new DoctorDaoImpl();
		AppointmentDaoImpl aimpl = new AppointmentDaoImpl();
		MedicalHistoryDaoImpl mimpl = new MedicalHistoryDaoImpl();
		Scanner sc = new Scanner(System.in);
		boolean loggedIn = false;

		// Admin Login
		while (!loggedIn) {
			System.out.print(col.BOLD_PURPLE +"Enter admin username: "+ col.RESET);
			String username = sc.next();

			System.out.print(col.BOLD_PURPLE +"Enter admin password: "+col.RESET);
			String password = sc.next();

			loggedIn = adminDao.login(username, password);

			if (!loggedIn) {
				System.out.println(col.BOLD_RED+"Invalid username or password. Please try again.");
			}
		}

		System.out.println(col.BOLD_PURPLE +"Login successful. Welcome!");

		try {
			char mainMenuChoice;
			do {
				System.out.println(col.BOLD_GREEN+"Main Menu");
				System.out.println(col.BOLD_YELLOW+"1. Patient");
				System.out.println(col.BOLD_YELLOW+"2. Doctor");
				System.out.println(col.BOLD_YELLOW+"3. Appointment");
				System.out.println(col.BOLD_YELLOW+"4. Medical History");
				System.out.println(col.BOLD_YELLOW+"5. Exit");
				System.out.print(col.BOLD_CYAN +"Enter choice: ");

				while (!sc.hasNextInt()) {
					System.out.println(col.BOLD_RED +"Invalid input. Please enter a number between 1 and 3.");
					sc.next(); // clear invalid input
				}

				int mainChoice = sc.nextInt();
				sc.nextLine(); // consume the newline left-over

				switch (mainChoice) {
				case 1:

					patientMenu(pimpl, sc);
					break;
				case 2:
					DoctorMenu(dimpl, sc);
					break;
				case 3:
					AppointmentMenu(aimpl, sc);
					break;
				case 4:
					MedicalHistoryMenu(mimpl, sc);
					break;

				case 5:
					System.out.println(col.BOLD_PURPLE+"Exiting... Thank you!");
					return;
				default:
					System.out.println(col.BOLD_PURPLE+"Incorrect choice entered. Please try again.");
					break;
				}
				System.out.print(col.BOLD_BLUE+"Do you want to return to the main menu? (Y/N): ");
				String input = sc.nextLine().trim();
				mainMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

			} while (mainMenuChoice == 'Y' || mainMenuChoice == 'y');

			System.out.println(col.BOLD_PURPLE+"Thank You..");

		} finally {
			sc.close(); // Ensure that the Scanner is closed
		}
	}

	// Patient Menu
	private static void patientMenu(PatientDaoImpl pimpl, Scanner sc) {
		char patientMenuChoice;
		do {
			Colour col =new Colour();
			System.out.println(col.BOLD_GREEN+"Patient System");
			System.out.println(col.BOLD_YELLOW+"1. Register Patient");
			System.out.println("2. View Patient by Id");
			System.out.println("3. Update Patient");
			System.out.println("4. View All placement Details");
			System.out.println("5. Back to Main Menu");
			System.out.print(col.BOLD_CYAN +"Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println(col.BOLD_RED +"Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				pimpl.registerPatient();
				break;
			case 2:
				pimpl.viewPatientDetail();
				break;
			case 3:
				pimpl.updatePatientDetail();
				break;
			case 4:
				pimpl.getAllPatient();
				break;

			case 5:
				return; // Back to main menu
			default:
				System.out.println(col.BOLD_PURPLE+"Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print(col.BOLD_PURPLE+"Do you want to continue in the Patient Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			patientMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (patientMenuChoice == 'Y' || patientMenuChoice == 'y');
	}

	// Doctor Menu
	private static void DoctorMenu(DoctorDaoImpl dimpl, Scanner sc) {
		char doctorMenuChoice;
		do {
			Colour col =new Colour();
			System.out.println(col.BOLD_GREEN+"Doctors System");
			System.out.println(col.BOLD_YELLOW+"1. Add Doctor");
			System.out.println("2. View Doctor by Id");
			System.out.println("3. Update Doctor Details");
			System.out.println("4. View All Doctor");
			System.out.println("5. Back to Main Menu");
			System.out.print(col.BOLD_CYAN +"Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println(col.BOLD_RED +"Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				dimpl.addDoctor();
				break;
			case 2:
				dimpl.viewDoctorDetail();
				break;
			case 3:
				dimpl.updateDoctor();
				break;
			case 4:
				dimpl.getAllDoctors();
				break;
			case 5:
				return; // Back to main menu
			default:
				System.out.println(col.BOLD_PURPLE+"Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print(col.BOLD_PURPLE+"Do you want to continue in the  Doctor Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			doctorMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (doctorMenuChoice == 'Y' || doctorMenuChoice == 'y');
	}

	// Appointment Menu
	private static void AppointmentMenu(AppointmentDaoImpl aimpl, Scanner sc) {
		char AppointmentMenuChoice;
		do {
			
			Colour col =new Colour();
			System.out.println(col.BOLD_GREEN +"Appointment System");
			System.out.println(col.BOLD_YELLOW+"1. Add Appointment");
			System.out.println("2. View Appointment by Id");
			System.out.println("3. Update Appointment Details");
			System.out.println("4. View All Appointment");
			System.out.println("5. Back to Main Menu");
			System.out.print(col.BOLD_CYAN+"Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println(col.BOLD_GREEN+"Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				aimpl.addAppointment();
				break;
			case 2:
				aimpl.viewAppointmentDetail();
				break;
			case 3:
				aimpl.updateAppointment();
				break;
			case 4:
				aimpl.getAllAppointments();
				break;
			case 5:
				return; // Back to main menu
			default:
				System.out.println(col.BOLD_RED+"Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print(col.BOLD_PURPLE+"Do you want to continue in the Appointment Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			AppointmentMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (AppointmentMenuChoice == 'Y' || AppointmentMenuChoice == 'y');
	}

	// MedicalHistory Menu
	private static void MedicalHistoryMenu(MedicalHistoryDaoImpl mimpl, Scanner sc) {
		char medicalHistoryChoice;
		do {
			Colour col =new Colour();
			System.out.println(col.BOLD_GREEN+" medical History System");
			System.out.println(col.BOLD_YELLOW+"1. Add  medical History");
			System.out.println("2. View  medical History by Id");
			System.out.println("3. Update  medical History Details");
			System.out.println("4. View All  medical History");
			System.out.println("5. Back to Main Menu");
			System.out.print(col.BOLD_YELLOW+"Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println(col.CYAN+"Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}

			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				mimpl.addMedicalHistory();
				break;
			case 2:
				mimpl.viewMedicalHistoryDetail();
				break;
			case 3:
				mimpl.updateMedicalHistory();
				
				break;
			case 4:
				mimpl.getAllMedicalHistories();
				break;
			case 5:
				return; // Back to main menu
			default:
				System.out.println(col.BOLD_RED+"Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print(col.BOLD_PURPLE+"Do you want to continue in the medicalHistory Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			medicalHistoryChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (medicalHistoryChoice == 'Y' || medicalHistoryChoice == 'y');
	}

}
