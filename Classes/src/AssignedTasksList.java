import java.util.ArrayList;

public class AssignedTasksList {
    private ArrayList<AssignedTasks> assignedTasks;

    public AssignedTasksList(){
        assignedTasks = new ArrayList<AssignedTasks>();
    }
    public void addAssignedTask(AssignedTasks assignedTasks){
        this.assignedTasks.add(assignedTasks);
    }
    public void removeAssignedTask(AssignedTasks assignedTasks){
        this.assignedTasks.remove(assignedTasks);
    }
}
