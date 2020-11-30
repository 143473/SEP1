/**
 * A class representing the AssignedTask of the employee with a first name, last name and country.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignedTasks extends Task{
    private double spentTime;
    private Mydate date;
    private AssignedEmployee assignedEmployee;

    public AssignedTasks(String name, String description, AssignedEmployee responsibleEmployee, AssignedEmployee assignedEmployee, Mydate date){
        super(name, description, responsibleEmployee);
        spentTime = 0;
        this.assignedEmployee = assignedEmployee;
        this.date = date;
    }
    public MyDate getDate(){
        return date.copy();
    }
    public AssignedEmployee getAssignedEmployee(){
        return assignedEmployee.copy();
    }
    public void setSpentTime(double spentTime){
        this.spentTime = spentTime;
    }
    public double getSpentTime() {
        return spentTime;
    }
}
