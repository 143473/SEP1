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
     * Adds an Employee to the list if he/she is not already there
     * @param employee the employee to add to the list
     */
    public void addEmployee(Employee employee){
        if(!employeeList.contains(employee)){
            employeeList.add(employee);
        }
    }

    /**
     * Removes an Employee from the list
     * @param employee the employee to remove from the list
     */
    public void removeEmployee(Employee employee){
        employeeList.remove(employee);
    }

    public int size(){
        return employeeList.size();
    }

    public Employee get(int index){
        return employeeList.get(index);
    }
}
