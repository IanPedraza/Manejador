package view;

import data.DataManager;
import data.dbManager;
import java.util.ArrayList;
import java.util.List;
import model.Table;
import static model.Utils.printProjection;
import static model.Utils.printTable;

public class Manejador {

    public static void main(String[] args) {
        dbTest();
    }
    
    public static void dbTest(){
        DataManager dataManager = new DataManager();
        dbManager dbManager = new dbManager();
        
        //Obtenemos la tabla
        Table table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");
        System.out.println("Tabla completa **************************************");
        printTable(table);
        
        //Hacemos la seleccion
        Table selectionResult = dbManager.between(1000.0, 2500.0, table);
        System.out.println("Tabla Selección **************************************");
        printTable(selectionResult);
        
        //Eliminamos las tuplas repetidas
        Table filteredTable = dbManager.filterRepeatedTuples(selectionResult);
        System.out.println("Tabla Filtrada **************************************");
        printTable(filteredTable);
        
        //Hacemos la proyección
        System.out.println("Tabla Proyección **************************************");
        
        //Creamos la lista de atributos que queremos hacer en la proyección
        List<String> attrs = new ArrayList();
        attrs.add("FIRST_NAME");
        attrs.add("SALARY");
        attrs.add("EMAIL");
        
        //HAcemos la proyección
        List<List<String>> projection = dbManager.projection(attrs, filteredTable);
        printProjection(projection);
    }

}
