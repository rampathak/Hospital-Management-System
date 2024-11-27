package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

    Scanner scanner = new Scanner(System.in);

    private final Connection connection;
//rivate Scanner scanner;

    //public Patient(Connection connection, Scanner scanner){
    //this.connection=connection;
    //this.scanner=scanner;
//}

    public Doctor(Connection connection) {
        this.connection = connection;
    }
    public void viewDoctors() {


        String query = "select * from doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("+........+...........+..........+................+");
            System.out.println("|Doctorid     |Name             |specializatioin|");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specializatioin = resultSet.getString("specializatioin");
                System.out.println(id + ", "  +name+  ", "  +specializatioin);

                System.out.println("+........+...........+..........+");
            }


        } catch (SQLException ex) {
            System.err.println("Error adding patient: " + ex.getMessage());
        }
    }

    public boolean getDoctorById(int id) {

        String query = "select * from doctors where id =?";
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




