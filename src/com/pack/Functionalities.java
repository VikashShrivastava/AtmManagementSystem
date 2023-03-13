package com.pack;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Functionalities {

    public void verify(){
        Connection c = DBConnection.getConnection();

        System.out.println("TO CONTINUE PLEASE ENTER YOUR CUSTID.");
        Scanner sc = new Scanner(System.in);
        int custId = sc.nextInt();
        try {
            //System.out.println("=========");
            PreparedStatement st = c.prepareStatement("select * from cust where id = ?");
            st.setInt(1,custId);
            ResultSet rs = st.executeQuery();


            int pin = 0;
            boolean custExits = false;
            while (rs.next()){
                custExits = true;
                System.out.println(rs.getInt(4));
                pin = rs.getInt(4);
            }

            if(custExits == true){
                System.out.println("User Found.");
            }else {
                throw new CustException("Error 405......User not found.");
            }

            System.out.println("pin is :" + pin);

            System.out.println("Please Enter your 4 digit pin to continue services : ");
            int custPin = sc.nextInt();

            if(pin == custPin){
                this.intro(custId);
            }else{
                throw new CustException("Wrong pin....... Restart the process.");
            }


            System.out.println("Please select the operation.");
            System.out.println("Enter '1' for Transaction History");
            System.out.println("Enter '2' for Withdrawing money.");

            int operation = sc.nextInt();

            if(operation != 1 && operation != 2){
                throw new CustException("Please select the valid operation.....Restart the process.");
            }


            if(operation == 1){
                this.showTransasctionHistory(custId);
            }else if(operation == 2){
                this.canWithdraw(custId);
            }else {
                return;
            }


        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void intro(int custId){
        Connection c = DBConnection.getConnection();
        try {
            PreparedStatement st = c.prepareStatement("select * from cust where id = ?");
            st.setInt(1,custId);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                String name = rs.getString(2);
                System.out.println("Welcome back " + name + " to MovieInSync Bank.");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void showTransasctionHistory(int custId){
        Connection c = DBConnection.getConnection();
        String str = "u" + custId;

        try {
            String query = "select * from $tablename";
            String tName = "u" + custId;
            String finalQuery = query.replace("$tablename",tName);
            PreparedStatement st = null;
            st = c.prepareStatement(finalQuery);
            ResultSet rs = st.executeQuery();
            System.out.println("TRANSACTION HISTORY : ");
            while (rs.next()){
                System.out.print(rs.getString(1) + "----");
                System.out.print(rs.getInt(2) + "----");
                System.out.print(rs.getDate(3) + " ");
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void canWithdraw(int custId){

        System.out.println("Enter the amount to withdraw :");
        Scanner sc = new Scanner(System.in);
        int amount= sc.nextInt();

        Connection c = DBConnection.getConnection();

        try {
            PreparedStatement st = c.prepareStatement("select * from cust where id = ?");
            st.setInt(1,custId);
            ResultSet rs = st.executeQuery();

            int availableBalance = 0;
            while (rs.next()){
                availableBalance = rs.getInt(3);
            }
            System.out.println("Current balance :"+availableBalance);

            if(amount <= availableBalance){
                String query = "insert into $tablename values(?,?,?)";
                String tName = "u" + custId;
                String finalQuery = query.replace("$tablename", tName);
                PreparedStatement st1 = c.prepareStatement(finalQuery);
                st1.setString(1,"Withdrawn");
                st1.setInt(2, amount);

                //System.out.println("11111========================");


                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String d = dateFormat.format(date);

                System.out.println(java.sql.Date.valueOf(d));

                st1.setDate(3, java.sql.Date.valueOf(d));

               // System.out.println("22222========================");
                int b = st1.executeUpdate();
                //System.out.println("3333========================");


                int newBalnace = availableBalance - amount;
                PreparedStatement st2 = c.prepareStatement("UPDATE cust set balance = ? WHERE id = ?");
                st2.setInt(1,newBalnace);
                st2.setInt(2,custId);

                int x = st2.executeUpdate();


                if(b == 1 && x == 1){
                    System.out.println("Amount Withdrawn.......And updated in transaction history.");
                }else{
                    System.out.println("tuple not inserted");
                }

            }else{
                throw new CustException("Insufficient Amount");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
