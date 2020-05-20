package model;

public class TupleProducto extends Tuple {
    
    private TupleDepartments department;
    private TupleEmployee employee;

    public TupleProducto(TupleDepartments department, TupleEmployee employee) {
        this.department = department;
        this.employee = employee;
    }

    public TupleProducto() {
        this.department = new TupleDepartments();
        this.employee = new TupleEmployee();
    }

    public TupleDepartments getDepartment() {
        return department;
    }

    public void setDepartment(TupleDepartments department) {
        this.department = department;
    }

    public TupleEmployee getEmployee() {
        return employee;
    }

    public void setEmployee(TupleEmployee employee) {
        this.employee = employee;
    }   
    
    public String getAttrFromString(String attr) {
        return this.employee.getAttrFromString(attr) + " , " + this.department.getAttrFromString(attr); 
    }
    
}
