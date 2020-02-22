package data;

import interfaces.IProjection;
import interfaces.ISelection;
import java.util.ArrayList;
import java.util.List;
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
        Table tableAux = new Table(table);

        tableResult.setTableName(table.getTableName());

        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;

            for (Tuple t2 : table.getTuplesList()) {
                TupleEmployee e2 = (TupleEmployee) t2;
                if (e != e2 && e.equals(e2)) {
                    tableAux.deleteTuple(t2);
                }
            }

        }

        return tableAux;
    }

    @Override
    public List<List<String>> projection(List<String> attrs, Table employeesTable) {
        List<List<String>> salida = new ArrayList();

        for (Tuple t : employeesTable.getTuplesList()) {
            TupleEmployee employee = (TupleEmployee) t;
            List<String> tuplaSalida = new ArrayList();

            for (String attr : attrs) {
                String value = getValueAttr(attr, employee);
                if(value != null) tuplaSalida.add(value);
            }

            salida.add(tuplaSalida);
        }

        return salida;
    }

    private String getValueAttr(String attr, TupleEmployee e) {

        switch (attr) {
            case "EMPLOYEE_ID":
                return String.valueOf(e.getEmployeeId());

            case "FIRST_NAME":
                return String.valueOf(e.getFirstName());

            case "LAST_NAME":
                return String.valueOf(e.getLastName());

            case "EMAIL":
                return String.valueOf(e.getEmail());

            case "PHONE_NUMBER":
                return String.valueOf(e.getPhoneNumber());

            case "HIRE_DATE":
                return String.valueOf(e.getHireDate());

            case "JOB_ID":
                return String.valueOf(e.getJobId());

            case "SALARY":
                return String.valueOf(e.getSalary());

            case "COMMISSION_PCT":
                return String.valueOf(e.getCommissionPct());

            case "MANAGER_ID":
                return String.valueOf(e.getManagerId());

            case "DEPARTMENT_ID":
                return String.valueOf(e.getDepartmentId());

            default:
                return null;
        }
    }

}
