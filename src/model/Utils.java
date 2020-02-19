package model;

public class Utils {

    public static String getString(String s) {
        if (s == null) {
            return "";
        } else {
            return s;
        }
    }

    public static void printTable(Table table) {
        for (Tuple t : table.getTuplesList()) {
            TupleEmployee e = (TupleEmployee) t;
            System.out.println(e);
        }
    }

}
