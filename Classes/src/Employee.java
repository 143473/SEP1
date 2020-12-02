import java.util.ArrayList;
import java.util.Objects;

public class Employee {
    private String firstName, lastName;
    private MyDate dateOfBirth;
    private ArrayList<Project> projects;

    public Employee(String firstName, String lastName, MyDate dateOfBirth){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth.copy();
        projects = new ArrayList<Project>();
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public MyDate getDateOfBirth() {
        return dateOfBirth.copy();
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)){
            return false;
        }
        Employee other = (Employee) obj;
        return firstName.equals(other.firstName) &&
                lastName.equals(other.lastName) &&
                dateOfBirth.equals(other.dateOfBirth) &&
                projects.equals(other.projects);
    }
}
