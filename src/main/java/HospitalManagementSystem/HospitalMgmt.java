package HospitalManagementSystem;

import HospitalManagementSystem.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class HospitalMgmt {
  public static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {



        try {
            Connection connection = DatabaseConnection.connectToDatabase();
            {
                Patient patient = new Patient(connection);
                Doctor doctor = new Doctor(connection);
               // patient.addPatient();
              //  patient.viewPatients();

                //boolean result=patient.getPatientById();
              //  System.out.println(result);

                while(true){


                    System.out.println("*********************************");

                    System.out.println("WELCOME TO HOSPITAL MANAGEMENT SYSTEM");
                    System.out.println("1. Add Patient");
                   // patient.
                    System.out.println("2. View Patient");
                    System.out.println("3.View Doctors");

                    System.out.println("4. Book Appointment");
                    System.out.println("5. Exit");

                    System.out.println("Enter the choice");
                    int choice =scanner.nextInt();
                    switch (choice){
                        case 1:
                            //add patient
                            patient.addPatient();
                            scanner.nextLine();
                            break;
                        case 2:
                            // view patient
                            patient.viewPatients();
                            scanner.nextLine();
                            break;
                        case 3:
                            // view doctors
                            doctor.viewDoctors();
                            scanner.nextLine();
                            break;
                        case 4:

                            bookAppointment(patient,doctor,connection);
                            scanner.nextLine();
                            break;
                            // Book Appointment


                        case 5:
                            return;
                        default:
                            System.out.println("Enter valid choice");
                            scanner.nextLine();
                    }
                }
           //    doctor.viewDoctors();
             //  boolean doctorresult= doctor.getDoctorById();
            //    System.out.println(doctorresult);



            }
            //Scanner scanner = new Scanner(System.in);


        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to database: " + e.getMessage());

        }
    }
    public static void bookAppointment(Patient patient, Doctor doctor,Connection connection) {
        System.out.println("Enter patient id");
        int patientid = scanner.nextInt();
        System.out.println("Enter Doctor id");
        int doctorid = scanner.nextInt();
        System.out.print("Enter appointment date(YYYY-MM-DD): ");
        String appointmentdate = scanner.next();
        if (patient.getPatientById(patientid) && doctor.getDoctorById(doctorid)) {

            if (checkDoctorAvailability(doctorid, appointmentdate, connection)) {
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientid);
                    preparedStatement.setInt(2, doctorid);
                    preparedStatement.setString(3, appointmentdate);
                    int rowsaffected = preparedStatement.executeUpdate();
                    System.out.println(rowsaffected);
                    if (rowsaffected > 0) {
                        System.out.println("Appointment Booked:");

                    } else {
                        System.out.println("Failed to Book Appointment");

                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }
        else {
            System.out.println("Either Doctor or Patient Does Not Exist");

        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate,Connection connection)
    {
        String query ="Select COUNT(*) from appointments where doctor_id=? AND appointment_date=?";
        try{
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        preparedStatement.setInt(1,doctorId);
        preparedStatement.setString(2,appointmentDate);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                int count=resultSet.getInt(1);
                if(count==0){
                    return true;

                }
                else{
                    System.out.println("Booking not avaiabable in this date");
                    return false;

                }

            }
    } catch (SQLException e){
            System.out.println(e.getMessage());

        }

        return false;

}
}




