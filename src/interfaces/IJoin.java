package interfaces;

import java.util.List;
import model.Table;

public interface IJoin {
    public Table productoCartesiano(Table tableEmployee, Table tableDepartment);
    public Table join(String campoUno, String campoDos, Table table);
}
