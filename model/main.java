package model;

import view.View;

public class main {
    public static void main(String[] args){
        KeyGeneration keyGeneration = new KeyGeneration();
        Encryption encryption  = new Encryption();
        View view = new View();
        Treament treament = new Treament();
        int[] data = new int[8];
        String encrypt="", decrypt="";
        view.intro();
        //key generation
        keyGeneration.GenerateKeys(view.enterKey());
        if (!keyGeneration.getFlagkey()){
            view.errorEnterkey();
            return;
        }
        // crypt
        String input = view.selectText();
        for (int i =0; i < input.length();i++){
            String value = treament.letterToBitString(i,input);
            data = encryption.encrypt(value,keyGeneration.getK1(),keyGeneration.getK2());
            encrypt += treament.bitToString(data);
        }
        view.showResultEncrypt(input, encrypt);
        //decrypt
        for (int i =0; i < input.length();i++){
            String value = treament.letterToBitString(i,encrypt);
            data = encryption.decrypt(value,keyGeneration.getK1(),keyGeneration.getK2());
            decrypt += treament.bitToString(data);
        }
        view.showResultDecrypt(encrypt, decrypt);
    }
}