/**
 * An class containing the array of possible project statuses
 * @author Marketa Lapcikova
 * @version 1.0
 */

public class ProgressStatus {
  private String[] statuses = new String[]{ "Not started", "Started", "Ended", "Approved", "Rejected"};
  private String chosenStatus;

  private int defaultIndex = 0;

  public String chooseStatus(int index){
    if(index < statuses.length){
      return statuses[index];
    }
    return null;
  }

  public String [] getStatuses(){
    return statuses;
  }

  public int getDefaultIndex(){
    return defaultIndex;
  }

}