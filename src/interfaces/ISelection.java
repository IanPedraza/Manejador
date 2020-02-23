package interfaces;

import java.util.List;
import model.Table;

public interface ISelection {
    public Table between(Double lowestValue, Double highestValue, Table table);
    public List<List<String>> filterRepeatedTuples(List<List<String>> table);
}
