package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TupleEmployee extends Tuple {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String jobId;
    private Double salary;
    private Double commissionPct;
    private int managerId;
    private int departmentId;

    public TupleEmployee(int employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, String jobId, Double salary, Double commissionPct, int managerId, int departmentId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerId = managerId;
        this.departmentId = departmentId;
    }

    public TupleEmployee() {
        this.employeeId = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.hireDate = "";
        this.jobId = "";
        this.salary = 0.0;
        this.commissionPct = 0.0;
        this.managerId = 0;
        this.departmentId = 0;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {

        return "\nemployeeId: " + employeeId
                + "\nfirstName: " + firstName
                + "\nlastName: " + lastName
                + "\nemail: " + email
                + "\nphoneNumber: " + phoneNumber
                + "\nhireDate: " + hireDate
                + "\njobId: " + jobId
                + "\nsalary: " + salary
                + "\ncommissionPct: " + commissionPct
                + "\nmanagerId: " + managerId
                + "\ndepartmentId: " + departmentId
                + "\n**********************************";
    }

    @Override
    public String getAttrFromString(String attr) {
        switch (attr) {
            case "EMPLOYEE_ID":
                return String.valueOf(employeeId);

            case "FIRST_NAME":
                return String.valueOf(firstName);

            case "LAST_NAME":
                return String.valueOf(lastName);

            case "EMAIL":
                return String.valueOf(email);

            case "PHONE_NUMBER":
                return String.valueOf(phoneNumber);

            case "HIRE_DATE":
                return String.valueOf(hireDate);

            case "JOB_ID":
                return String.valueOf(jobId);

            case "SALARY":
                return String.valueOf(this.salary);

            case "COMMISSION_PCT":
                return String.valueOf(commissionPct);

            case "MANAGER_ID":
                return String.valueOf(managerId);

            case "DEPARTMENT_ID":
                return String.valueOf(departmentId);

            default:
                return null;
        }
    }
    
    @Override
    public List<String> toArrayString(){
        List<String> list = new ArrayList();
       
        list.add(String.valueOf(employeeId));
        list.add(String.valueOf(firstName));
        list.add(String.valueOf(lastName));
        list.add(String.valueOf(email));
        list.add(String.valueOf(phoneNumber));
        list.add(String.valueOf(hireDate));
        list.add(String.valueOf(jobId));
        list.add(String.valueOf(salary));
        list.add(String.valueOf(commissionPct));
        list.add(String.valueOf(managerId));
        list.add(String.valueOf(departmentId));
        
        return list;
    }

    @Override
    public Boolean equalsTo(Tuple tupla) {
        TupleEmployee e = (TupleEmployee) tupla;

        return employeeId == e.getEmployeeId()
                && firstName.equals(e.firstName)
                && lastName.equals(e.lastName)
                && email.equals(e.email)
                && phoneNumber.equals(e.phoneNumber)
                && hireDate.equals(e.hireDate)
                && jobId.equals(e.jobId)
                && Objects.equals(salary, e.salary)
                && Objects.equals(commissionPct, e.commissionPct)
                && managerId == e.managerId
                && departmentId == e.departmentId;
    }

}
