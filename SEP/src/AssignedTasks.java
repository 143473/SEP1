import java.io.Serializable;

/**
 * A class representing the AssignedTask of the employee with a first name, last name and country
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignedTasks extends Task implements Serializable {
    private double spentTime;
    private MyDate date;
    private AssignedEmployee assignedEmployee;
    private Project project;
    private Requirement requirement;
    private boolean reported;

    /**
     * 3-argument constructor
     * @param name the employee's first name
     * @param description the employee's last name
     * @param deadline deadline of the task
     * @param estimatedTime estimated time of the task
     * @param responsibleEmployee the responsible employee
     * @param assignedEmployee the assigned employee
     * @param date the date the task was worked on
     * @param id id of the task
     * @param status status of the task
     */
    public AssignedTasks(String name, String description, MyDate deadline, double estimatedTime, AssignedEmployee responsibleEmployee, AssignedEmployee assignedEmployee, MyDate date, int id, String status){
        super(name, description, estimatedTime, deadline, id, status, responsibleEmployee);
        spentTime = 0;
        this.assignedEmployee = assignedEmployee;
        this.date = date.copy();
        this.project = null;
        this.requirement = null;
        reported = false;
    }

    /**
     * Gets the employee's date working on the task
     * @return the date of type MyDate the task was assigned to
     */
    public MyDate getDate(){
        return date.copy();
    }


    /**
     * Gets the employee assigned to the task.
     * @return the employee assigned to the task
     */
    public AssignedEmployee getAssignedEmployee(){
        return assignedEmployee;
    }

    /**
     * Checks whether the Employee given is assigned this task
     * @param employee the Employee who is or is not assigned a task
     * @return boolean true if they are, false if they are not assigned any task
     */
    public boolean checkIfEmployeeIsAssigned(Employee employee){
        if(getAssignedEmployee().getFirstName().equals(employee.getFirstName())&&
            getAssignedEmployee().getLastName().equals(employee.getLastName())&&
                getAssignedEmployee().getDateOfBirth().equals(employee.getDateOfBirth()))
            return true;
        else
            return false;
    }

    /**
     * Sets the boolean parameter of the assigned task reported to false or true
     * @param reported boolean value whether the task is supposed to be set to reported or not
     */
    public void setReported(boolean reported)
    {
        this.reported = reported;
    }

    /**
     * Gets the boolean value whether this assigned task is reported or not
     * @return boolean true if the assigned task is reported, false otherwise
     */
    public boolean getReported(){
        return this.reported;
    }

    /**
     * Sets the employee's time spent
     * @param spentTime what the employee's time spent is
     */
    public void setSpentTime(double spentTime){
        this.spentTime += spentTime;
    }

    /**
     * Gets the employee's time spent working
     * @return the employee's time spent working
     */
    public double getSpentTime() {
        return spentTime;
    }

    /**
     * Sets the requirement of this assigned task of type Requirement to given value
     * @param requirement the requirement of the Assigned task of type Requirement
     */
    public void setRequirement(Requirement requirement)
    {
        this.requirement = requirement;
    }

    /**
     * Sets the project of this assigned task of type Project to given value
     * @param project the project of the Assigned task of type Project
     */
    public void setProject(Project project)
    {
        this.project = project;
    }

    /**
     * Gets the name of the project of the assigned task as a String
     * @return the name of the project of the assigned task as a String
     */
    public String getProjectName()
    {
        return project.getName();
    }

    /**
     * Gets the id of the requirement of the assigned task as an int
     * @return the id of the requirement of the assigned task as an int
     */
    public int getRequirementId()
    {
        return requirement.getId();
    }

    /**
     * Gets the  requirement of the assigned task as a Requirement
     * @return the requirement of the assigned task as a Requirement
     */
    public Requirement getRequirement()
    {
        return requirement;
    }
    /**
     * Gets the status of the superclass, Task
     * @return status of the Task of Assigned task as String
     */
    public String getStatus(){
        return super.getStatus();
    }

    /**
     * Compares time spent, date and assigned employee.
     * @param obj the object to compare with
     * @return true if the given object is equal to this AssignedTask
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof AssignedTasks)){
            return false;
        }
        AssignedTasks other = (AssignedTasks) obj;
        return super.equals(other) &&
                spentTime == other.spentTime &&
                date.equals(other.date) &&
                assignedEmployee.equals(other.assignedEmployee);
    }

    /**
     * Gets the information about this AssignedTask as a String
     * @return information about this AssignedTask as a String
     */

     public String toString()
    {
        return "AssignedTasks{" + "spentTime=" + spentTime + ", date=" + date
            + ", assignedEmployee=" + assignedEmployee + '}' + super.toString() +
            "requirementId== " + getRequirementId() + "projectName == " + getProjectName()+
            "taskId == " + getId() + getReported();
    }
}
