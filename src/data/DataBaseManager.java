package data;

import interfaces.IJoin;
import interfaces.IProjection;
import interfaces.ISelection;
import java.util.ArrayList;
import java.util.List;
import model.Table;
import model.Tuple;
import model.TupleDepartments;
import model.TupleEmployee;
import model.TupleProducto;
import model.TupleProductoCartesiano;

public class DataBaseManager implements ISelection, IProjection, IJoin {
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
    public List<List<String>> filterRepeatedTuples(List<List<String>> table) {

        List<List<String>> tableAux = new ArrayList(table);
        List<List<String>> tableRes = new ArrayList(table);

        for (List<String> lista : tableAux) {
            
            List<String> listaStr = (List<String>) lista;
            int repeatCont = 0;
            
            for (List<String> t2 : table) {
                List e2 = (List<String>) t2;
                //evalua si son iguales
                if (listaStr.equals(e2)) {
                    if (repeatCont > 0) {
                        tableRes.remove(t2);
                    } else {
                        repeatCont = 1;
                    }
                }
            }

        }
        
        return tableRes;
    }

    //hace la proyeccion
    @Override
    public List<List<String>> projection(List<String> attrs, Table employeesTable) {
        List<List<String>> salida = new ArrayList();
        if (attrs == null) {
            return salida;
        }

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

    @Override
    public Table productoCartesiano(Table tableEmployee, Table tableDepartment) {
        Table tableResult = new Table();
        tableResult.setTableName(tableEmployee.getTableName() + "-" + tableDepartment.getTableName());

        //Recorre las tuplas de empleados
        for (Tuple tupleEmployee : tableEmployee.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) tupleEmployee;

            //Recorre las tuplas de depatamentos
            for (Tuple tupleDepartment : tableDepartment.getTuplesList()) {
                TupleDepartments d = (TupleDepartments) tupleDepartment;

                TupleProducto p = new TupleProducto();
                p.setDepartment(d);
                p.setEmployee(e);
                
                tableResult.addTuple(p);
            }

        }

        return tableResult;
    }

    public TupleProductoCartesiano crearTupla(TupleEmployee e, TupleDepartments d) {
        TupleProductoCartesiano res = new TupleProductoCartesiano();
        res.setEmployeeId(e.getEmployeeId());
        res.setFirstName(e.getFirstName());
        res.setLastName(e.getLastName());
        res.setEmail(e.getEmail());
        res.setPhoneNumber(e.getPhoneNumber());
        res.setHireDate(e.getHireDate());
        res.setJobId(e.getJobId());
        res.setSalary(e.getSalary());
        res.setCommissionPct(e.getCommissionPct());
        res.setManagerIdTupleE(e.getManagerId());
        res.setDepartmentIdTupleE(e.getDepartmentId());
        res.setDepartmentIdTupleD(d.getDepartmentId());
        res.setDepartmentName(d.getDepartmentName());
        res.setManagerIdTupleD(e.getDepartmentId());
        res.setLocationId(d.getLocationId());
        
        return res;
    }

    @Override
    public Table join(String campoUno, String campoDos, Table table) {
        Table tableResult = new Table();
        tableResult.setTableName("JOIN: " + table.getTableName());
        
        for (Tuple t : table.getTuplesList()) {
            TupleProducto tp = (TupleProducto) t;
            
            String departmentValue = tp.getDepartment().getAttrFromString(campoUno);
            String employeeValue = tp.getEmployee().getAttrFromString(campoDos);            
            
            if (employeeValue != null && departmentValue != null && employeeValue.equalsIgnoreCase(departmentValue)) {
                tableResult.addTuple(tp);
            }
            
        }

        return tableResult;
    }

    @Override
    public List<List<String>> projectionJoin(List<String> attrs, Table table) {
        List<List<String>> salida = new ArrayList();
        
        if (attrs == null) {
            return salida;
        }

        //toma los datos empleado por empleado
        for (Tuple t : table.getTuplesList()) {
            
            TupleProducto tp = (TupleProducto) t;
            List<String> tuplaSalida = new ArrayList();
            
            //devuelve los datos del atributo seleccionado
            for (String attr : attrs) {
                String value = tp.getEmployee().getAttrFromString(attr);
                
                tuplaSalida.add(value);
            }
            
            for (String attr : attrs) {
                String value = tp.getDepartment().getAttrFromString(attr);
                
                tuplaSalida.add(value);
            }

            salida.add(tuplaSalida);
        }

        return salida;
    }

}
