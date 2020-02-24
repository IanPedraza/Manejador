package data;

import interfaces.IProjection;
import interfaces.ISelection;
import java.util.ArrayList;
import java.util.List;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

public class DataBaseManager implements ISelection, IProjection {
    //Between recibe los rangos junto con la tabla
    @Override
    public Table between(Double lowestValue, Double highestValue, Table table) {
        Table tableResult = new Table();
        tableResult.setTableName(table.getTableName());
        //Recorre las tuplas de empleados con un "t"
        for (Tuple t : table.getTuplesList()) {
            //El "t" lo pasa a formato employees
            TupleEmployee e = (TupleEmployee) t;
            //Evalua el salario
            if (e.getSalary() >= lowestValue && e.getSalary() <= highestValue) {
                tableResult.addTuple(e);
            }

        }

        return tableResult;
    }
    
    //Elimina las tuplas repetidas de la proyeccion
    @Override
    public List<List<String>> filterRepeatedTuples( List<List<String>> table) {
        
        List<List<String>> tableAux = new ArrayList(table);

        
        for (List lista : tableAux) {
            List listaStr = (List) lista;
            int repeatCont=0;
            for (List t2 : table) {
                List e2 = (List) t2;
               //evalua si son iguales
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
    //hace la proyeccion
    @Override
    public List<List<String>> projection(List<String> attrs, Table employeesTable) {
        List<List<String>> salida = new ArrayList();
        if(attrs == null) return salida;
        
        //toma los datos empleado por empleado
        for (Tuple t : employeesTable.getTuplesList()) {
            TupleEmployee employee = (TupleEmployee) t;
            List<String> tuplaSalida = new ArrayList();
            //devuelve los datos del atributo seleccionado
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
