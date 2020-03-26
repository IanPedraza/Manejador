package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TupleDepartments extends Tuple{
    
    private int departmentId;
    private String departmentName;
    private int managerId;
    private int locationId;
    
    public TupleDepartments(int departmentId, String departmenName, int managerId, int locationId){
        this.departmentId = departmentId;
        this.departmentName = departmenName;
        this.managerId = managerId;
        this.locationId = locationId;
    }
    
    public TupleDepartments(){
        this.departmentId = 0;
        this.departmentName = "";
        this.locationId = 0;
        this.managerId = 0;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "\ndepartmentId: " + departmentId
                + "departmentName: " + departmentName
                + "managerId: " + managerId
                + "locationId: " + locationId
                + "\n*********************************";
    }
    
    @Override
    public String getAttrFromString(String attr){
        switch(attr){
            
            case "DEPARTMENT_ID":
                return String.valueOf(departmentId);
            
            case "DEPARTMENTNAME":
                return String.valueOf(departmentName);
            
            case "MANAGERID":
                return String.valueOf(managerId);
            
            case "LOCATIONID":
                return String.valueOf(locationId);
            
            default:
                return null;
        }
    }
    
    @Override
    public List<String> toArrayString(){
        
        List<String> list = new ArrayList();
        
        list.add(String.valueOf(departmentId));
        list.add(String.valueOf(departmentName));
        list.add(String.valueOf(locationId));
        list.add(String.valueOf(managerId));
        
        return list;
    }
    
    @Override
    public Boolean equalsTo(Tuple tupla){
        
        TupleDepartments e = (TupleDepartments) tupla;
        
        return departmentId == e.getDepartmentId()
                && departmentName.equals(e.departmentName)
                && locationId == e.locationId
                && managerId == e.managerId;
    }
}
