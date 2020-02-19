package view;

import data.DataManager;
import data.dbManager;
import model.Table;
import static model.Utils.printTable;

public class Manejador {

    public static void main(String[] args) {
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
//        
        //Eliminamos las tuplas repetidas
        Table filteredTable = dbManager.filterRepeatedTuples(selectionResult);
        System.out.println("Tabla Filtrada **************************************");
        printTable(filteredTable);
        
        //Hacemos la proyección
        System.out.println("Tabla Proyección **************************************");
        dbManager.firstNameSalaryProjection(filteredTable);
    }

}
