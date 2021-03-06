package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Attribute;
import model.Table;
import model.TupleDepartments;
import model.TupleEmployee;
import static model.Utils.getString;

public class DataManager {

    private List<Attribute> dictionary;
    private List<Attribute> dictionaryDepartments;

    public DataManager() {
        this.dictionary = new ArrayList();
        this.dictionaryDepartments = new ArrayList();
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
    
    //recibe los directorios de descriptor 
    public Table loadTableDepartments(String descriptorPath, String tablePath) {
        this.dictionaryDepartments = new ArrayList();
        Table table = new Table();

        try {
            createDictionaryDepartments(descriptorPath);
            table = createTableDepartments(tablePath);
        } catch (Exception e) {
            System.out.println("Error al abrir el archivo: Formato inválido: " + e);
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
    
    private void createDictionaryDepartments(String descriptorPath) throws FileNotFoundException {
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

            this.dictionaryDepartments.add(attribute);
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

    private Table createTableDepartments(String tablePath) throws FileNotFoundException {
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
                for (Attribute a : this.dictionaryDepartments) {
                    String value = line.substring(a.getInitialPosition(), a.getInitialPosition() + a.getLenght());
                    values.add(value.trim());
                }
                

                //Creamos nuestra tupla a partir de los datos obtenidos del diccionario de datos y el archivo
                TupleDepartments tupleDepartment = new TupleDepartments(
                        Integer.parseInt(values.get(0)),
                        getString(values.get(1)),
                        Integer.parseInt(values.get(2)),
                        Integer.parseInt(values.get(3))
                );

                //Agregamos la tupla creada a la tabla
                table.addTuple(tupleDepartment);
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
    
    public List<String> dictionaryDepartmentsToArrayString() {
        List<String> list = new ArrayList();

        for (Attribute a : dictionaryDepartments) {
            list.add(a.getFieldName());
        }

        return list;
    }

    public List<Attribute> getDictionary() {
        return dictionary;
    }
    
    public List<Attribute> getDictionaryDepartments() {
        return dictionaryDepartments;
    }

    public void setDictionary(List<Attribute> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getColumns() {
        List<String> list = new ArrayList();

        list.add("FIELD_NAME");
        list.add("INITIAL_POSITION");
        list.add("LENGHT");
        list.add("TYPE");
        list.add("INITIAL_VALUE");
        list.add("LOWEST_VALUE");
        list.add("HIGHEST_VALUE");

        return list;
    }

}
