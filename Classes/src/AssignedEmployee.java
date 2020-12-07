/**
 * A subclass of class Employee containg the status of the employee in given project
 * @author Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */

public class AssignedEmployee extends Employee{
    private EmployeeStatus status;

    /**
     * 4-argument constructor setting first and last name, date of birth and status of the employee
     * @param firstName first name(s) of the employee
     * @param lastName last name(s) of the employee
     * @param dateOfBirth date of birth of the employee
     * @param status the status of employee in this project
     */
    public AssignedEmployee(String firstName, String lastName, MyDate dateOfBirth, EmployeeStatus status){
        super(firstName, lastName, dateOfBirth);
        this.status = status;
    }

    /**
     * Gets the employee status of the employee
     * @return the status of the employee
     */
    public EmployeeStatus getEmployeeStatus() {
        return status;
    }

    /**
     * Sets the employee status of the employee
     * @param status the status of the employee in this project
     */
    public void setEmployeeStatus(EmployeeStatus status) {
        this.status = status;
    }

    /**
     * Copies the AssignedEmployee
     * @return the copy of this assigned employee
     */
    public AssignedEmployee copy(){
        return new AssignedEmployee(getFirstName(), getLastName(), getDateOfBirth(), status);
    }
}
