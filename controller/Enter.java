package controller;

import java.text.ParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Enter {
    public int enterInt(){
        boolean verification=false;
        int X=0;
        do{
            Scanner s = new Scanner(System.in);
            try{
                X = s.nextInt();
                verification = true;
            }catch (InputMismatchException e){
                verification = false;
            }
        }while(verification!=true);
        return X;
    }

    public double enterDouble(){
        boolean verification=false;
        double X=0;
        do{
            Scanner s = new Scanner(System.in);
            try{
                X = s.nextDouble();
                verification = true;
            }catch (InputMismatchException e){
                verification = false;
            }
        }while(verification!=true);
        return X;
    }

    public String enterString(){
        boolean verification=false;
        String X="";
        do{
            Scanner s = new Scanner(System.in);
            try{
                X = s.nextLine();
                verification = true;
            }catch (InputMismatchException e){
                verification = false;
            }
        }while(verification!=true);
        return X;
    }

    public Date enterDate(){
        DateFormat format =new SimpleDateFormat("dd/mm/yyyy");
        Scanner scan=new Scanner(System.in);
        Date date=null;
        while (date==null){
            System.out.print("date (dd/mm/yyyy): ");
            String entree=scan.next();
            try {
                date=format.parse(entree);
            }catch (ParseException e){

            }
        }
        return date;
    }


}