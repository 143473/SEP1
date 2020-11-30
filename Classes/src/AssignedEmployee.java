public class AssignedEmployee extends Employee{
    private EmployeeStatus status;

    public AssignedEmployee(String firstName, String lastName, MyDate dateOfBirth, EmployeeStatus status){
        super(firstName, lastName, dateOfBirth);
        this.status = status;
    }
    public EmployeeStatus getStatus() {
        return status;
    }
    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }
    public AssignedEmployee copy(){
        return new AssignedEmployee(firstName, lastName, dateOfBirth, status);
    }
}
