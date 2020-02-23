package data;

import interfaces.IProjection;
import interfaces.ISelection;
import java.util.ArrayList;
import java.util.List;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

public class dbManager implements ISelection, IProjection {

    //Metodo Between para elegir las tuplas que se encuentran en los rangos deseados
    @Override
    public Table between(Double lowestValue, Double highestValue, Table table) {
        //Tabla que contendra los resultados
        Table tableResult = new Table();
        tableResult.setTableName(table.getTableName());
        //Ciclo para comparar todas las tuplas        
        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;
            //Comparacion tupla por tupla para ver cuales cumplen los parametros
            if (e.getSalary() >= lowestValue && e.getSalary() <= highestValue) {
                tableResult.addTuple(e);
            }

        }

        return tableResult;
    }

    //Metodo para el filtrado de tuplas repetidas
    @Override
    public Table filterRepeatedTuples(Table table) {
        Table tableResult = new Table();
        Table tableAux = new Table(table);

        tableResult.setTableName(table.getTableName());
        //Ciclo para comparar todas las tuplas        
        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;
        //Ciclo para eliminar las tuplas que se repiten
            for (Tuple t2 : table.getTuplesList()) {
                TupleEmployee e2 = (TupleEmployee) t2;
                if (e != e2 && e.equals(e2)) {
                    tableAux.deleteTuple(t2);
                }
            }

        }

        return tableAux;
    }

    //Metodo para obtener la proyeccion de la tabla
    @Override
    public List<List<String>> projection(List<String> attrs, Table employeesTable) {
        List<List<String>> salida = new ArrayList();
        //Ciclo para comparar todas las tuplas                
        for (Tuple t : employeesTable.getTuplesList()) {
            TupleEmployee employee = (TupleEmployee) t;
            List<String> tuplaSalida = new ArrayList();
        //Ciclo para añadir al ArrayList las tuplas que se necesitan en la proyeccion
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
