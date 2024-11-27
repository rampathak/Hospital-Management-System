package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Scanner;

public class Patient {
    Scanner scanner = new Scanner(System.in);

    private final Connection connection;
//rivate Scanner scanner;

    //public Patient(Connection connection, Scanner scanner){
    //this.connection=connection;
    //this.scanner=scanner;
//}

    public Patient(Connection connection) {
        this.connection = connection;
    }

    public void addPatient() {

        System.out.println("Enter patient name");
        String name = scanner.next();
        System.out.println("Enter patient age");
        int age = scanner.nextInt();
        System.out.println("Enter patient gender");
        String gender = scanner.next();


        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added sucessfully");
            } else {
                System.out.println("Failed to add Patient");
            }
        } catch (SQLException e) {
            System.err.println("Error adding patient: " + e.getMessage());
        }


    }

    public void viewPatients() {


        String query = "select * from patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("+........+...........+..........+................+");
            System.out.println("|PatientId|Name             |Age          |Gender       |");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.println(id + ", " + name + ", " + age + ", " + gender);

                System.out.println("+........+...........+..........+................+");
            }


        } catch (SQLException ex) {
            System.err.println("Error adding patient: " + ex.getMessage());
        }
    }

    public boolean getPatientById(int id) {
      //  System.out.println("Enter patient id");
        //int id = scanner.nextInt();
        String query = "select * from patients where id =?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
