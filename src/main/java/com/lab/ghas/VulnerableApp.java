package com.lab.ghas;

public class VulnerableApp {
    public static void main(String[] args) {

        boolean flag = true;
        
        // Equality test where one operand is a BooleanLiteral (true)
        if (flag == true) {  
            System.out.println("Boolean literal in equality test!");
        }

        boolean flagTrue = true;
        if (flagTrue) {
            System.out.println("No Boolean literal in equality test!");
        }

        int x = 10;

        if (x > 5) {
            // 🚨 Empty block - could indicate the missing logic
            // TODO: Add a calculation here
        }

        try {
            // 🚨 Empty try block - possible missing exception handling
        } catch (Exception e) {
            // Proper exception handling should be added here
        }

         processStudentData(
            101, "John", "Doe", 22, "Male", 
            "john.doe@example.com", "123-456-7890", "123 Main St, NY", "Class A",
            3.8, true
        );
    }
    
     // 🚨 Method with more than 10 parameters
     public static void processStudentData(
        int studentId, String firstName, String lastName, int age, 
        String gender, String email, String phoneNumber, String address, String className,
        double gpa, boolean isGraduated) {
        
        System.out.println("Processing student data...");
        System.out.println("ID: " + studentId);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("GPA: " + gpa);
        System.out.println("Graduated: " + (isGraduated ? "Yes" : "No"));
        System.out.println("Class: " + className);
    }

    public static void findStudent(int studentId, String firstName) {
        System.out.println("Finding student with ID: " + studentId);   
    }
}