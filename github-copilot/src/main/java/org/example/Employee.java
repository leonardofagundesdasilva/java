package main.java.org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Employee {
    private String name;
    private int id;
    private String department;
    private double hourlySalary;

    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    public Employee(String name, int id, String department, double hourlySalary) {
        logger.info("Creating Employee: name={}, id={}, department={}, hourlySalary={}", name, id, department, hourlySalary);
        this.name = name;
        this.id = id;
        this.department = department;
        this.hourlySalary = hourlySalary;
    }

    public double getHourlySalary() {
        logger.info("Getting hourlySalary: {}", hourlySalary);
        return hourlySalary;
    }


    public void setHourlySalary(double hourlySalary) {
        logger.info("Setting hourlySalary from {} to {}", this.hourlySalary, hourlySalary);
        this.hourlySalary = hourlySalary;
    }

    public String getName() {
        logger.info("Getting name: {}", name);
        return name;
    }

    public void setName(String name) {
        logger.info("Setting name from {} to {}", this.name, name);
        this.name = name;
    }

    public int getId() {
        logger.info("Getting id: {}", id);
        return id;
    }

    public void setId(int id) {
        logger.info("Setting id from {} to {}", this.id, id);
        this.id = id;
    }

    public String getDepartment() {
        logger.info("Getting department: {}", department);
        return department;
    }

    public void setDepartment(String department) {
        logger.info("Setting department from {} to {}", this.department, department);
        this.department = department;
    }

    public double getAnnualSalary() {
        double annualSalary = hourlySalary * 40 * 52;
        logger.info("Calculating annualSalary: hourlySalary={}, annualSalary={}", hourlySalary, annualSalary);
        return annualSalary;
    }

    @Override
    public String toString() {
        logger.info("Converting Employee to string");
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", department='" + department + '\'' +
                '}';
    }
}
