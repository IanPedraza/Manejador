package data;

import interfaces.IProjection;
import interfaces.ISelection;
import model.Table;

public class dbManager implements ISelection, IProjection{

    @Override
    public Table between(Double lowestValue, Double highestValue, Table table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Table filterRepeatedTuples(Table table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void firstNameSalaryProjection(Table employeesTable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
