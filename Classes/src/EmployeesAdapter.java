import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeesAdapter
{
  private MyFileIO mfio;
  private String fileName;

  public EmployeesAdapter(String fileName)
  {
    this.fileName = fileName;
    mfio = new MyFileIO();
  }

  // Use the MyFileIO class to retrieve an EmployeesList object with all Employees
  public EmployeesList getAllEmployees()
  {
    EmployeesList employees = new EmployeesList();

    try
    {
      employees = (EmployeesList) mfio.readObjectFromFile(fileName);
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

  // Use the MyFileIO class to save all Employees in the EmployeesList object
  public void saveEmployees(EmployeesList employees)
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
}
