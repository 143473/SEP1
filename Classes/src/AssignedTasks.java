/**
 * A class representing the AssignedTask of the employee with a first name, last name and country.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignedTasks extends Task{
    private double spentTime;
    private Mydate date;
    private AssignedEmployee assignedEmployee;

    /**
     * Three-argument constructor.
     * @param name the student's first name
     * @param description the student's last name
     * @param responsibleEmployee the student's country
     * @param assignedEmployee
     * @param date
     */
    public AssignedTasks(String name, String description, AssignedEmployee responsibleEmployee, AssignedEmployee assignedEmployee, Mydate date){
        super(name, description, responsibleEmployee);
        spentTime = 0;
        this.assignedEmployee = assignedEmployee;
        this.date = date;
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
    /**
     * Sets the employee's time spent.
     * @param spentTime what the employee's time spent is
     */
    public void setSpentTime(double spentTime){
        this.spentTime = spentTime;
    }
    /**
     * Gets the employee's time spent working.
     * @return the student's time spent working
     */
    public double getSpentTime() {
        return spentTime;
    }
}
