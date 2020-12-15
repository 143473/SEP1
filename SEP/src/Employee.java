import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing information about employee
 * @author Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */

public class Employee implements Serializable {
    private String firstName, lastName, status;
    private MyDate dateOfBirth;
    private ProjectList projects;
    private EmployeeList coworkers;
    private double hoursWorked, hoursExpected;
    private String productivity;

    public Employee(){
        firstName = "Jim";
        lastName = "Joe";
        dateOfBirth = new MyDate(1, 1, 1900);
        projects = new ProjectList();
        coworkers = new EmployeeList();
        hoursWorked = 0;
        hoursExpected = 0;
        productivity = "";
    }

    /**
     * 3-argument constructor initializing the Employee
     * @param firstName first name(s) of the employee
     * @param lastName last name(s) of the employee
     * @param dateOfBirth date of birth of the employee
     */
    public Employee(String firstName, String lastName, MyDate dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth.copy();
        projects = new ProjectList();
        coworkers = new EmployeeList();
        hoursWorked = 0;
        hoursExpected = 0;
        productivity = "";
    }

    /**
     * Gets the list of employees he/she ever worked with on the same project
     * @return ArrayList of type String of names of coworkers
     */
    public EmployeeList getCoworkers(){
        ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");
        return projectsAdapter.getCoworkersOfEmployee(this);
    }

    public void getCoworkersOfEmployee(){
        ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");
        coworkers = projectsAdapter.getCoworkersOfEmployee(this);
    }

    /**
     * Gets the ratio between the employees estimated and spent time on his/her tasks
     * @return number of type double being 1 if the estimated and spent time are equal
     */
    public void getProductivityOfEmployee(){
        if(hoursWorked != 0){
            double productivityRatio = hoursExpected/hoursWorked;
            productivity = Math.round(productivityRatio*100) + "%";
        }
        else{
            productivity = "";
        }

    }

    public String getProductivity(){
        if(hoursWorked != 0){
            double productivityRatio = hoursExpected/hoursWorked;
            return Math.round(productivityRatio*100) + "%";
        }
        return "";
    }

/*
    public double getHoursWorked(){
        AssignedTasksAdapter assignedTasksAdapter = new AssignedTasksAdapter("assignedTasks.bin");
        return assignedTasksAdapter.getTotalWorkedTimeOnEmployee(this);
    }

    public void getHoursWorkedOfEmployee(){
        AssignedTasksAdapter assignedTasksAdapter = new AssignedTasksAdapter("assignedTasks.bin");
        hoursWorked = assignedTasksAdapter.getTotalWorkedTimeOnEmployee(this);
    }
*/

    public double getHoursExpected(){
        AssignedTasksAdapter assignedTasksAdapter = new AssignedTasksAdapter("assignedTasks.bin");
        return assignedTasksAdapter.getTotalEstimatedTimeOnEmployee(this);
    }

    public void getHoursExpectedOfEmployee(){
        AssignedTasksAdapter assignedTasksAdapter = new AssignedTasksAdapter("assignedTasks.bin");
        hoursExpected = assignedTasksAdapter.getTotalEstimatedTimeOnEmployee(this);
    }

    /**
     * Gets the list of projects this employee ever worked on
     * @return ArrayList of type Project including the projects he/she worked on
     */

    public void getProjectsWorkedOn(){
        ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");
        projects = projectsAdapter.getProjectsOfEmployee(this);
    }


    public ProjectList getProjects(){
        ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");
        return projectsAdapter.getProjectsOfEmployee(this);
    }


    /**
     * Sets first name(s) of the employee in case of change
     * @param firstName first name(s) of the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the first name(s) of the employee
     * @return the first name(s) of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets last name(s) of the employee in case of change
     * @param lastName last name(s) of the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the last name(s) of the employee
     * @return the last name(s) of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the date of birth of the employee
     * @return MyDate date of birth of the employee
     */
    public MyDate getDateOfBirth() {
        return dateOfBirth.copy();
    }

    /**
     * Gets the day of birth of the employee
     * @return int day of birth of the employee
     */
    public int getDayOfBirth(){
        return dateOfBirth.getDay();
    }

    /**
     * Gets the month of birth of the employee
     * @return int month of birth of the employee
     */
    public int getMonthOfBirth(){
        return dateOfBirth.getMonth();
    }

    /**
     * Gets the year of birth of the employee
     * @return int year of birth of the employee
     */
    public int getYearOfBirth(){
        return dateOfBirth.getYear();
    }

    /**
     * Compares the Employee with another object
     * @param obj the object we are comparing this Employee to
     * @return true if the two objects are equal, false if otherwise
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)){
            return false;
        }
        Employee other = (Employee) obj;
        return firstName.equals(other.firstName) &&
                lastName.equals(other.lastName) &&
                dateOfBirth.equals(other.dateOfBirth);
    }

    /**
     * Gets the employee information in the String format
     * @return String information of the employee
     */
    public String toString(){
        return firstName+" "+lastName+" ("+getDayOfBirth()+"/"+getMonthOfBirth()+"/"+getYearOfBirth()+")";
    }
}
