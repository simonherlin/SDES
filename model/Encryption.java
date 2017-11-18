package model;

import view.View;

public class Encryption{
    final int[][] S0 = { {1,0,3,2} , {3,2,1,0} , {0,2,1,3} , {3,1,3,2} } ;
    final int[][] S1 = { {0,1,2,3},  {2,0,1,3}, {3,0,1,0}, {2,1,0,3}} ;
    private int[] k1;
    private int[] k2;
    private int[] bitOfChar;
    private boolean flag;
    private View view;
    private BinaryOperation binaryOperation;

    public Encryption(){
        this.k1 = new int[8];
        this.k2 = new int[8];
        this.bitOfChar = new int[8];
        this.flag = true;
        this.view = new View();
    }

    private void SaveParameters(String text , int[] k1, int[] k2){
        this.bitOfChar = this.recupText(text);
        this.k1 = k1;
        this.k2 = k2;
    }

    private int[] recupText(String text){
        int[] pt = new int[8];
        char term;
        try{
            for(int i=0;i<8;i++){
                term = text.charAt(i);
                pt[i] = Integer.parseInt(Character.toString(term));
                if(pt[i] !=0 && pt[i]!=1){
                    this.view.errorText();
                    this.flag = false;
                }
            }
        }
        catch(Exception e){
            this.view.errorText();
            this.flag = false;
        }
        return pt;
    }

    private void InitialPermutation(){
        int[] temp = new int[8];
        temp[0] = bitOfChar[1];
        temp[1] = bitOfChar[5];
        temp[2] = bitOfChar[2];
        temp[3] = bitOfChar[0];
        temp[4] = bitOfChar[3];
        temp[5] = bitOfChar[7];
        temp[6] = bitOfChar[4];
        temp[7] = bitOfChar[6];
        bitOfChar = temp;
    }

    private void InverseInitialPermutation(){
        int[] temp = new int[8];
        temp[0] = bitOfChar[3];
        temp[1] = bitOfChar[0];
        temp[2] = bitOfChar[2];
        temp[3] = bitOfChar[4];
        temp[4] = bitOfChar[6];
        temp[5] = bitOfChar[1];
        temp[6] = bitOfChar[7];
        temp[7] = bitOfChar[5];
        bitOfChar = temp;
    }

    private int[] permutationXOR(int[] R, int[] SK){
        int[] temp = new int[8];
        temp[0]  = R[3];
        temp[1]  = R[0];
        temp[2]  = R[1];
        temp[3]  = R[2];
        temp[4]  = R[1];
        temp[5]  = R[2];
        temp[6]  = R[3];
        temp[7]  = R[0];

        temp[0] = temp[0] ^ SK[0];
        temp[1] = temp[1] ^ SK[1];
        temp[2] = temp[2] ^ SK[2];
        temp[3] = temp[3] ^ SK[3];
        temp[4] = temp[4] ^ SK[4];
        temp[5] = temp[5] ^ SK[5];
        temp[6] = temp[6] ^ SK[6];
        temp[7] = temp[7] ^ SK[7];
        return temp;
    }

    private int[] sBox(int line1, int line2, int column1, int column2, boolean choiceSbox){
        this.binaryOperation = new BinaryOperation();

        int row = this.binaryOperation.binToDec(line1,line2);
        int col = this.binaryOperation.binToDec(column1,column2);
        int result;
        if (choiceSbox){
            result = S0[row][col];
        }
        else{
            result = S1[row][col];
        }
        return this.binaryOperation.decToBinArr(result);
    }

    private int[] mappingF(int[] R, int[] SK){
        int[] temp = this.permutationXOR(R,SK);
        int[] out1 = this.sBox(temp[0],temp[3],temp[1],temp[2],true);
        int[] out2 = this.sBox(temp[4],temp[7],temp[5],temp[6],false);
        int[] out = new int[4];
        out[0] = out1[1];
        out[1] = out2[1];
        out[2] = out2[0];
        out[3] = out1[0];
        return out;
    }

    private int[] functionFk(int[] L, int[] R,int[] SK){
        int[] temp = new int[4];
        int[] out = new int[8];
        temp = mappingF(R,SK);
        out[0] = L[0] ^ temp[0];
        out[1] = L[1] ^ temp[1];
        out[2] = L[2] ^ temp[2];
        out[3] = L[3] ^ temp[3];
        out[4] = R[0];
        out[5] = R[1];
        out[6] = R[2];
        out[7] = R[3];
        return out;
    }

    private int[] switchSW(int[] in){

        int[] temp = new int[8];

        temp[0] = in[4];
        temp[1] = in[5];
        temp[2] = in[6];
        temp[3] = in[7];

        temp[4] = in[0];
        temp[5] = in[1];
        temp[6] = in[2];
        temp[7] = in[3];

        return temp;
    }

    private int[] constructionLeftRightHalf(int i){
        int[] temp = new int[4];
        temp[0] = this.bitOfChar[i];
        temp[1] = this.bitOfChar[i+1];
        temp[2] = this.bitOfChar[i+2];
        temp[3] = this.bitOfChar[i+3];
        return temp;
    }

    public int[] encrypt(String plaintext , int[] LK, int[] RK){
        SaveParameters(plaintext,LK,RK);
        InitialPermutation();
        int[] LH = this.constructionLeftRightHalf(0);
        int[] RH = this.constructionLeftRightHalf(4);

        //first round with sub-key K1
        int[] r1 = new int[8];
        r1 = functionFk(LH,RH,k1);

        //Switch the left half & right half of about output
        int[] temp = new int[8];
        temp = switchSW(r1);

        // again saperate left half & right half for second round
        LH[0] = temp[0];
        LH[1] = temp[1];
        LH[2] = temp[2];
        LH[3] = temp[3];

        RH[0] = temp[4];
        RH[1] = temp[5];
        RH[2] = temp[6];
        RH[3] = temp[7];

        //second round with sub-key K2
        int[] r2 = new int[8];
        r2 = functionFk(LH,RH,this.k2);

        bitOfChar = r2;


        InverseInitialPermutation();

        //Encryption done... return 8-bit output .
        return bitOfChar;
    }
}
