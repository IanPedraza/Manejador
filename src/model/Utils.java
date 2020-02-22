package model;

import java.util.List;

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
    
    public static void printProjection(List<List<String>> projection){
        for(List<String> l : projection){
            String joinedString = String.join("\t", l);
            System.out.println(joinedString);
        }
    }
            

}
