import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *  A class for loading the initial data for employees
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */
public class LoadInitialEmployeesData {

    /**
     * Starts the program
     * @param args comment line argument
     */
    public static void main(String[] args)
    {
        EmployeeList employees = new EmployeeList();

        MyTextFileIO mtfio = new MyTextFileIO();
        String[] employeesArray = null;
        try
        {
            employeesArray = mtfio.readArrayFromFile("employees.txt");

            for(int i = 0; i<employeesArray.length; i++)
            {
                String temp = employeesArray[i];
                String[] tempArr = temp.split(",");
                String firstName = tempArr[0];
                String lastName = tempArr[1];
                String dayString = tempArr[2];
                int day = Integer.parseInt(dayString);
                String monthString = tempArr[3];
                int month = Integer.parseInt(monthString);
                String yearString = tempArr[4];
                int year = Integer.parseInt(yearString);

                employees.addEmployee(new Employee(firstName, lastName, new MyDate(day, month, year)));
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not found, or could not be opened");
        }

        MyFileIO mfio = new MyFileIO();

        try
        {
            mfio.writeToFile("employees.bin", employees);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error opening file ");
        }
        catch (IOException e)
        {
            System.out.println("IO Error writing to file ");
        }

        System.out.println("Done");
    }
}
