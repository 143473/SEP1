/**
 * An class containing the array of possible project statuses
 * @author Marketa Lapcikova
 * @version 1.0
 */

public class ProgressStatus {
  private String[] statuses = new String[]{ "Not started", "Started", "Ended", "Approved", "Rejected"};
  private String chosenStatus;

  public String chooseStatus(int index){
    if(index < statuses.length){
      return statuses[index];
    }
    return null;
  }

}