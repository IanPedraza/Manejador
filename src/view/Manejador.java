package view;

import data.DataBaseManager;
import data.DataManager;
import java.util.ArrayList;
import java.util.List;
import model.Table;

public class Manejador {

   
    public static void main(String[] args) {
        ViewController viewController = new ViewController();
        viewController.start();
    }
    public static void dbTest(){
        DataManager dataManager = new DataManager();
        DataBaseManager dbManager = new DataBaseManager();
        
        //Obtenemos la tabla
        Table table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");

        System.out.println("Tabla completa **************************************");
        dataManager.MostrarTabla(table);
        
        //Hacemos la seleccion
        Table selectionResult = dbManager.between(1000.0, 2500.0, table);
        System.out.println("Tabla Selección **************************************");
        dataManager.MostrarTabla(selectionResult);
        
        //Eliminamos las tuplas repetidas
        Table filteredTable = dbManager.filterRepeatedTuples(selectionResult);
        System.out.println("Tabla Filtrada **************************************");
        dataManager.MostrarTabla(filteredTable);
        
        //Hacemos la proyección
        System.out.println("Tabla Proyección **************************************");
        
        //Creamos la lista de atributos que queremos hacer en la proyección
        List<String> attrs = new ArrayList();
        attrs.add("FIRST_NAME");
        attrs.add("SALARY");
        attrs.add("EMAIL");
    }
}
