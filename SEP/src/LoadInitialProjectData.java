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
    {   MyDate deadline1 = new MyDate(12,9,2020);
        MyDate deadline2 = new MyDate(14,12,2020);
        MyDate deadline3 = new MyDate(24,1,2021);
        MyDate deadline4 = new MyDate(3,2,2021);
        MyDate deadline5 = new MyDate(16,4,2021);
        MyDate deadline6 = new MyDate(18,2,2021);
        MyDate deadline7 = new MyDate(26,3,2021);


        EmployeeAdapter employeeAdapter = new EmployeeAdapter("employees.bin");
        EmployeeList allEmployees = employeeAdapter.getAllEmployees();
        AssignedEmployee assignedEmployee1 = new AssignedEmployee(allEmployees.get(0).getFirstName(), allEmployees.get(0).getLastName(), allEmployees.get(0).getDateOfBirth(), 0);
        AssignedEmployee assignedEmployee2 = new AssignedEmployee(allEmployees.get(1).getFirstName(), allEmployees.get(1).getLastName(), allEmployees.get(1).getDateOfBirth(), 1);
        AssignedEmployee assignedEmployee3 = new AssignedEmployee(allEmployees.get(2).getFirstName(), allEmployees.get(2).getLastName(), allEmployees.get(2).getDateOfBirth(), 2);
        AssignedEmployee assignedEmployee4 = new AssignedEmployee(allEmployees.get(3).getFirstName(), allEmployees.get(3).getLastName(), allEmployees.get(3).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee5 = new AssignedEmployee(allEmployees.get(4).getFirstName(), allEmployees.get(4).getLastName(), allEmployees.get(4).getDateOfBirth(), 0);
        AssignedEmployee assignedEmployee6 = new AssignedEmployee(allEmployees.get(5).getFirstName(), allEmployees.get(5).getLastName(), allEmployees.get(5).getDateOfBirth(), 1);
        AssignedEmployee assignedEmployee7 = new AssignedEmployee(allEmployees.get(6).getFirstName(), allEmployees.get(6).getLastName(), allEmployees.get(6).getDateOfBirth(), 2);
        AssignedEmployee assignedEmployee8 = new AssignedEmployee(allEmployees.get(7).getFirstName(), allEmployees.get(7).getLastName(), allEmployees.get(7).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee9 = new AssignedEmployee(allEmployees.get(8).getFirstName(), allEmployees.get(8).getLastName(), allEmployees.get(8).getDateOfBirth(), 0);
        AssignedEmployee assignedEmployee10 = new AssignedEmployee(allEmployees.get(9).getFirstName(), allEmployees.get(9).getLastName(), allEmployees.get(9).getDateOfBirth(), 1);
        AssignedEmployee assignedEmployee11 = new AssignedEmployee(allEmployees.get(10).getFirstName(), allEmployees.get(10).getLastName(), allEmployees.get(10).getDateOfBirth(), 2);
        AssignedEmployee assignedEmployee12 = new AssignedEmployee(allEmployees.get(11).getFirstName(), allEmployees.get(11).getLastName(), allEmployees.get(11).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee13 = new AssignedEmployee(allEmployees.get(12).getFirstName(), allEmployees.get(12).getLastName(), allEmployees.get(12).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee14 = new AssignedEmployee(allEmployees.get(13).getFirstName(), allEmployees.get(13).getLastName(), allEmployees.get(13).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee15 = new AssignedEmployee(allEmployees.get(14).getFirstName(), allEmployees.get(14).getLastName(), allEmployees.get(14).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee16 = new AssignedEmployee(allEmployees.get(15).getFirstName(), allEmployees.get(15).getLastName(), allEmployees.get(15).getDateOfBirth(), 3);
        AssignedEmployee assignedEmployee17 = new AssignedEmployee(allEmployees.get(16).getFirstName(), allEmployees.get(16).getLastName(), allEmployees.get(16).getDateOfBirth(), 3);

        Project project1 = new Project("Project 1", "Description 1", 1);
        Project project2 = new Project("Project 2", "Description 2", 2);
        Project project3 = new Project("Project 3", "Description 3",1);

        Requirement requirement1 = new Requirement("Requirement 1", "User story 1",2,assignedEmployee1,deadline1,0,"Started");
        Requirement requirement2 = new Requirement("Requirement 2", "User story 2",1,assignedEmployee5,deadline2,1,"Not started");
        Requirement requirement3 = new Requirement("Requirement 3", "User story 3",3,assignedEmployee3,deadline3,2,"Ended");
        Requirement requirement4 = new Requirement("Requirement 4", "User story 4",3,assignedEmployee7,deadline5,0,"Approved");
        Requirement requirement5 = new Requirement("Requirement 5", "User story 5",1,assignedEmployee10,deadline7,1,"Started");
        Requirement requirement6 = new Requirement("Requirement 6", "User story 6",1,assignedEmployee9,deadline1,2,"Started");
        Requirement requirement7 = new Requirement("Requirement 7", "User story 7",2,assignedEmployee13,deadline3,0,"Not started");
        Requirement requirement8 = new Requirement("Requirement 8", "User story 8",2,assignedEmployee15,deadline2,1,"Rejected");
        Requirement requirement9 = new Requirement("Requirement 9", "User story 9",3,assignedEmployee17,deadline6,2,"Ended");

        Task task1 = new Task("task1","This is task 1",2.0,deadline1, 0, "Started", assignedEmployee1);
        Task task2 = new Task("task2","This is task 2",4.0,deadline4, 1, "Not started", assignedEmployee5);
        Task task3 = new Task("task3","This is task 3",10.0,deadline3, 2, "Approved", assignedEmployee4);
        Task task4 = new Task("task4","This is task 4",12.0,deadline6, 0, "Started", assignedEmployee2);
        Task task5 = new Task("task5","This is task 5",14.0,deadline7, 1, "Ended", assignedEmployee5);
        Task task6 = new Task("task6","This is task 6",6.0,deadline4, 2, "Started", assignedEmployee3);
        Task task7 = new Task("task7","This is task 7",9.0,deadline2, 0, "Not started", assignedEmployee1);
        Task task8 = new Task("task8","This is task 8",10.5,deadline1, 1, "Started", assignedEmployee2);
        Task task9 = new Task("task9","This is task 9",12.0,deadline5, 2, "Rejected", assignedEmployee4);

        Task task10 = new Task("task10","This is task 10",5.0,deadline3, 0, "Started", assignedEmployee6);
        Task task11 = new Task("task11","This is task 11",15.5,deadline7, 1, "Not started", assignedEmployee12);
        Task task12 = new Task("task12","This is task 12",11.0,deadline1, 2, "Approved", assignedEmployee10);
        Task task13 = new Task("task13","This is task 13",11.0,deadline2, 0, "Started", assignedEmployee7);
        Task task14 = new Task("task14","This is task 14",14.0,deadline4, 1, "Not started", assignedEmployee9);
        Task task15 = new Task("task15","This is task 15",8.0,deadline6, 2, "Started", assignedEmployee9);
        Task task16 = new Task("task16","This is task 16",10.0,deadline3, 0, "Not started", assignedEmployee8);
        Task task17 = new Task("task17","This is task 17",18.0,deadline7, 1, "Started", assignedEmployee10);
        Task task18 = new Task("task18","This is task 18",13.0,deadline6, 2, "Ended", assignedEmployee11);

        Task task19 = new Task("task19","This is task 19",15.0,deadline4, 0, "Started", assignedEmployee13);
        Task task20 = new Task("task20","This is task 20",7.5,deadline3, 1, "Started", assignedEmployee15);
        Task task21 = new Task("task21","This is task 21",16.0,deadline5, 2, "Not started", assignedEmployee17);
        Task task22 = new Task("task22","This is task 22",12.0,deadline1, 0, "Approved", assignedEmployee13);
        Task task23 = new Task("task23","This is task 23",3.5,deadline2, 1, "Not started", assignedEmployee16);
        Task task24 = new Task("task24","This is task 24",8.0,deadline6, 2, "Started", assignedEmployee14);
        Task task25 = new Task("task25","This is task 25",10.0,deadline7, 0, "Started", assignedEmployee17);
        Task task26 = new Task("task26","This is task 26",11.0,deadline2, 1, "Not started", assignedEmployee15);
        Task task27 = new Task("task27","This is task 27",9.0,deadline3, 2, "Ended", assignedEmployee16);

        project1.addTeamMember(assignedEmployee1);
        project1.addTeamMember(assignedEmployee2);
        project1.addTeamMember(assignedEmployee3);
        project1.addTeamMember(assignedEmployee4);
        project1.addTeamMember(assignedEmployee5);
        project1.setProjectCreator(assignedEmployee1);
        project1.setProductOwner(assignedEmployee5);
        project1.setScrumMaster(assignedEmployee3);

        project2.addTeamMember(assignedEmployee6);
        project2.addTeamMember(assignedEmployee7);
        project2.addTeamMember(assignedEmployee8);
        project2.addTeamMember(assignedEmployee9);
        project2.addTeamMember(assignedEmployee10);
        project2.addTeamMember(assignedEmployee11);
        project2.addTeamMember(assignedEmployee12);
        project2.setProjectCreator(assignedEmployee11);
        project2.setProductOwner(assignedEmployee8);
        project2.setScrumMaster(assignedEmployee10);

        project3.addTeamMember(assignedEmployee13);
        project3.addTeamMember(assignedEmployee14);
        project3.addTeamMember(assignedEmployee15);
        project3.addTeamMember(assignedEmployee16);
        project3.addTeamMember(assignedEmployee17);
        project3.setProjectCreator(assignedEmployee14);
        project3.setProductOwner(assignedEmployee15);
        project3.setScrumMaster(assignedEmployee13);

        project1.addRequirement(requirement1);
        project1.addRequirement(requirement2);
        project1.addRequirement(requirement3);

        project2.addRequirement(requirement4);
        project2.addRequirement(requirement5);
        project2.addRequirement(requirement6);

        project3.addRequirement(requirement7);
        project3.addRequirement(requirement8);
        project3.addRequirement(requirement9);

        requirement1.addTask(task1);
        requirement1.addTask(task2);
        requirement1.addTask(task3);

        requirement2.addTask(task4);
        requirement2.addTask(task5);
        requirement2.addTask(task6);

        requirement3.addTask(task7);
        requirement3.addTask(task8);
        requirement3.addTask(task9);

        requirement4.addTask(task10);
        requirement4.addTask(task11);
        requirement4.addTask(task12);

        requirement5.addTask(task13);
        requirement5.addTask(task14);
        requirement5.addTask(task15);

        requirement6.addTask(task16);
        requirement6.addTask(task17);
        requirement6.addTask(task18);

        requirement7.addTask(task19);
        requirement7.addTask(task20);
        requirement7.addTask(task21);

        requirement8.addTask(task22);
        requirement8.addTask(task23);
        requirement8.addTask(task24);

        requirement9.addTask(task25);
        requirement9.addTask(task26);
        requirement9.addTask(task27);


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
