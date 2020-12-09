import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An adapter to the employees file, making it easy to retrieve and store information.
 * @author Claudiu Cordunianu
 * @version 1.0
 */

public class EmployeeAdapter
{
  private MyFileIO mfio;
  private String fileName;

  /**
   * 1-argument constructor setting the file name.
   * @param fileName the name and path of the file where employees will be saved and retrieved
   */

  public EmployeeAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  /**
   * Uses the MyFileIO class to retrieve an EmployeesList object with all employees.
   * @return a EmployeesList object with all stored employees.
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
  public void changeCountry(String firstName, String lastName, String birthday)
  {
    EmployeeList employees = getAllEmployees();

    for (int i = 0; i < employees.size(); i++)
    {
      Employee student = employees.get(i);

      if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
      {
        student.getDateOfBirth().toString();
      }
    }

    saveEmployees(employees);
  }

}