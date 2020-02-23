package view;

import data.DataManager;
import data.dbManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

public class ViewController {

    private Window window;
    
    public void start() {
        DataManager dataManager = new DataManager();
        dbManager dbManager = new dbManager();

        //Obtenemos la tabla
        Table table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");

        //Creamos la ventana
        window = new Window();

        //Cargamos la tabla completa
        loadTable(table, window.getFullTable(), dataManager.dictionaryToArrayString());

        //Hacemos la selección
        Table selectionResult = dbManager.between(1000.0, 2500.0, table);

        //Cargamos la selección a la vista
        loadTable(selectionResult, window.getSelectionTable(), dataManager.dictionaryToArrayString());

        //Eliminamos las tuplas repetidas
        Table filteredTable = dbManager.filterRepeatedTuples(selectionResult);

        //Creamos la lista de atributos que queremos hacer en la proyección
        List<String> attrs = new ArrayList();
        attrs.add("FIRST_NAME");
        attrs.add("SALARY");
        attrs.add("EMAIL");

        //HAcemos la proyección
        List<List<String>> projection = dbManager.projection(attrs, filteredTable);
        this.loadTableFromArrayString(projection, window.getProyectionTable(), attrs);

    }

    public void loadTable(Table table, JTable jtable, List<String> columnNames) {
        DefaultTableModel model = new DefaultTableModel(null, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;
            model.addRow(e.toArrayString().toArray());
        }

        model.fireTableDataChanged();
        jtable.setModel(model);
    }

    public void loadTableFromArrayString(List<List<String>> data, JTable jtable, List<String> columnNames) {
        DefaultTableModel modelFullTable = new DefaultTableModel(null, columnNames.toArray()){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
;

        for (List<String> row : data) {
            modelFullTable.addRow(row.toArray());
        }

        modelFullTable.fireTableDataChanged();
        jtable.setModel(modelFullTable);
    }

    public static void dbTest() {
        DataManager dataManager = new DataManager();
        dbManager dbManager = new dbManager();

        //Obtenemos la tabla
        Table table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");

        System.out.println("Tabla completa **************************************");
        //printTable(table);

        //Hacemos la seleccion
        Table selectionResult = dbManager.between(1000.0, 2500.0, table);
        System.out.println("Tabla Selección **************************************");
        //printTable(selectionResult);

        //Eliminamos las tuplas repetidas
        Table filteredTable = dbManager.filterRepeatedTuples(selectionResult);
        System.out.println("Tabla Filtrada **************************************");
        //printTable(filteredTable);

        //Hacemos la proyección
        System.out.println("Tabla Proyección **************************************");

        //Creamos la lista de atributos que queremos hacer en la proyección
        List<String> attrs = new ArrayList();
        attrs.add("FIRST_NAME");
        attrs.add("SALARY");
        attrs.add("EMAIL");

        //HAcemos la proyección
        List<List<String>> projection = dbManager.projection(attrs, filteredTable);
        //printProjection(projection);

        Window window = new Window();
    }

}
