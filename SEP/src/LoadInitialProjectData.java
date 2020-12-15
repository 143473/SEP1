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
    {   MyDate birthday = new MyDate(12,9,2020);
        MyDate deadline = new MyDate(14,12,2020);
        Project project1 = new Project("Project1", "description1", 3);
        AssignedEmployee assignedEmployee1 = new AssignedEmployee("Quewueveve", "Osas", birthday,0);
        AssignedEmployee assignedEmployee2 = new AssignedEmployee("Aeaeeaeeae", "Pepe", birthday,1);
        AssignedEmployee assignedEmployee3 = new AssignedEmployee("Nanananan", "Nunu", birthday,2);
        Requirement requirement1 = new Requirement("requirement1", "ueueueue",12.0,2,assignedEmployee1,deadline,0,"Started");
        Requirement requirement2 = new Requirement("requirement2", "dfdfdfdf",12.0,1,assignedEmployee1,deadline,1,"Started");
        Requirement requirement3 = new Requirement("requirement3", "ytytyty",12.0,3,assignedEmployee1,deadline,2,"Started");
        Task task1 = new Task("task1","This is task 1",12.0,deadline, 0, "Started", assignedEmployee1);
        Task task2 = new Task("task2","This is task 2",12.0,deadline, 1, "Started", assignedEmployee1);
        Task task3 = new Task("task3","This is task 3",12.0,deadline, 2, "Started", assignedEmployee2);
        requirement1.addTask(task1);
        requirement1.addTask(task2);
        requirement1.addTask(task3);
        requirement2.addTask(task1);
        requirement2.addTask(task2);
        requirement2.addTask(task3);
        requirement3.addTask(task1);
        requirement3.addTask(task2);
        requirement3.addTask(task3);
        project1.addRequirement(requirement1);
        project1.addRequirement(requirement2);
        project1.addRequirement(requirement3);
        project1.addTeamMember(assignedEmployee1);
        project1.addTeamMember(assignedEmployee2);
        project1.addTeamMember(assignedEmployee3);
        project1.setScrumMaster(assignedEmployee1);
        project1.setProductOwner(assignedEmployee2);
        project1.setProjectCreator(assignedEmployee3);
        Project project2 = new Project("Project2", "description2", 2);
        project2.addRequirement(requirement1);
        project2.addRequirement(requirement2);
        project2.addRequirement(requirement3);
        project2.addTeamMember(assignedEmployee1);
        project2.addTeamMember(assignedEmployee2);
        project2.addTeamMember(assignedEmployee3);
        project2.setScrumMaster(assignedEmployee1);
        project2.setProductOwner(assignedEmployee2);
        project2.setProjectCreator(assignedEmployee3);
        Project project3 = new Project("Project3", "description",1);
        project3.addRequirement(requirement1);
        project3.addRequirement(requirement2);
        project3.addRequirement(requirement3);
        project3.addTeamMember(assignedEmployee1);
        project3.addTeamMember(assignedEmployee2);
        project3.addTeamMember(assignedEmployee3);
        project3.setScrumMaster(assignedEmployee1);
        project3.setProductOwner(assignedEmployee2);
        project3.setProjectCreator(assignedEmployee3);

        ProjectList projectList = new ProjectList();

        projectList.addProject(project1);
        projectList.addProject(project2);
        projectList.addProject(project3);
        ProjectsAdapter projectsAdapter = new ProjectsAdapter("projects.bin");
        projectsAdapter.saveProjects(projectList);

        MyFileIO mfio = new MyFileIO();
        try
        {
            mfio.writeToFile("projects.bin",projectList);
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
