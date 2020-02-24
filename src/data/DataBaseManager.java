package data;

import interfaces.IProjection;
import interfaces.ISelection;
import java.util.ArrayList;
import java.util.List;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

    public class DataBaseManager implements ISelection, IProjection {

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
    public List<List<String>> filterRepeatedTuples( List<List<String>> table) {
        
        List<List<String>> tableAux = new ArrayList(table);


        for (List lista : tableAux) {
            List listaStr = (List) lista;
            int repeatCont=0;
            for (List t2 : table) {
                List e2 = (List) t2;
                if ( listaStr.equals(e2)) {
                    if(repeatCont>0){
                        tableAux.remove(t2);
                    }else{
                        repeatCont=1;
                    }
                }
            }

        }
        return tableAux;
    }

    @Override
    public List<List<String>> projection(List<String> attrs, Table employeesTable) {
        List<List<String>> salida = new ArrayList();
        if(attrs == null) return salida;
        
        
        for (Tuple t : employeesTable.getTuplesList()) {
            TupleEmployee employee = (TupleEmployee) t;
            List<String> tuplaSalida = new ArrayList();

            for (String attr : attrs) {
                String value = employee.getAttrFromString(attr);
                if (value != null) {
                    tuplaSalida.add(value);
                }
            }

            salida.add(tuplaSalida);
        }

        return salida;
    }

}
