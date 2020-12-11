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
