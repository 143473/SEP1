import java.util.ArrayList;

public class AssignedTaskList {
    private ArrayList<AssignedTask> assignedTasks;

    public AssignedTaskList(){
        assignedTasks = new ArrayList<AssignedTask>();
    }
    public void addAssignedTask(AssignedTask assignedTask){
        assignedTasks.add(assignedTask);
    }
    public void removeAssignedTask(AssignedTask assignedTask){
        assignedTasks.remove(assignedTask);
    }
}
