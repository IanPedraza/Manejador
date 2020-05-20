package view;

import data.DataManager;
import data.DataBaseManager;
import interfaces.AttributeSelectedListener;
import interfaces.Callback;
import interfaces.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Attribute;
import model.Table;
import model.Tuple;
import model.TupleDepartments;
import model.TupleEmployee;
import model.TupleProducto;
import model.TupleProductoCartesiano;

public class ViewController implements AttributeSelectedListener, MenuListener {

    private static Window window;
    private static List<String> attrs;

    private JButton buttonQuery;
    private JButton buttonQueryJoin;
    private static JTextField tfStart;
    private static JTextField tfEnd;

    private static DataManager dataManager;
    private static DataBaseManager dbManager;

    private Table table;
    private Table tableDepartments;

    public void start() {
        dataManager = new DataManager();
        dbManager = new DataBaseManager();

        //Obtenemos la tabla
        table = dataManager.loadTable("data/descriptor.txt", "data/EMPLOYEES.txt");
        tableDepartments = dataManager.loadTableDepartments("data/descriptorDep.txt", "data/DEPARTMENTS.txt");

        //Creamos la ventana
        window = new Window(this, this);

        attrs = new ArrayList();

        buttonQuery = window.getBtnQuery();
        buttonQueryJoin = window.getBtnQueryJoin();
        tfStart = window.getTfRangeStart();
        tfEnd = window.getTfRangeEnd();

        buttonQuery.addActionListener((ActionEvent e) -> {
            makeQuery(table);
        });

        buttonQueryJoin.addActionListener((ActionEvent e) -> {
            makeJoin();
        });

        //Cargamos la tabla completa
        loadTable(table, window.getFullTable(), dataManager.dictionaryToArrayString());
        loadTableDictinary(dataManager.getDictionary(), window.getDictionaryTable(), dataManager.getColumns());

        loadTableDEpartments(tableDepartments, window.getFullTableDepartments(), dataManager.dictionaryDepartmentsToArrayString());
        loadTableDictinary(dataManager.getDictionaryDepartments(), window.getDictionaryTableDepartments(), dataManager.getColumns());

        //Cargamos los atributos
        //window.addCheckbox(dataManager.dictionaryToArrayString());
        List<String> checkboxAttrs = 
                getAttrs(dataManager.dictionaryToArrayString(), dataManager.dictionaryDepartmentsToArrayString());
        
        window.addCheckbox(checkboxAttrs);
    }
    
    private List<String> getAttrs(List<String> dictionary1,  List<String> dictionary2) {
        List<String> res = new ArrayList();
        
        for (String value : dictionary1){
            if (!res.contains(value)) {
                res.add(value);
            }
        }
        
        for (String value : dictionary2){
            if (!res.contains(value)) {
                res.add(value);
            }
        }
        
        return res;
    }

