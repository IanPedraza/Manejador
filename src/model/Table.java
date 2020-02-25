package model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Tuple> tuplesList;
    private String tableName;

    public Table(List<Tuple> tuplesList, String tableName) {
        this.tuplesList = tuplesList;
        this.tableName = tableName;
    }

    public Table(Table t) {
        this.tuplesList = new ArrayList();
        this.tuplesList.addAll(t.tuplesList);
        this.tableName = t.tableName;
    }

    public Table() {
        this.tuplesList = new ArrayList();
        this.tableName = "";
    }

    public void addTuple(Tuple tuple) {
        this.tuplesList.add(tuple);
    }

    public void deleteTuple(Tuple tuple) {
        this.tuplesList.remove(tuple);
    }

    public List<Tuple> getTuplesList() {
        return tuplesList;
    }

    public void setTuplesList(List<Tuple> tuplesList) {
        this.tuplesList = tuplesList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
