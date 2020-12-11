import java.io.Serializable;

/**
 * An abstract class containing the array of possible employee statuses
 * @author Marketa Lapcikova
 * @version 1.0
 */
public class EmployeeStatus implements Serializable {
    private String[] statuses = new String[]{"Scrum Master", "Product Owner", "Project Creator", "Team Member"};
    private String chosenStatus;
    private int defaultIndex = 3;

    /**
     * Chooses employee status from the array and returns it as String
     * @param index the index of wanted status in statuses String array
     * @return the status as a String
     */
    public String chooseStatus(int index){
        if(index < statuses.length){
            return statuses[index];
        }
        return null;
    }

    /**
     * Gets all the statuses
     * @return String array of all the statuses
     */
    public String [] getStatuses(){
        return statuses;
    }

    /**
     * Gets the default index
     * @return the default int defaultIndex
     */
    public int getDefaultIndex(){
        return defaultIndex;
    }
}
