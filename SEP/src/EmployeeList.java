//updated in class diagram
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing list of Employee objects
 * @author Marketa Lapcikova
 * @version 1.0
 */
public class EmployeeList implements Serializable {
    private ArrayList<Employee> employeeList;

    /**
     * No-argument constructor initializing the EmployeeList
     */
    public EmployeeList(){
        employeeList = new ArrayList<Employee>();
    }

    /**
     * Adds an Employee to the list if they are not already there
     * @param employee the employee to add to the list
     */
    public void addEmployee(Employee employee){
        if(!containsEmployee(employee)){
            employeeList.add(employee);
        }
    }

    /**
     * Checks if ArrayList of Employee objects contains specific employee
     * @param employee the employee we are looking for
     * @return true if the ArrayList does and false if does not contain the Employee object
     */
    public boolean containsEmployee(Employee employee){
        if(employeeList.contains(employee)){
            return true;
        }
        return false;
    }

    /**
     * Removes an Employee from the list
     * @param employee the employee to remove from the list
     */
    public void removeEmployee(Employee employee){
        employeeList.remove(employee);
    }

    /**
     * Gets the size of the employeeList
     * @return int the size of the employeeList
     */
    public int size(){
        return employeeList.size();
    }

    /**
     * gets the Employee at this index
     * @param index the index of the Employee in the EmployeeList
     * @return the Employee object of this index
     */
    public Employee get(int index){
        if(index < employeeList.size()){
            return employeeList.get(index);
        }
        return null;
    }

    /**
     * Compares the EmployeeList with another object
     * @param obj the object we are comparing this EmployeeList to
     * @return true if the two objects are equal, false if otherwise
     */
    public boolean equals(Object obj){
        if(!(obj instanceof EmployeeList))
            return false;
        EmployeeList temp = (EmployeeList)obj;
        return employeeList.equals(temp.employeeList);
    }

    /**
     * Gets the employeeList information in the String format
     * @return String information of the employeeList stating information about all the employees in the employeeList
     */
    public String toString(){
        String returnStr = "";
        for (int i = 0; i < employeeList.size(); i++) {
            returnStr+= employeeList.get(i)+"\n";
        }
        return returnStr;
    }
}
