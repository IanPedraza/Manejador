package data;

import interfaces.IProjection;
import interfaces.ISelection;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

public class dbManager implements ISelection, IProjection {

    @Override
    public Table between(Double lowestValue, Double highestValue, Table table) {
        Table tableResult = new Table();
        tableResult.setTableName(table.getTableName());

        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;

            if (e.getSalary() >= lowestValue && e.getSalary() <= highestValue) {
                tableResult.addTuple(e);
            }

        }

        return tableResult;
    }

    @Override
    public Table filterRepeatedTuples(Table table) {
        Table tableResult = new Table();
        Table tableAux = table;
        tableResult.setTableName(table.getTableName());

        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;

            for (Tuple t2 : table.getTuplesList()) {
                TupleEmployee e2 = (TupleEmployee) t;
                if (e.equals(e2)) {
                    tableAux.deleteTuple(t2);
                }
            }

        }

        return tableAux;
    }

    @Override
    public void firstNameSalaryProjection(Table employeesTable) {
        for(Tuple t : employeesTable.getTuplesList()){
            TupleEmployee e = (TupleEmployee) t;
            System.out.println(e.getFirstName() + "\t" + e.getSalary());
        }
    }

}
