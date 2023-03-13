package com.pack;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) throws CustException {
        System.out.println("WELCOME TO MOVEINSYNC BANK.");

        Scanner sc = new Scanner(System.in);

        Functionalities f = new Functionalities();

        while(true){
            System.out.println("Want to proceed ? (yes/no) ");
            String str = sc.next();

            if(str.equals("yes")){
                f.verify();
            }else if(str.equals("no")){
                break;
            }else {
                throw new CustException("Please type the preferance wisely.....");
            }


        }


    }
}
