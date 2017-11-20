package view;

import controller.Enter;

public class View {
    private Enter myEnter;

    public View(){
        myEnter = new Enter();
    }

    public void intro(){
        System.out.println("Welcome to encrypt decrypt SDES");
    }

    public String enterKey(){
        System.out.println("Enter your key (following of 1 or 0)");
        return this.myEnter.enterString();
    }

    public String selectText(){
        System.out.println("Select your data to encrypt");
        return this.myEnter.enterString();
    }

    public void errorEnterkey(){
        System.out.println("Invalid Key");
    }

    public void errorText(){
        System.out.println("Invalid text");
    }

    public void showResultEncrypt(String input, String encrypt){
        System.out.println("Encrypting ...............");
        System.out.println("Data no encrypt = "+ input+"\nData encrypt = "+encrypt);
    }

    public void showResultDecrypt(String encrypt, String decrypt){
        System.out.println("Decrypting ...............");
        System.out.println("Data encrypt = "+ encrypt+"\nData encrypt = "+decrypt);
    }
}
