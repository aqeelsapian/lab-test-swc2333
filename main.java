import java.io.*;
import java.util.*;

class Employee {
    String name;
    double baseSalary;
    int yearsOfService;

    Employee(String name, double baseSalary, int yearsOfService) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }

    double calculateAnnualSalary() {
        return baseSalary + (baseSalary * 0.05 * yearsOfService);
    }
}

public class main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        // Read data from file
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\aqeel\\Downloads\\employeeSalaries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double baseSalary = Double.parseDouble(parts[1]);
                int yearsOfService = Integer.parseInt(parts[2]);
                employees.add(new Employee(name, baseSalary, yearsOfService));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Store Employee Data
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\aqeel\\Downloads\\employeeData.txt"))) {
            // Calculate and display annual salary for each employee
            for (Employee employee : employees) {
                writer.println(employee.name + ": " + employee.calculateAnnualSalary());
            }

            // Identify and display top performing employee based on highest annual salary
            Employee topEmployee = Collections.max(employees, Comparator.comparing(Employee::calculateAnnualSalary));
            writer.println("Top performing employee: " + topEmployee.name);

            // Identify and display employee with least years of service
            Employee leastExperienceEmployee = Collections.min(employees, Comparator.comparing(employee -> employee.yearsOfService));
            writer.println("Employee with least years of service: " + leastExperienceEmployee.name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
