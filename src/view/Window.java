package view;

import interfaces.AttributeSelectedListener;
import interfaces.MenuListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
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
    private JPanel chekboxPanel;
    private JPanel panelDictionary;

    private DefaultTableModel modelFullTable;
    private DefaultTableModel modelSelectionTable;
    private DefaultTableModel modelProyectionTable;
    private DefaultTableModel modelDictionary;

    private JTable fullTable;
    private JTable dictionaryTable;
    private JTable selectionTable;
    private JTable proyectionTable;

    private JTextField tfRangeStart;
    private JTextField tfRangeEnd;

    private JButton btnQuery;
    private JButton btn_refresh;

    private final String font = "Arial";
    private final int font_size = 16;
    private final int row_height = 26;
    private final java.awt.Color bgColor = new java.awt.Color(0, 66, 118);
    private final Color textColor = Color.WHITE;

    private AttributeSelectedListener listener;
    private MenuListener menuListener;

    public Window(AttributeSelectedListener listener, MenuListener menuListener) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("File Descriptor");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());

        this.listener = listener;
        this.menuListener = menuListener;

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Error al cargar el tema");
        }

        try {
            initComponents();
        } catch (IOException ex) {
            System.out.println("Error al cargar la imagen");
        }
    }

    private void initComponents() throws IOException {
        //Menu
        JMenuBar barra = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu runMenu = new JMenu("Run");
        JMenu windowMenu = new JMenu("Window");
        
        JMenuItem itemRefresh = new JMenuItem(new AbstractAction("Refresh") {
            public void actionPerformed(ActionEvent e) {
                menuListener.onMenuItemSelected("Refresh");
            }
        });
        
        JMenuItem itemRun = new JMenuItem(new AbstractAction("Run") {
            public void actionPerformed(ActionEvent e) {
                menuListener.onMenuItemSelected("Run");
            }
        });
        
        JMenuItem itemClose = new JMenuItem(new AbstractAction("Close") {
            public void actionPerformed(ActionEvent e) {
                menuListener.onMenuItemSelected("Close");
            }
        });
        
        itemRefresh.setIcon(new ImageIcon("images/ic_refres_small.png"));
        itemRun.setIcon(new ImageIcon("images/ic_run_small.png"));

        fileMenu.add(itemRefresh);
        runMenu.add(itemRun);
        windowMenu.add(itemClose);
        
        barra.add(fileMenu);
        barra.add(runMenu);
        barra.add(windowMenu);
        setJMenuBar(barra);
        //Menu
        
        JPanel panelDivider = new JPanel();
        panelDivider.setLayout(new BorderLayout());
        
        //Icons Menu        
        JPanel menuIcons = new JPanel();
        menuIcons.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        menuIcons.setPreferredSize(new Dimension(0, 45));
        menuIcons.setBackground(Color.WHITE);
        
        BufferedImage ic_refresh = ImageIO.read(new File("images/ic_refresh.png"));
        btn_refresh = new JButton(new ImageIcon(ic_refresh));
        btn_refresh.setBorder(BorderFactory.createEmptyBorder());
        btn_refresh.setPreferredSize(new Dimension(30, 30));
        btn_refresh.setSize(30, 30);    
        btn_refresh.setContentAreaFilled(false);
        
        btn_refresh.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                menuListener.onMenuItemSelected("Refresh");
            }
        });
        
        
        BufferedImage ic_run = ImageIO.read(new File("images/ic_run.png"));
        JButton btn_run = new JButton(new ImageIcon(ic_run));
        btn_run.setBorder(BorderFactory.createEmptyBorder());
        btn_run.setPreferredSize(new Dimension(30, 30));
        btn_run.setSize(30, 30);    
        btn_run.setContentAreaFilled(false);
        
        btn_run.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                menuListener.onMenuItemSelected("Run");
            }
        });
        
        menuIcons.add(btn_refresh);  
        menuIcons.add(btn_run);  
        //Icons Menu
        
        //Panel Pestañas
        tabbedPanel = new JTabbedPane();
        tabbedPanel.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
        tabbedPanel.setBackground(Color.WHITE);

        panelFullTable = new JPanel();
        panelFullTable.setLayout(new BorderLayout());

        panelSelecitionTable = new JPanel();
        panelSelecitionTable.setLayout(new BorderLayout());

        panelProyectionTable = new JPanel();
        panelProyectionTable.setLayout(new BorderLayout());
        
        panelDictionary = new JPanel();
        panelDictionary.setLayout(new BorderLayout());

        modelFullTable = new DefaultTableModel();
        modelSelectionTable = new DefaultTableModel();
        modelProyectionTable = new DefaultTableModel();
        modelDictionary = new DefaultTableModel();
           
        dictionaryTable = new JTable(modelDictionary);
        dictionaryTable.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
        dictionaryTable.setRowHeight(row_height);                  
                  
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
        panelDictionary.add(BorderLayout.CENTER, new JScrollPane(dictionaryTable));

        tabbedPanel.addTab("Diccionario", panelDictionary);
        tabbedPanel.addTab("Tabla Completa", panelFullTable);
        tabbedPanel.addTab("Seleción", panelSelecitionTable);
        tabbedPanel.addTab("Proyección", panelProyectionTable);
        tabbedPanel.setSelectedIndex(1);
        
        panelDivider.add(BorderLayout.CENTER, new JScrollPane(tabbedPanel));
        panelDivider.add(BorderLayout.NORTH, menuIcons);
        //Panel de Pestañas

        //Panel de opciones
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridBagLayout());
        optionsPanel.setPreferredSize(new Dimension(300, 0));
        optionsPanel.setBackground(bgColor);
        optionsPanel.setBorder( BorderFactory.createEmptyBorder(0,0,0,0) );

        chekboxPanel = new JPanel();
        chekboxPanel.setBackground(bgColor);
        chekboxPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        BufferedImage logo = ImageIO.read(new File("images/logo.png"));
        JLabel labelLogo = new JLabel(new ImageIcon(logo));
        labelLogo.setSize(200, 0);

        JLabel labelRange = new JLabel("Ingrese el rango de la consulta:");
        labelRange.setFont(new Font(font, Font.BOLD, 18));
        labelRange.setForeground(textColor);

        tfRangeStart = new JTextField(10);
        tfRangeStart.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));

        JLabel labelBetween = new JLabel("BETWEEN");
        labelBetween.setFont(new Font(font, Font.ROMAN_BASELINE, 14));
        labelBetween.setForeground(textColor);

        tfRangeEnd = new JTextField(10);
        tfRangeEnd.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));

        JTextArea labelAttrs = new JTextArea("Selecciona los campos para\nhacer la selección:");
        labelAttrs.setFont(new Font(font, Font.BOLD, 18));
        labelAttrs.setEditable(false);
        labelAttrs.setBackground(bgColor);
        labelAttrs.setForeground(textColor);

        btnQuery = new JButton("Hacer consulta");
        btnQuery.setFont(new Font(font, Font.BOLD, font_size));
        btnQuery.setBackground(bgColor);
        btnQuery.setContentAreaFilled(false);

        btnQuery.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 5),
                BorderFactory.createLineBorder(bgColor, 8)));
        btnQuery.setForeground(textColor);

        btnQuery.setRolloverEnabled(true);

        optionsPanel.add(labelLogo, gbc);
        gbc.insets = new Insets(70, 0, 0, 0);
        optionsPanel.add(labelRange, gbc);
        gbc.insets = new Insets(20, 0, 0, 0);
        optionsPanel.add(tfRangeStart, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        optionsPanel.add(labelBetween, gbc);
        optionsPanel.add(tfRangeEnd, gbc);
        gbc.insets = new Insets(30, 0, 0, 0);
        optionsPanel.add(labelAttrs, gbc);
        gbc.insets = new Insets(10, 0, 0, 0);
        optionsPanel.add(chekboxPanel, gbc);
        gbc.insets = new Insets(50, 0, 0, 0);
        optionsPanel.add(btnQuery, gbc);

        //Panel de Opciones
        add(BorderLayout.CENTER, panelDivider);
        add(BorderLayout.WEST, new JScrollPane(optionsPanel));
        
        tfRangeStart.setSize(80,80);
        setSize(1800, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addCheckbox(List<String> list) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(15, 0, 0, 0);

        chekboxPanel.removeAll();

        for (String option : list) {
            JCheckBox cb = new JCheckBox(option);
            cb.setFont(new Font(font, Font.ROMAN_BASELINE, font_size));
            cb.setBackground(bgColor);
            cb.setForeground(textColor);

            cb.addItemListener((ItemEvent e) -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    listener.onSelected(option);
                } else {
                    listener.onUnselected(option);
                }
            });

            chekboxPanel.add(cb, gbc);
            gbc.insets = new Insets(10, 0, 0, 0);
        }

        chekboxPanel.revalidate();
        chekboxPanel.repaint();
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

    public JPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(JPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }

    public JPanel getChekboxPanel() {
        return chekboxPanel;
    }

    public void setChekboxPanel(JPanel chekboxPanel) {
        this.chekboxPanel = chekboxPanel;
    }

    public JTextField getTfRangeStart() {
        return tfRangeStart;
    }

    public void setTfRangeStart(JTextField tfRangeStart) {
        this.tfRangeStart = tfRangeStart;
    }

    public JTextField getTfRangeEnd() {
        return tfRangeEnd;
    }

    public void setTfRangeEnd(JTextField tfRangeEnd) {
        this.tfRangeEnd = tfRangeEnd;
    }

    public JButton getBtnQuery() {
        return btnQuery;
    }

    public void setBtnQuery(JButton btnQuery) {
        this.btnQuery = btnQuery;
    }

    public AttributeSelectedListener getListener() {
        return listener;
    }

    public void setListener(AttributeSelectedListener listener) {
        this.listener = listener;
    }

    public JTable getDictionaryTable() {
        return dictionaryTable;
    }

    public void setDictionaryTable(JTable dictionaryTable) {
        this.dictionaryTable = dictionaryTable;
    }

    
    
}
