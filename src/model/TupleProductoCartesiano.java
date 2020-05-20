

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TupleProductoCartesiano extends Tuple {
    
    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String jobId;
    private Double salary;
    private Double commissionPct;
    private int managerIdTupleE;
    private int departmentIdTupleE;

    private int departmentIdTupleD;
    private String departmentName;
    private int managerIdTupleD;
    private int locationId;

    public TupleProductoCartesiano(int employeeId, String firstName, String lastName, String email, String phoneNumber, String hireDate, String jobId, Double salary, Double commissionPct, int managerIdTupleE, int departmentIdTupleE, int departmentIdTupleD, String departmentName, int managerIdTupleD, int locationId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.managerIdTupleE = managerIdTupleE;
        this.departmentIdTupleE = departmentIdTupleE;
        this.departmentIdTupleD = departmentIdTupleD;
        this.departmentName = departmentName;
        this.managerIdTupleD = managerIdTupleD;
        this.locationId = locationId;
    }
    
    
    public TupleProductoCartesiano(){
        
        this.employeeId = 0;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.hireDate = "";
        this.jobId = "";
        this.salary = 0.0;
        this.commissionPct = 0.0;
        this.managerIdTupleE = 0;
        this.departmentIdTupleE = 0;
    
        this.departmentIdTupleD = 0;
        this.departmentName = "";
        this.locationId = 0;
        this.managerIdTupleD = 0;
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

    public int getManagerIdTupleE() {
        return managerIdTupleE;
    }

    public void setManagerIdTupleE(int managerIdTupleE) {
        this.managerIdTupleE = managerIdTupleE;
    }

    public int getDepartmentIdTupleE() {
        return departmentIdTupleE;
    }

    public void setDepartmentIdTupleE(int departmentIdTupleE) {
        this.departmentIdTupleE = departmentIdTupleE;
    }

    public int getDepartmentIdTupleD() {
        return departmentIdTupleD;
    }

    public void setDepartmentIdTupleD(int departmentIdTupleD) {
        this.departmentIdTupleD = departmentIdTupleD;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getManagerIdTupleD() {
        return managerIdTupleD;
    }

    public void setManagerIdTupleD(int managerIdTupleD) {
        this.managerIdTupleD = managerIdTupleD;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
                + "\nmanagerIdEmployee: " + managerIdTupleE
                + "\ndepartmentIdEmployee: " + departmentIdTupleE
                +  "\ndepartmentIdDepartments: " + departmentIdTupleD
                + "departmentName: " + departmentName
                + "managerIdDepartments: " + managerIdTupleD
                + "locationId: " + locationId
                + "\n*********************************";
    
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

            case "MANAGER_ID_EMPLOYEE":
                return String.valueOf(managerIdTupleE);

            case "DEPARTMENT_ID_EMPLOYEE":
                return String.valueOf(departmentIdTupleE);
                
            case "DEPARTMENT_ID_DEPARTMENTS":
                return String.valueOf(departmentIdTupleD);
            
            case "DEPARTMENTNAME":
                return String.valueOf(departmentName);
            
            case "MANAGER_ID_DEPARTMENTS":
                return String.valueOf(managerIdTupleD);
            
            case "LOCATIONID":
                return String.valueOf(locationId);
            
            default:
                return null;
                
        }
    }
    
    
    
    
    @Override
    public List<String> toArrayString() {
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
        list.add(String.valueOf(managerIdTupleE));
        list.add(String.valueOf(departmentIdTupleE));
        list.add(String.valueOf(departmentIdTupleD));
        list.add(String.valueOf(departmentName));
        list.add(String.valueOf(managerIdTupleD));
        list.add(String.valueOf(locationId));
        

        return list;
    }
    
    
    public List<String> NamestoArrayString() {
        List<String> list = new ArrayList();

        list.add(String.valueOf("employee_Id".toUpperCase()));
        list.add(String.valueOf("first_Name".toUpperCase()));
        list.add(String.valueOf("last_Name".toUpperCase()));
        list.add(String.valueOf("email".toUpperCase()));
        list.add(String.valueOf("phone_Number".toUpperCase()));
        list.add(String.valueOf("hire_Date".toUpperCase()));
        list.add(String.valueOf("job_Id".toUpperCase()));
        list.add(String.valueOf("salary".toUpperCase()));
        list.add(String.valueOf("commission_Pct".toUpperCase()));
        list.add(String.valueOf("manager_Id (employee)".toUpperCase()));
        list.add(String.valueOf("department_Id (employee)".toUpperCase()));
        list.add(String.valueOf("department_Id (department)".toUpperCase()));
        list.add(String.valueOf("department_Name".toUpperCase()));
        list.add(String.valueOf("manager_Id (department)".toUpperCase()));
        list.add(String.valueOf("location_Id".toUpperCase()));
        

        return list;
    }

    @Override
    public Boolean equalsTo(Tuple tupla) {
        TupleProductoCartesiano e = (TupleProductoCartesiano) tupla;

        return employeeId == e.getEmployeeId()
                && firstName.equals(e.firstName)
                && lastName.equals(e.lastName)
                && email.equals(e.email)
                && phoneNumber.equals(e.phoneNumber)
                && hireDate.equals(e.hireDate)
                && jobId.equals(e.jobId)
                && Objects.equals(salary, e.salary)
                && Objects.equals(commissionPct, e.commissionPct)
                && managerIdTupleE == e.managerIdTupleE
                && departmentIdTupleE == e.departmentIdTupleE
                && departmentIdTupleD == e.getDepartmentIdTupleD()
                && departmentName.equals(e.departmentName)
                && locationId == e.locationId
                && managerIdTupleD == e.managerIdTupleD;
    }

}

    

