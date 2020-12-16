//updated in class diagram
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of AssignedTask objects
 * @author Timothy Engkar
 * @version 1.0
 */
public class AssignedTasksList implements Serializable {
    private ArrayList<AssignedTasks> assignedTasks;

    /**
     * No-argument constructor initializing the AssignedTasksList
     */
    public AssignedTasksList(){
        assignedTasks = new ArrayList<AssignedTasks>();
    }

    /**
     * Adds an AssignedTask to the list
     * @param assignedTasks the assignedTask to add to the list
     */
    public void addAssignedTask(AssignedTasks assignedTasks){
        this.assignedTasks.add(assignedTasks);
    }

    /**
     * Gets the AssignedTasks from this AssignedTasksList
     * @param assignedTask the assignedTask we want to get from the AssignedTaskList
     * @return AssignedTask from the AssignedTaskList that is equal to the one given or null if there is none
     */
    public AssignedTasks getAssignedTask(AssignedTasks assignedTask){
        for (int i = 0; i < assignedTasks.size(); i++) {
            if(assignedTasks.get(i).equals(assignedTask)){
                return assignedTasks.get(i);
            }
        }
        return null;
    }

    /**
     * Removes an AssignedTask from the list
     * @param assignedTasks the assignedTask to remove to the list
     */
    public void removeAssignedTask(AssignedTasks assignedTasks){
        this.assignedTasks.remove(assignedTasks);
    }

    /**
     * Gets how many AssignedTasks objects are in the list
     * @return the number of AssignedTasks objects in the list
     */
    public int size(){
        return assignedTasks.size();
    }

    /**
     * Gets a AssignedTask object position index from the list.
     * @param index the position n the list of the AssignedTask object
     * @return the AssignedTask object at position index if one exists, else null
     */
    public AssignedTasks get(int index){
        if(index < assignedTasks.size()){
            return assignedTasks.get(index);
        }
        return null;
    }
}
