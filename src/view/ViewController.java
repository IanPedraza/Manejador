package view;

import data.DataManager;
import data.DataBaseManager;
import interfaces.AttributeSelectedListener;
import interfaces.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

public class ViewController implements AttributeSelectedListener, MenuListener {

    private static Window window;
    private static List<String> attrs;

    private JButton buttonQuery;
    private static JTextField tfStart;
    private static JTextField tfEnd;

    private static DataManager dataManager;
    private static DataBaseManager dbManager;
    
    private Table table;

    public void start() {
        dataManager = new DataManager();
        dbManager = new DataBaseManager();

        //Obtenemos la tabla
        table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");

        //Creamos la ventana
        window = new Window(this, this);

        attrs = new ArrayList();

        buttonQuery = window.getBtnQuery();
        tfStart = window.getTfRangeStart();
        tfEnd = window.getTfRangeEnd();

        buttonQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeQuery(table);
            }
        });

        //Cargamos la tabla completa
        loadTable(table, window.getFullTable(), dataManager.dictionaryToArrayString());

        //Cargamos los atributos
        window.addCheckbox(dataManager.dictionaryToArrayString());
    }

    public static void loadTable(Table table, JTable jtable, List<String> columnNames) {        
        DefaultTableModel model = new DefaultTableModel(null, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //agregando table a la vista jtable recibida por parametros
        if (table != null) {
            for (Tuple t : table.getTuplesList()) {
                TupleEmployee e = (TupleEmployee) t;
                model.addRow(e.toArrayString().toArray());
            }
        }else{
            model = new DefaultTableModel();
        }
        

        model.fireTableDataChanged();
        jtable.setModel(model);
    }
    //cargar la tabla
    public static void loadTableFromArrayString(List<List<String>> data, JTable jtable, List<String> columnNames) {
        if (columnNames == null) {
            return;
        }
        //se crea objeto que recibe columnas 
        DefaultTableModel model = new DefaultTableModel(null, columnNames.toArray()) {
            @Override
            //evita editar los datos de la tabla
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //se agregan las filas a la tabla
        if(data != null){
            for (List<String> row : data) {
                //al modelo de la tablaba le agrega una fila
                model.addRow(row.toArray());
            }
        }else{
            model = new DefaultTableModel();
        }

        model.fireTableDataChanged();
        jtable.setModel(model);
    }

    public static void dbTest() {
        DataManager dataManager = new DataManager();
        DataBaseManager dbManager = new DataBaseManager();

        //Obtenemos la tabla
        Table table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");

        System.out.println("Tabla completa **************************************");
        //printTable(table);

        //Hacemos la seleccion
        Table selectionResult = dbManager.between(1000.0, 2500.0, table);
        System.out.println("Tabla Selección **************************************");
        //printTable(selectionResult);

        //Eliminamos las tuplas repetidas

        //Hacemos la proyección
        System.out.println("Tabla Proyección **************************************");

        //Creamos la lista de atributos que queremos hacer en la proyección
        List<String> attrs = new ArrayList();
        attrs.add("FIRST_NAME");
        attrs.add("SALARY");
        attrs.add("EMAIL");

        //HAcemos la proyección
        List<List<String>> projection = dbManager.projection(attrs, selectionResult);
        //printProjection(projection);

        List<List<String>> filteredTable = dbManager.filterRepeatedTuples(projection);
        System.out.println("Tabla Filtrada **************************************");
        //printProjection(filteredTable);


    }

  public static void makeQuery(Table table) {
        //Obetener Rangos
        Double rangeSart = 0.0;
        Double rangeEnd = 0.0;

        try {
            String valueStart = tfStart.getText();
            String valueEnd = tfEnd.getText();

            if (attrs.size()>0) {
                if (!valueStart.isEmpty()) {
                    if (!valueEnd.isEmpty()) {
                        rangeSart = Double.parseDouble(valueStart);
                        rangeEnd = Double.parseDouble(valueEnd);
                        
                        if(rangeSart < 0 ){
                            JOptionPane.showMessageDialog(null, "El rango inicial no puede ser menor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                           if(rangeEnd < rangeSart){
                               JOptionPane.showMessageDialog(null, "El rango final no puede ser menor al rango inicial", "Error", JOptionPane.ERROR_MESSAGE);
                           }else{
                               if(rangeEnd<0){
                                   JOptionPane.showMessageDialog(null, "El rango final no puede ser menor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                               }else{
                                   if(Objects.equals(rangeEnd, rangeSart)){
                                     JOptionPane.showMessageDialog(null, "Los rangos inicial y final no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);  
                                   }      
                                    //Hacemos la selección
                                    Table selectionResult = dbManager.between(rangeSart, rangeEnd, table);

                                    //Cargamos la selección a la vista
                                    loadTable(selectionResult, window.getSelectionTable(), dataManager.dictionaryToArrayString());

                                    //Hacemos la proyección
                                    List<List<String>> projection = dbManager.projection(attrs, selectionResult);

                                    //Eliminamos las tuplas repetidas
                                    List<List<String>>  filteredTable = dbManager.filterRepeatedTuples(projection);

                                    //Cargamos la proyeccion con las tuplas filtradas
                                    loadTableFromArrayString(filteredTable, window.getProyectionTable(), attrs);

                                    JOptionPane.showMessageDialog(null, projection.size() + " resultados");
                               }
                           }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El valor final no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El valor inicial no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun campo para la proyeccion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Los valores del rango no son válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    private static void refreshTable() {
        Table table = dataManager.loadTable("descriptor.txt", "EMPLOYEES.txt");
        attrs = new ArrayList();

        //Cargamos la tabla completa
        loadTable(table, window.getFullTable(), dataManager.dictionaryToArrayString());

        //Cargamos los atributos
        window.addCheckbox(dataManager.dictionaryToArrayString());
        
        loadTable(null, window.getSelectionTable(), dataManager.dictionaryToArrayString());
        loadTableFromArrayString(null, window.getProyectionTable(), attrs);

        JOptionPane.showMessageDialog(null, "Tabla actualizada");
    }

    @Override
    public void onSelected(String attr) {
        attrs.add(attr);
    }

    @Override
    public void onUnselected(String attr) {
        attrs.remove(attr);
    }

    @Override
    public void onMenuItemSelected(String option) {
        switch (option) {
            case "Refresh":
                refreshTable();
                break;
                
            case "Run":
                makeQuery(table);
                break;
                
            case "Close":
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
                break;
        }
    }

}
