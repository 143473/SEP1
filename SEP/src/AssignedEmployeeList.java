//updated in class diagram
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing list of assignedEmployee objects
 * @author Marketa Lapcikova
 * @version 1.0
 */
public class AssignedEmployeeList implements Serializable {
  private ArrayList<AssignedEmployee> assignedEmployeeList;

  /**
   * No-argument constructor initializing the assignedEmployeeList
   */
  public AssignedEmployeeList(){
    assignedEmployeeList = new ArrayList<AssignedEmployee>();
  }

  /**
   * Adds an assignedEmployee to the list if he/she is not already there
   * @param assignedEmployee the assignedEmployee to add to the list
   */

  public void addAssignedEmployee(AssignedEmployee assignedEmployee){
    if(!containsEmployee(assignedEmployee)){
      assignedEmployeeList.add(assignedEmployee);
    }
  }

  /**
   * Checks if ArrayList of assignedEmployee objects contains specific employee
   * @param assignedEmployee the assignedEmployee we are looking for
   * @return true if the ArrayList does and false if does not contain the assignedEmployee object
   */
  public boolean containsEmployee(AssignedEmployee assignedEmployee){
    if(assignedEmployeeList.contains(assignedEmployee)){
      return true;
    }
    return false;
  }

  public boolean containsNormalEmployee(Employee employee){
    for (int i = 0; i < assignedEmployeeList.size(); i++) {
      if(assignedEmployeeList.get(i).getFirstName().equals(employee.getFirstName())
      && assignedEmployeeList.get(i).getLastName().equals(employee.getLastName())
      && assignedEmployeeList.get(i).getDateOfBirth().equals(employee.getDateOfBirth())){
        return true;
      }
    }
    return false;
  }

  public Employee getNormalEmployeeOnIndex(int index){
    if(index<assignedEmployeeList.size()){
      return new Employee(assignedEmployeeList.get(index).getFirstName(), assignedEmployeeList.get(index).getLastName(), assignedEmployeeList.get(index).getDateOfBirth());
    }
    return null;
  }

  /**
   * Removes an assignedEmployee from the list
   * @param  assignedEmployee the assignedEmployee to remove from the list
   */
  public void removeEmployee(AssignedEmployee assignedEmployee){
    assignedEmployeeList.remove(assignedEmployee);
  }

  /**
   * Gets the size of the assignedEmployeeList
   * @return int the size of the assignedEmployeeList
   */
  public int size(){
    return assignedEmployeeList.size();
  }

  /**
   * gets the assignedEmployee at this index
   * @param index the index of the assignedEmployee in the assignedEmployeeList
   * @return the assignedEmployee object of this index
   */
  public AssignedEmployee get(int index){
    if(index < assignedEmployeeList.size()){
      return assignedEmployeeList.get(index);
    }
    return null;
  }

  public String toString(){
    String returnStr = "";
    for (int i = 0; i < assignedEmployeeList.size(); i++) {
      if (i< assignedEmployeeList.size()-1)
        returnStr+= assignedEmployeeList.get(i)+"\n";
      else
        returnStr+= assignedEmployeeList.get(i);
    }
    return returnStr;
  }
  public boolean equals(Object obj){
    if(!(obj instanceof AssignedEmployeeList))
      return false;
    AssignedEmployeeList temp = (AssignedEmployeeList)obj;
    return assignedEmployeeList.equals(temp.assignedEmployeeList);
  }
}
