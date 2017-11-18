package model;

public class Treament {
    private BinaryOperation binaryOperation;

    public Treament(){
        this.binaryOperation = new BinaryOperation();
    }
    public String letterToBitString(int i, String input){
        char c = input.charAt(i);
        int intOfChar = (int)c;
        int[] tabInt = this.binaryOperation.decToBinArr(intOfChar);
        return this.tabIntToString(tabInt);
    }

    public String bitToString(int[] data){
        int intOfChar = this.binaryOperation.binToDec(data);
        char c = (char)intOfChar;
        return String.valueOf('c');
    }

    private String tabIntToString(int[] tab){
        String temp = "";
        for(int j = 0;j<tab.length;j++){
            temp+=tab[j];
        }
        return temp;
    }

}
