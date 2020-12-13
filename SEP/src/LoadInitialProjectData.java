import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *  A class for loading the initial data for employees
 *  * @author Marketa Lapcikova
 *  * @version 1.0
 */
public class LoadInitialProjectData {

    /**
     * Starts the program
     * @param args comment line arguments
     * @throws FileNotFoundException exception when file is not found
     * @throws IOException exceptions happening during input or output
     */
    public static void main(String[] args)
    {

        ProjectList projectList = new ProjectList();
        EmployeeAdapter employee = new EmployeeAdapter("employees.bin");
        EmployeeList employeeList = new EmployeeList();
        ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");



        MyTextFileIO mtfio = new MyTextFileIO();
        String[] projectsArray = null;
        try
        {
            projectsArray = mtfio.readArrayFromFile("projects.txt");

            for(int i = 0; i<projectsArray.length; i++)
            {
                String temp = projectsArray[i];
                String[] tempArr = temp.split(",");
                String name = tempArr[0];
                String description = tempArr[1];
                String statusString = tempArr[2];
                int status = Integer.parseInt(statusString);
                String indexString1 = tempArr[3];
                int index1 = Integer.parseInt(indexString1);
                String indexString2 = tempArr[4];
                int index2 = Integer.parseInt(indexString2);
                String indexString3 = tempArr[5];
                int index3 = Integer.parseInt(indexString3);
                projectList.addProject(new Project(name, description, status));







            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File was not found, or could not be opened");
        }

        MyFileIO mfio = new MyFileIO();

        try
        {
            mfio.writeToFile("projects.bin", projectList);
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
        try {
            File myObj = new File("projects.bin");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
