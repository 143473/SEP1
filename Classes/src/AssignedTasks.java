/**
 * A class representing the AssignedTask of the employee with a first name, last name and country.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignedTasks extends Task{
    private double spentTime;
    private MyDate date;
    private AssignedEmployee assignedEmployee;

    /**
     * Three-argument constructor.
     * @param name the employee's first name
     * @param description the employee's last name
     * @param responsibleEmployee the responsible employee
     * @param assignedEmployee the assigned employee
     * @param date the date the task was worked on
     */
    public AssignedTasks(String name, String description, double estimatedTime, AssignedEmployee responsibleEmployee, AssignedEmployee assignedEmployee, MyDate date){
        super(name, description, estimatedTime, responsibleEmployee);
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
