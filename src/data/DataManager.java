package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Attribute;
import model.Table;
import model.TupleEmployee;
import static model.Utils.getString;

public class DataManager {

    private List<Attribute> dictionary;

    public DataManager() {
        this.dictionary = new ArrayList();
    }

    //recibe los directorios de descriptor 
    public Table loadTable(String descriptorPath, String tablePath) {
        this.dictionary = new ArrayList();
        Table table = new Table();

        try {
            createDictionary(descriptorPath);
            table = createTable(tablePath);
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo: Formato inválido");
        }

        return table;
    }

    private void createDictionary(String descriptorPath) throws FileNotFoundException {
        //Abrir el descriptor
        File file = new File(descriptorPath);
        Scanner scanner = new Scanner(file);

        //Construir datos
        while (scanner.hasNextLine()) {
            String[] decodificador = scanner.nextLine().split(",");

            Attribute attribute = new Attribute(
                    getString(decodificador[0]),
                    Integer.parseInt(decodificador[1]),
                    Integer.parseInt(decodificador[2]),
                    getString(decodificador[3]),
                    getString(decodificador[4]),
                    getString(decodificador[5]),
                    getString(decodificador[6])
            );

            this.dictionary.add(attribute);
        }
    }

    private Table createTable(String tablePath) throws FileNotFoundException {
        //Abrir la tabla
        File file = new File(tablePath);
        Scanner scanner = new Scanner(file);

        //Instanciar tabla
        Table table = new Table();

        //Construir tabla
        boolean isFirstTuple = true;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (isFirstTuple) {
                table.setTableName(line);
                isFirstTuple = false;
            } else {
                //Creamos una lista de los valores que vamos a recoger de esta tupla
                List<String> values = new ArrayList();

                //Recogemos los valores de la tupla deacuerdo a los campos del diccionario de datos
                for (Attribute a : this.dictionary) {
                    String value = line.substring(a.getInitialPosition(), a.getInitialPosition() + a.getLenght());
                    values.add(value.trim());
                }

                //Creamos nuestra tupla a partir de los datos obtenidos del diccionario de datos y el archivo
                TupleEmployee tupleEmployee = new TupleEmployee(
                        Integer.parseInt(values.get(0)),
                        getString(values.get(1)),
                        getString(values.get(2)),
                        getString(values.get(3)),
                        getString(values.get(4)),
                        getString(values.get(5)),
                        getString(values.get(6)),
                        Double.parseDouble(values.get(7)),
                        Double.parseDouble(values.get(8)),
                        Integer.parseInt(values.get(9)),
                        Integer.parseInt(values.get(10))
                );

                //Agregamos la tupla creada a la tabla
                table.addTuple(tupleEmployee);
            }
        }

        return table;
    }

    public List<String> dictionaryToArrayString() {
        List<String> list = new ArrayList();

        for (Attribute a : dictionary) {
            list.add(a.getFieldName());
        }

        return list;
    }

    public List<Attribute> getDictionary() {
        return dictionary;
    }

    public void setDictionary(List<Attribute> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getColumns() {
        List<String> list = new ArrayList();

        list.add("fieldName");
        list.add("initialPosition");
        list.add("lenght");
        list.add("type");
        list.add("initialValue");
        list.add("lowestValue");
        list.add("highestValue");

        return list;
    }

}
