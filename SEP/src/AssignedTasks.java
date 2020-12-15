import java.io.Serializable;

/**
 * A class representing the AssignedTask of the employee with a first name, last name and country.
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
     * Three-argument constructor.
     * @param name the employee's first name
     * @param description the employee's last name
     * @param responsibleEmployee the responsible employee
     * @param assignedEmployee the assigned employee
     * @param date the date the task was worked on
     */
    public AssignedTasks(String name, String description, MyDate deadline, double estimatedTime, AssignedEmployee responsibleEmployee, AssignedEmployee assignedEmployee, MyDate date, int id, String status){
        super(name, description, estimatedTime, deadline, id, status, responsibleEmployee);
        spentTime = 0;
        this.assignedEmployee = assignedEmployee;
        this.date = date;
        this.project = null;
        this.requirement = null;
        /*this.task = null;*/
        reported = false;
    }

    /**
     * Gets the employee's date working on the task.
     * @return the date the task was worked on
     */
    public MyDate getDate(){
        return date.copy();
    }


    /**
     * Gets the employee assigned to the task.
     * @return the employee assigned to the task
     */

    public AssignedEmployee getAssignedEmployee(){
        return assignedEmployee.copy();
    }
    public boolean checkIfEmployeeIsAssigned(Employee employee){
        if(getAssignedEmployee().getFirstName().equals(employee.getFirstName())&&
            getAssignedEmployee().getLastName().equals(employee.getLastName())&&
                getAssignedEmployee().getDateOfBirth().equals(employee.getDateOfBirth()))
            return true;
        else
            return false;
    }

    @Override public String getName()
    {
        return super.getName();
    }

    public void setReported(boolean reported)
    {
        this.reported = reported;
    }
    public boolean getReported(){
        return this.reported;
    }

    @Override public void setStatus(String status)
    {
        super.setStatus(status);
    }

    /**
     * Sets the employee's time spent.
     * @param spentTime what the employee's time spent is
     */
    public void setSpentTime(double spentTime){
        this.spentTime += spentTime;
    }

    /**
     * Gets the employee's time spent working.
     * @return the student's time spent working
     */
    public double getSpentTime() {
        return spentTime;
    }

    public void setRequirement(Requirement requirement)
    {
        this.requirement = requirement;
    }

    public void setProject(Project project)
    {
        this.project = project;
    }

    public String getProjectName()
    {
        return project.getName();
    }

    public int getRequirementId()
    {
        return requirement.getId();
    }

    public Requirement getRequirement()
    {
        return requirement;
    }

    @Override public int getId()
    {
        return super.getId();
    }

    public String getStatus(){
        return super.getStatus();
    }

    /**
     * Compares time spent, date and assigned employee.
     * @param obj the object to compare with
     * @return true if the given object is equal to this student
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

    @Override public String toString()
    {
        return "AssignedTasks{" + "spentTime=" + spentTime + ", date=" + date
            + ", assignedEmployee=" + assignedEmployee + '}' + super.toString() +
            "requirementId== " + getRequirementId() + "projectName == " + getProjectName()+
            "taskId == " + getId() + getReported();
    }
}
