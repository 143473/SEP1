import java.util.ArrayList;

/**
 * A class containing a list of AssignedTask objects.
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignedTasksList {
    private ArrayList<AssignedTasks> assignedTasks;

    /**
     * No-argument constructor initializing the AssignedTasksList.
     */
    public AssignedTasksList(){
        assignedTasks = new ArrayList<AssignedTasks>();
    }
    /**
     * Adds an AssignedTask to the list.
     * @param assignedTasks the assignedTask to add to the list
     */
    public void addAssignedTask(AssignedTasks assignedTasks){
        this.assignedTasks.add(assignedTasks);
    }
    /**
     * Removes a Student to the list.
     * @param assignedTasks the assignedTask to remove to the list
     */
    public void removeAssignedTask(AssignedTasks assignedTasks){
        this.assignedTasks.remove(assignedTasks);
    }
}
