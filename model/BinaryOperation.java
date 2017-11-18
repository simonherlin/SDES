package model;

class BinaryOperation {
    public int binToDec(int...bits) {
        int temp=0;
        int base = 1;
        for(int i=bits.length-1 ; i>=0;i--){
            temp = temp + (bits[i]*base);
            base = base * 2 ;
        }
        return temp;
    }

    public int[] decToBinArr(int no){
        if(no==0){
            int[] zero = new int[2];
            zero[0] = 0;
            zero[1] = 0;
            return zero;
        }
        int[] temp = new int[10] ;
        int count = 0 ;
        for(int i= 0 ; no!= 0 ; i++){
            temp[i] = no % 2;
            no = no/2;
            count++;
        }
        int[] temp2 = new int[count];
        for(int i=count-1, j=0;i>=0 && j<count;i--,j++){
            temp2[j] = temp[i];
        }
        if(count<2){
            temp = new int[2];
            temp[0] = 0;
            temp[1] = temp2[0];
            return temp;
        }
        return temp2;
    }

    public int[] decToBinArrLg8(int no){
        if(no==0){
            int[] zero = new int[2];
            zero[0] = 0;
            zero[1] = 0;
            return zero;
        }
        int[] temp = new int[10] ;
        for(int i= 0 ; no!= 0 ; i++){
            temp[i] = no % 2;
            no = no/2;
        }
        int[] temp2 = new int[8];
        for(int i=8-1, j=0;i>=0 && j<8;i--,j++){
            temp2[j] = temp[i];
        }
        return temp2;
    }
}
