/**
 * A subclass of class Employee containg the status of the employee in given project
 * @author Marketa Lapcikova, Timothy Johan Engkar
 * @version 1.0
 */

public class AssignedEmployee extends Employee{
    private String status;
    private int statusNumber;

    /**
     * 4-argument constructor setting first and last name, date of birth and status of the employee
     * @param firstName first name(s) of the employee
     * @param lastName last name(s) of the employee
     * @param dateOfBirth date of birth of the employee
     * @param statusNumber the status number of employee in this project
     */
    public AssignedEmployee(String firstName, String lastName, MyDate dateOfBirth, int statusNumber){
        super(firstName, lastName, dateOfBirth);
        EmployeeStatus employeeStatus = new EmployeeStatus();
        this.status = employeeStatus.chooseStatus(statusNumber);
        this.statusNumber = statusNumber;
    }

    /**
     * Sets status of the employee in case of the change
     * @param index the index of new employee status
     */
    public void setStatus(int index){
        EmployeeStatus employeeStatus = new EmployeeStatus();
        this.status = employeeStatus.chooseStatus(index);
    }

    /**
     * Gets the employee status of the employee
     * @return the status of the employee
     */
    public String getStatusString() {
        return status;
    }

    /**
     * Gets the employee status of the employee
     * @return the status of the employee
     */
    public int getStatusInt() {
        return statusNumber;
    }

    /**
     * Copies the AssignedEmployee
     * @return the copy of this assigned employee
     */
    public AssignedEmployee copy(){
        return new AssignedEmployee(getFirstName(), getLastName(), getDateOfBirth(), statusNumber);
    }
}
