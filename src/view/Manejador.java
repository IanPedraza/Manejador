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
//        printTable(table);
        
//        //Hacemos la seleccion
//        Table selectionResult = dbManager.between(10000.0, 15000.0, table);
//        
//        //Eliminamos las tuplas repetidas
//        Table filteredTable = dbManager.filterRepeatedTuples(selectionResult);
//        
//        //Hacemos la proyección
//        dbManager.firstNameSalaryProjection(filteredTable);
    }

}