    public static void loadTableDictinary(List<Attribute> dictionary, JTable jtable, List<String> columnNames) {

        DefaultTableModel model = new DefaultTableModel(null, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Agregando table a la vista jtable recibida por parametros
        if (dictionary != null) {
            for (Attribute a : dictionary) {
                model.addRow(a.toArrayString().toArray());
            }
        } else {
            model = new DefaultTableModel();
            System.out.println("null");
        }

        model.fireTableDataChanged();
        jtable.setModel(model);
    }

    public static void loadTable(Table table, JTable jtable, List<String> columnNames) {
        DefaultTableModel model = new DefaultTableModel(null, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Agregando table a la vista jtable recibida por parametros
        if (table != null) {
            for (Tuple t : table.getTuplesList()) {
                TupleEmployee e = (TupleEmployee) t;
                model.addRow(e.toArrayString().toArray());
            }
        } else {
            model = new DefaultTableModel();
        }

        model.fireTableDataChanged();
        jtable.setModel(model);
    }

    public static void loadTableDEpartments(Table table, JTable jtable, List<String> columnNames) {
        DefaultTableModel model = new DefaultTableModel(null, columnNames.toArray()) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Agregando table a la vista jtable recibida por parametros
        if (table != null) {
            for (Tuple t : table.getTuplesList()) {
                TupleDepartments e = (TupleDepartments) t;
                model.addRow(e.toArrayString().toArray());
            }
        } else {
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
        if (data != null) {
            for (List<String> row : data) {
                //al modelo de la tablaba le agrega una fila
                model.addRow(row.toArray());
            }
        } else {
            model = new DefaultTableModel();
        }

        model.fireTableDataChanged();
        jtable.setModel(model);
    }

    public void makeJoin() {
        if (attrs.size() > 0) {
            ParamsChooserWindow paramsChooserWindow = new ParamsChooserWindow(
            dataManager.dictionaryToArrayString().stream().toArray(String[]::new), 
            dataManager.dictionaryDepartmentsToArrayString().stream().toArray(String[]::new), 
            new Callback<String>() {
                @Override
                public void onChooseOption(String option1, String option2) {
                    makeJoin(option1, option2);
                }
            });

            paramsChooserWindow.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun campo para la proyeccion del join", "Error", JOptionPane.ERROR_MESSAGE);
        }        
       
    }
    
    private void makeJoin(String field1, String field2) {
        Table producto = dbManager.productoCartesiano(table, tableDepartments);
        
        List<List<String>> productoAux = new ArrayList();

        for (Tuple t : producto.getTuplesList()) {
            TupleProducto tp = (TupleProducto) t;
            TupleProductoCartesiano tpc = dbManager.crearTupla(tp.getEmployee(), tp.getDepartment());
            productoAux.add(tpc.toArrayString());
        }
        
        loadTableFromArrayString(productoAux, window.getProductoJoinTable(), new TupleProductoCartesiano().NamestoArrayString());
        
        Table join = dbManager.join(field1, field2, producto);

        List<List<String>> joinAux = new ArrayList();

        for (Tuple t : join.getTuplesList()) {
            TupleProducto tp = (TupleProducto) t;
            TupleProductoCartesiano tpc = dbManager.crearTupla(tp.getEmployee(), tp.getDepartment());
            joinAux.add(tpc.toArrayString());
        }

        loadTableFromArrayString(joinAux, window.getSelectionTableJoin(), new TupleProductoCartesiano().NamestoArrayString());

        List<String> attributes = new ArrayList();
        attributes.add("DEPARTMENT_ID");
        attributes.add("FIRST_NAME");

        List<List<String>> projectionList = dbManager.projectionJoin(attrs, join);

        List<List<String>> fileteredList = dbManager.filterRepeatedTuples(projectionList);

        List<String> attributesBoth = new ArrayList();

        
        for (String atribute : attrs) {
            attributesBoth.add(atribute + " (" + table.getTableName() + ")");
        }

        for (String atribute : attrs) {
            attributesBoth.add(atribute + " (" + tableDepartments.getTableName() + ")");
        }

        loadTableFromArrayString(fileteredList, window.getProyectionTableJoin(), attributesBoth);
    }

    public static void makeQuery(Table table) {
        //Obetener Rangos
        Double rangeSart = 0.0;
        Double rangeEnd = 0.0;

        try {
            String valueStart = tfStart.getText();
            String valueEnd = tfEnd.getText();

            if (attrs.size() > 0) {
                if (!valueStart.isEmpty()) {
                    if (!valueEnd.isEmpty()) {
                        rangeSart = Double.parseDouble(valueStart);
                        rangeEnd = Double.parseDouble(valueEnd);

                        if (rangeSart < 0) {
                            JOptionPane.showMessageDialog(null, "El rango inicial no puede ser menor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (rangeEnd < rangeSart) {
                                JOptionPane.showMessageDialog(null, "El rango final no puede ser menor al rango inicial", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (rangeEnd < 0) {
                                    JOptionPane.showMessageDialog(null, "El rango final no puede ser menor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    if (Objects.equals(rangeEnd, rangeSart)) {
                                        JOptionPane.showMessageDialog(null, "Los rangos inicial y final no pueden ser iguales", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    //Hacemos la selección
                                    Table selectionResult = dbManager.between(rangeSart, rangeEnd, table);

                                    //Cargamos la selección a la vista
                                    loadTable(selectionResult, window.getSelectionTable(), dataManager.dictionaryToArrayString());

                                    //Hacemos la proyección
                                    List<List<String>> projection = dbManager.projection(attrs, selectionResult);

                                    //Eliminamos las tuplas repetidas
                                    List<List<String>> filteredTable = dbManager.filterRepeatedTuples(projection);

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
        Table table = dataManager.loadTable("data/descriptor.txt", "data/EMPLOYEES.txt");
        attrs = new ArrayList();

        //Cargamos la tabla completa
        loadTable(table, window.getFullTable(), dataManager.dictionaryToArrayString());

        //Cargamos los atributos
        window.addCheckbox(dataManager.dictionaryToArrayString());

        loadTable(null, window.getSelectionTable(), dataManager.dictionaryToArrayString());
        loadTableFromArrayString(null, window.getProyectionTable(), attrs);

        loadTableDictinary(dataManager.getDictionary(), window.getDictionaryTable(), dataManager.getColumns());

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
