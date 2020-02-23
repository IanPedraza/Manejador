package view;

import data.DataManager;
import data.dbManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Table;
import static model.Utils.printProjection;
import static model.Utils.printTable;

public class Manejador {

   
    public static void main(String[] args) {
        ViewController viewController = new ViewController();
        viewController.start();
    }


}
