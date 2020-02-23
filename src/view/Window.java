package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Table;
import model.Tuple;
import model.TupleEmployee;

public class Window extends JFrame {

    private JTabbedPane tabbedPanel;
    
    private JPanel panelFullTable;
    private JPanel panelSelecitionTable;
    private JPanel panelProyectionTable;
    private JPanel optionsPanel;

    private  DefaultTableModel modelFullTable;
    private  DefaultTableModel modelSelectionTable;
    private  DefaultTableModel modelProyectionTable;
    
    private JTable fullTable;
    private JTable selectionTable;
    private JTable proyectionTable;
    
    private final String font = "Arial";
    private final int font_size = 16;
    private final int row_height = 26;
    
    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Manejador");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //setUndecorated(true);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        //Panel Pestañas
        tabbedPanel = new JTabbedPane();
        tabbedPanel.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
        
        panelFullTable = new JPanel();
        panelFullTable.setLayout(new BorderLayout());
        
        panelSelecitionTable = new JPanel();
        panelSelecitionTable.setLayout(new BorderLayout());
        
        panelProyectionTable = new JPanel();
        panelProyectionTable.setLayout(new BorderLayout());
        
        modelFullTable = new DefaultTableModel();
        modelSelectionTable = new DefaultTableModel();
        modelProyectionTable = new DefaultTableModel();
        
        fullTable = new JTable(modelFullTable);
        fullTable.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
        fullTable.setRowHeight(row_height);
        
        selectionTable = new JTable(modelSelectionTable);
        selectionTable.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
        selectionTable.setRowHeight(row_height);
        
        proyectionTable = new JTable(modelProyectionTable);
        proyectionTable.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
        proyectionTable.setRowHeight(row_height);
        
        panelFullTable.add(BorderLayout.CENTER, new JScrollPane(fullTable));
        panelSelecitionTable.add(BorderLayout.CENTER, new JScrollPane(selectionTable));
        panelProyectionTable.add(BorderLayout.CENTER, new JScrollPane(proyectionTable));
        
        tabbedPanel.addTab("Tabla Completa", panelFullTable);
        tabbedPanel.addTab("Seleción", panelSelecitionTable);
        tabbedPanel.addTab("Proyección", panelProyectionTable);
        //Panel de Pestañas
        
        //Panel de opciones
        optionsPanel = new JPanel();
        optionsPanel.setSize(50, 500);
        
        panelFullTable.setLayout(new BoxLayout(panelFullTable, BoxLayout.Y_AXIS));
        
        JTextField tfRangeStart = new JTextField(8);
        JTextField tfRangeEnd = new JTextField(8);
        
        optionsPanel.add(tfRangeStart);
        optionsPanel.add(tfRangeEnd);
        
        //Panel de Opciones
        
        add(BorderLayout.CENTER, tabbedPanel);
        add(BorderLayout.WEST, optionsPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTabbedPane getTabbedPanel() {
        return tabbedPanel;
    }

    public void setTabbedPanel(JTabbedPane tabbedPanel) {
        this.tabbedPanel = tabbedPanel;
    }

    public JPanel getPanelFullTable() {
        return panelFullTable;
    }

    public void setPanelFullTable(JPanel panelFullTable) {
        this.panelFullTable = panelFullTable;
    }

    public JPanel getPanelSelecitionTable() {
        return panelSelecitionTable;
    }

    public void setPanelSelecitionTable(JPanel panelSelecitionTable) {
        this.panelSelecitionTable = panelSelecitionTable;
    }

    public JPanel getPanelProyectionTable() {
        return panelProyectionTable;
    }

    public void setPanelProyectionTable(JPanel panelProyectionTable) {
        this.panelProyectionTable = panelProyectionTable;
    }

    public DefaultTableModel getModelFullTable() {
        return modelFullTable;
    }

    public void setModelFullTable(DefaultTableModel modelFullTable) {
        this.modelFullTable = modelFullTable;
    }

    public DefaultTableModel getModelSelectionTable() {
        return modelSelectionTable;
    }

    public void setModelSelectionTable(DefaultTableModel modelSelectionTable) {
        this.modelSelectionTable = modelSelectionTable;
    }

    public DefaultTableModel getModelProyectionTable() {
        return modelProyectionTable;
    }

    public void setModelProyectionTable(DefaultTableModel modelProyectionTable) {
        this.modelProyectionTable = modelProyectionTable;
    }

    public JTable getFullTable() {
        return fullTable;
    }

    public void setFullTable(JTable fullTable) {
        this.fullTable = fullTable;
    }

    public JTable getSelectionTable() {
        return selectionTable;
    }

    public void setSelectionTable(JTable selectionTable) {
        this.selectionTable = selectionTable;
    }

    public JTable getProyectionTable() {
        return proyectionTable;
    }

    public void setProyectionTable(JTable proyectionTable) {
        this.proyectionTable = proyectionTable;
    }
    
    

}
