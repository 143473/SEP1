/**
 * An abstract class containing the array of possible employee statuses
 * @author Marketa Lapcikova
 * @version 1.0
 */
public abstract class EmployeeStatus {
    private String[] statuses = new String[]{"Scrum Master", "Product Owner", "Project Creator", "Team Member"};
    private String chosenStatus;

    public String chooseStatus(int index){
        if(index < statuses.length){
            return statuses[index];
        }
        return null;
    }

}
