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
   * Adds an assignedEmployee to the list if they are not already there
   * @param assignedEmployee the assignedEmployee to add to the list
   */

  public void addAssignedEmployee(AssignedEmployee assignedEmployee){
    if(!containsEmployee(assignedEmployee)){
      assignedEmployeeList.add(assignedEmployee);
    }
  }

  /**
   * Checks if ArrayList of assignedEmployee objects contains specific employee of type AssignedEmployee
   * @param assignedEmployee the AssignedEmployee we are looking for
   * @return true if the ArrayList does and false if does not contain the AssignedEmployee object
   */
  public boolean containsEmployee(AssignedEmployee assignedEmployee){
    if(assignedEmployeeList.contains(assignedEmployee)){
      return true;
    }
    return false;
  }

  /**
   * Checks if ArrayList of assignedEmployee objects contains specific employee of type Employee
   * @param employee the Employee we are looking for
   * @return true if the ArrayList does and false if does not contain the Employee object
   */
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

  /**
   * Gets an Employee object that is in the AssignedEmployeeList on certain index
   * @param index the int index of the given employee
   * @return Employee type of the employee at the given index
   */
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
   * gets the AssignedEmployee at this index
   * @param index the index of the AssignedEmployee in the AssignedEmployeeList
   * @return the AssignedEmployee object of this index
   */
  public AssignedEmployee get(int index){
    if(index < assignedEmployeeList.size()){
      return assignedEmployeeList.get(index);
    }
    return null;
  }

  /**
   * Gets the assigned employee list information as String stating all the information about given assigned employee
   * @return String containing the information of the list of the AssignedEmployees
   */
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

  /**
   * Checks if the object is equal to another one
   * @param obj the object we are comparing this AssignedEmployeeList to
   * @return boolean true if those two are the same, false otherwise
   */
  public boolean equals(Object obj){
    if(!(obj instanceof AssignedEmployeeList))
      return false;
    AssignedEmployeeList temp = (AssignedEmployeeList)obj;
    return assignedEmployeeList.equals(temp.assignedEmployeeList);
  }
}
