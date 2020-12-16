//updated in class diagram
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the employees file, making it easy to retrieve and store information.
 * @author Claudiu Cordunianu, Marketa Lapcikova
 * @version 1.0
 */

public class EmployeeAdapter
{
  private MyFileIO mfio;
  private String fileName;

  /**
   * 1-argument constructor setting the file name
   * @param fileName the name and path of the file where employees will be saved and retrieved
   */
  public EmployeeAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  /**
   * Uses the MyFileIO class to retrieve an EmployeesList object with all employees
   * @return a EmployeesList object with all stored employees.
   * @throws FileNotFoundException exception thrown when the file is not found
   * @throws IOException exception thrown when there is problem with input or output
   * @throws ClassNotFoundException exception thrown when the class is not found
   */
  public EmployeeList getAllEmployees()
  {
    EmployeeList employees = new EmployeeList();

    try
    {
      employees = (EmployeeList)mfio.readObjectFromFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return employees;
  }

  /**
   * Use the MyFileIO class to save some employees.
   * @param employees the list of employees that will be saved
   * @throws FileNotFoundException exception thrown when the file is not found
   * @throws IOException exception thrown when there is problem with input or output
   */
  public void saveEmployees(EmployeeList employees)
  {
    try
    {
      mfio.writeToFile(fileName, employees);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**
   * Gets the EmployeeList of employees whose name contain the String searchingFor
   * @param searchingFor String of characters we need to look for in the names of employees
   * @return EmployeeList of employees whose name contains the String searchingFor
   */
  public EmployeeList getEmployeesByName(String searchingFor, EmployeeList employeeList){
    EmployeeList allEmployees = employeeList;
    EmployeeList resultEmployees = new EmployeeList();

    searchingFor = searchingFor.toLowerCase();
    for (int i = 0; i < allEmployees.size(); i++) {
      String fullName = (allEmployees.get(i).getFirstName()+" "+allEmployees.get(i).getLastName()).toLowerCase();
      if(fullName.contains(searchingFor)){
        resultEmployees.addEmployee(allEmployees.get(i));
      }
    }
    return resultEmployees;
  }

  /**
   * Gets the EmployeeList of all employees whose first or last name contains the String in the parameter converted to small letters
   * @param searchingFor the String that the method is looking for in the name
   * @return EmployeeList of all employees with this String included in their name
   */
  public EmployeeList getEmployeesByName(String searchingFor){
    EmployeeList allEmployees = getAllEmployees();
    EmployeeList resultEmployees = new EmployeeList();

    searchingFor = searchingFor.toLowerCase();
    for (int i = 0; i < allEmployees.size(); i++) {
      String fullName = (allEmployees.get(i).getFirstName()+" "+allEmployees.get(i).getLastName()).toLowerCase();
      if(fullName.contains(searchingFor)){
        resultEmployees.addEmployee(allEmployees.get(i));
      }
    }
    return resultEmployees;
  }

  /**
   * Saves the changed information about the employee
   * @param firstName first name of the employee as String
   * @param lastName last name of the employee as String
   * @param birthday birthday of the employee as MyDate
   * @param indexInList the employee's index in the EmployeeList
   */
  public void saveChangedEmployee(String firstName, String lastName, MyDate birthday, int indexInList)
  {
    Employee changedEmployee = new Employee(firstName, lastName, birthday);
    EmployeeList employees = getAllEmployees();
    System.out.println(employees.get(indexInList));

    employees.removeEmployee(employees.get(indexInList));
    employees.addEmployee(changedEmployee);
    System.out.println(firstName);

    saveEmployees(employees);
  }

  /**
   * Deletes an employee from the arrayList
   * @param indexInList int of the employee's index in the EmployeeList
   */
  public void deleteEmployee(int indexInList){
    EmployeeList employees = getAllEmployees();
    for (int i = 0; i < employees.size(); i++) {
      if(i == indexInList){
        employees.removeEmployee(employees.get(i));
      }
    }
    saveEmployees(employees);
  }

  /**
   * Gets the selected employee by employee's index
   * @param index int of employee's index in the EmployeeList
   * @return Employee the employee on this index
   */
  public Employee getSelectedEmployee(int index){
    if(index < getAllEmployees().size()){
      return getAllEmployees().get(index);
    }
    return null;
  }
}