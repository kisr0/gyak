package Model;

public class Employee extends Person {
    private Company company;

    public Employee(int id, Vehicle vehicle, Company company) {
        super(id, vehicle);
        this.company = company;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}