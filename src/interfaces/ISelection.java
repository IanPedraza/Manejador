package interfaces;

import model.Table;

public interface ISelection {
    public Table between(Double lowestValue, Double highestValue, Table table);
    public Table filterRepeatedTuples(Table table);
}
