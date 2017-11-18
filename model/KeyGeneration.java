package model;

import view.View;

public class KeyGeneration
{
    private int[] key;
    private int[] k1;
    private int[] k2;
    private boolean flagKey, flagError;
    private View view;

    public KeyGeneration(){
        this.key = new int[10];
        this.k1 = new int[8];
        this.k2 = new int[8];
        this.flagKey = false;
        this.flagError = false;
        this.view = new View();
    }

    public void GenerateKeys(String inputkey){
       this.key = this.recupKey(inputkey);
       if (!this.flagError){
           permutationP10();
           leftshiftLS1();
           this.k1 = permutationP8();
           leftshiftLS2();
           this.k2 = permutationP8();
           this.flagKey = true;
       }
    }

    private int[] recupKey(String inputkey){
        int[] key = new int[10];
        char term;
        try{
            for(int i=0;i<10;i++){
                term = inputkey.charAt(i);
                key[i] = Integer.parseInt(Character.toString(term));
                if(key[i] !=0 && key[i]!=1){
                    this.view.errorEnterkey();
                    this.flagError = true;
                }
            }
        }
        catch(Exception e){
            this.view.errorEnterkey();
            this.flagError = true;
        }
        return key;
    }

    private void permutationP10(){
        int[] temp = new int[10];
        temp[0] = key[2];
        temp[1] = key[4];
        temp[2] = key[1];
        temp[3] = key[6];
        temp[4] = key[3];
        temp[5] = key[9];
        temp[6] = key[0];
        temp[7] = key[8];
        temp[8] = key[7];
        temp[9] = key[5];
        key = temp;
    }

    private void leftshiftLS1(){
        int[] temp = new int[10];
        temp[0] = key[1];
        temp[1] = key[2];
        temp[2] = key[3];
        temp[3] = key[4];
        temp[4] = key[0];

        temp[5] = key[6];
        temp[6] = key[7];
        temp[7] = key[8];
        temp[8] = key[9];
        temp[9] = key[5];
        key = temp;
    }

    private void leftshiftLS2(){
        int[] temp = new int[10];
        temp[0] = key[2];
        temp[1] = key[3];
        temp[2] = key[4];
        temp[3] = key[0];
        temp[4] = key[1];

        temp[5] = key[7];
        temp[6] = key[8];
        temp[7] = key[9];
        temp[8] = key[5];
        temp[9] = key[6];
        key = temp;
    }

    private int[] permutationP8(){
        int[] temp = new int[8];
        temp[0] = key[5];
        temp[1] = key[2];
        temp[2] = key[6];
        temp[3] = key[3];
        temp[4] = key[7];
        temp[5] = key[4];
        temp[6] = key[9];
        temp[7] = key[8];
        return temp;
    }

    public boolean getFlagkey(){
        return this.flagKey;
    }

    public int[] getK1(){
        return this.k1;
    }

    public int[] getK2(){
        return this.k2;
    }
}
