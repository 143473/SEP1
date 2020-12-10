/**
 * An class containing the array of possible project statuses
 * @author Marketa Lapcikova
 * @version 1.0
 */

public class ProgressStatus {
  private String[] statuses = new String[]{ "Not started", "Started", "Ended", "Approved", "Rejected"};
  private String chosenStatus;
  private int defaultIndex = 0;

  /**
   * Chooses a status from the array by the index
   * @param index the index of the wanted status in the statuses array
   * @return String of the chosen status on given index
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