# Hospital-Management-System
**Project Overview**
 Hospital Management System (HMS) is a Java-based application designed to manage various hospital operations. This system allows users to add patients, view patient and doctor details, and book appointments. The program uses JDBC for interacting with a relational database to store and retrieve patient and doctor data, as well as appointment information..

 
**Technologies Used**
Java: The core programming language used to build the application.
JDBC (Java Database Connectivity): For connecting to the database and executing SQL queries.
MySQL: A relational database to store patient, doctor, and appointment data.
Scanner: For reading user input from the console.


**Features**
Add Patient: Allows users to add a new patient to the system.
View Patients: Displays a list of all registered patients in the system.
View Doctors: Displays a list of available doctors in the system.
Book Appointment: Enables patients to book an appointment with a doctor on a specified date.


**Project Structure**
DatabaseConnection: Handles the connection to the database.
Patient: Manages patient-related operations such as adding new patients, viewing patients, and retrieving patient details by ID.
Doctor: Manages doctor-related operations such as viewing doctors and retrieving doctor details by ID.
HospitalMgmt: The main class that interacts with the user, providing a menu to perform operations and managing the flow of the application.


**Database Schema (MySQL)**
The system uses the following tables:
patients: Stores information about patients (ID, name, contact details, etc.).
doctors: Stores information about doctors (ID, name, specialization, etc.).
appointments: Stores information about appointments (patient ID, doctor ID, appointment date).





