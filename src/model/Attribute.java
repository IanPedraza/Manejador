package model;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private String fieldName;
    private int initialPosition;
    private int lenght;
    private String type;
    private String initialValue;
    private String lowestValue;
    private String highestValue;

    public Attribute(String fieldName, int initialPosition, int lenght, String type, String initialValue, String lowestValue, String highestValue) {
        this.fieldName = fieldName;
        this.initialPosition = initialPosition;
        this.lenght = lenght;
        this.type = type;
        this.initialValue = initialValue;
        this.lowestValue = lowestValue;
        this.highestValue = highestValue;
    }

    public Attribute() {
        this.fieldName = "";
        this.initialPosition = 0;
        this.lenght = 0;
        this.type = "";
        this.initialValue = "";
        this.lowestValue = "";
        this.highestValue = "";
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

    public String getLowestValue() {
        return lowestValue;
    }

    public void setLowestValue(String lowestValue) {
        this.lowestValue = lowestValue;
    }

    public String getHighestValue() {
        return highestValue;
    }

    public void setHighestValue(String highestValue) {
        this.highestValue = highestValue;
    }

    @Override
    public String toString() {
        /*
         private String fieldName;
         private int initialPosition;
         private int lenght;
         private String type;
         private String initialValue;
         private String lowestValue;
         private String highestValue;
         */

        return "\nfieldName: " + fieldName
                + "\ninitialPosition: " + initialPosition
                + "\nlenght: " + lenght
                + "\ntype: " + type
                + "\ninitialValue: " + initialValue
                + "\nlowestValue: " + lowestValue
                + "\nhighestValue: " + highestValue
                + "\n**************************************";
    }
    
     public List<String> toArrayString(){
        List<String> list = new ArrayList();
       
        list.add(String.valueOf(fieldName));
        list.add(String.valueOf(initialPosition));
        list.add(String.valueOf(initialPosition));
        list.add(String.valueOf(type));
        list.add(String.valueOf(initialValue));
        list.add(String.valueOf(lowestValue));
        list.add(String.valueOf(highestValue));
        
        return list;
    }

}
