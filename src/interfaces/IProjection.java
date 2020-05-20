package interfaces;

import java.util.List;
import model.Table;

public interface IProjection {
    public List<List<String>> projection(List<String> attrs, Table employeesTable);
    public List<List<String>> projectionJoin(List<String> attrs, Table table);
}
