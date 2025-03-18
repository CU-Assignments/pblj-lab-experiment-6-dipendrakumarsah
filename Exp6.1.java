To implement a Java program that sorts a list of Employee objects (based on name, age, and salary) using lambda expressions and stream operations to demonstrate efficient data processing.

//CODE

  import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    public void display() {
        System.out.println(name + " (" + age + ", " + salary + ")");
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, 50000),
            new Employee("Bob", 25, 60000),
            new Employee("Charlie", 35, 55000),
            new Employee("Alex", 28, 45000),
            new Employee("Alex", 32, 47000),
            new Employee("Alex", 25, 46000),
            new Employee("David", 29, 50000),
            new Employee("Eve", 31, 50000),
            new Employee("Frank", 27, 50000)
        );

        System.out.println("Sorting by Name:");
        employees.stream()
                 .sorted(Comparator.comparing(Employee::getName))
                 .forEach(Employee::display);

        System.out.println("\nSorting by Age:");
        employees.stream()
                 .sorted(Comparator.comparingInt(Employee::getAge))
                 .forEach(Employee::display);

        System.out.println("\nSorting by Salary:");
        employees.stream()
                 .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                 .forEach(Employee::display);
    }
}



Test Cases
Test Case         	Input Data                                                      	                    Expected Output
Case 1:       Sorting by Name	Alice (30, 50000), Bob (25, 60000), Charlie (35, 55000)	                    Alice, Bob, Charlie (sorted alphabetically)
Case 2:       Sorting by Age	Alice (30, 50000), Bob (25, 60000), Charlie (35, 55000)	                    Bob, Alice, Charlie (sorted by age in ascending order)
Case 3:       Sorting by Salary	Alice (30, 50000), Bob (25, 60000), Charlie (35, 55000)              	    Bob, Charlie, Alice (sorted by salary in descending order)
Case 4:       Edge Case - Same Name, Different Age	Alex (28, 45000), Alex (32, 47000), Alex (25, 46000)	Sorted by age (25 → 28 → 32)
Case 5:       Edge Case - Same Salary	David (29, 50000), Eve (31, 50000), Frank (27, 50000)	              Sorted by name if salary is the same
