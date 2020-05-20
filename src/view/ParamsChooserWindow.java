package view;

import interfaces.Callback;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static view.ViewController.makeQuery;

public class ParamsChooserWindow extends JFrame {

    private final Callback<String> callback;

    private String[] values1;
    private String[] values2;
    
    private String value1;
    private String value2;

    private JComboBox combo1;
    private JComboBox combo2;
    
    private JLabel label1;
    private JLabel label2;

    private JPanel panel;
    
    private JButton button;
    
    public ParamsChooserWindow(String[] values1, String[] values2, Callback<String> callback1) {
        this.callback = callback1;
        this.values1 = values1;
        this.values2 = values2;
        this.value1 = null;
        this.value2 = null;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Campos del Join"); 
        setLayout(new BorderLayout());
        
        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        
        label1 = new JLabel("Escoja el campo para la tabla EMPLOYEES");
        label1.setBounds(20, 20, 400, 35);
        label1.setFont(new Font("Arial", Font.PLAIN, 14));
        
        combo1 = new JComboBox();
        combo1.setBounds(20, 60, 200, 35);
        combo1.setModel(new DefaultComboBoxModel<>(values1));
        combo1.setFont(new Font("Arial", Font.PLAIN, 14));
        combo1.setEditable(false);
        
        label2 = new JLabel("Escoja el campo para la tabla DEPARTMENTS");
        label2.setBounds(20, 100, 400, 35);
        label2.setFont(new Font("Arial", Font.PLAIN, 14));
      
        combo2 = new JComboBox();
        combo2.setBounds(20, 140, 200, 35);
        combo2.setModel(new DefaultComboBoxModel<>(values2));
        combo2.setFont(new Font("Arial", Font.PLAIN, 14));
        combo2.setEditable(false);   
        
        button = new JButton("ACEPTAR");
        button.setBounds(20, 200, 100, 35);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        
        button.addActionListener((ActionEvent e) -> {
            onReady();
        });
        
        panel.add(label1);
        panel.add(label2);
        panel.add(combo1);
        panel.add(combo2);
        panel.add(button);
        
        add(panel);
        
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);        
    }
    
    private void onReady() {
        callback.onChooseOption(combo1.getSelectedItem().toString(), combo2.getSelectedItem().toString());
        dispose();
    }

}
