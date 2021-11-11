package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        VareData vare = new VareData();

        VareData[] array = new VareData[20];

        for (int i = 0; i < 20; i++) {
            array[i] = new VareData();
        }

        //int amountVare;
        int amountVare = readFromFile(array);

        //amountVare = readFromFile(array);
        // Oversdtående er skrevet ind i int
        print(array, amountVare);

        writeToDataFile(array, amountVare);
        //print(array, amountVare); // test

        amountVare = readDataFile(array);
        print(array, amountVare); // test

        totalPrice(array, amountVare);
        //print(array, amountVare);
    }

    public static int readFromFile(VareData[] array) throws FileNotFoundException{
        File bestilling = new File("Bestilling.txt");

        Scanner input = new Scanner(bestilling);

        int i = 0;

        while (input.hasNext()){
            array[i].setAmount(input.nextInt());
            array[i].setVarer(input.next());
            array[i].setPrice(input.nextDouble());
            i++;

        }
        return i;
    }

    //Print funktion
    //%d = int
    //%s = String
    //%.02f = 2 decimaler efter comma i double
    public static void print(VareData[] a, int amount){
        for (int i = 0; i < amount; i++) {
            System.out.format("\n %d %s %.2f \n", a[i].getAmount(), a[i].getVarer(),a[i].getPrice());
            //Tager fat ved hjælp af get, i de forskellige elementer.
        }
    }

    //Skriv til .ser fil
    public static void writeToDataFile(VareData [] array, int amount) throws FileNotFoundException{
        try {
            //opretter også .ser fil hvis dewn ikke findes
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Varer.ser"));

            for (int i = 0; i < amount; i++) {
                out.writeObject(array[i]);
            }
            out.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readDataFile(VareData[] array) throws FileNotFoundException{
        int i = 0;

        try {
            FileInputStream f = new FileInputStream("Varer.ser");

            ObjectInputStream input = new ObjectInputStream(f);

            while (f.available() > 0){
                array[i] = (VareData) input.readObject();
                i++;
            }
            input.close();
        }

        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void totalPrice(VareData [] a, int amount){
        /*System.out.format("\n %-20s %20s %20s %20s \n", "Varenavn", "Antal", "Samlet pris uden rabat", "Samlet pris");

        double sumWhitDiscount = 0.0;
        double sumWhitout = 0.0;

        for (int i = 0; i < amount; i++) {
            // %-20s %20s %20s skubber teksten

            //Her udregner vi den totale pris af alle varerne
            sumWhitout = sumWhitout + a[i].getAmount() * a[i].getPrice();

            if (a[i].getAmount() > 10){
                System.out.format("\n %-20s %20d %20.2f %20.2f \n", a[i].getVarer(), a[i].getAmount(), a[i].getAmount() * a[i].getPrice(), (a[i].getAmount() * a[i].getPrice() * 0.85));
                //Nu beregnes den totale sum af produkter med rabat
                sumWhitDiscount = sumWhitDiscount + (a[i].getAmount() * a[i].getPrice() * 0.85);
           }

           else {
                System.out.format("\n %-20s %20d %20.2f %20.2f\n", a[i].getVarer(), a[i].getAmount(), a[i].getAmount() * a[i].getPrice(), a[i].getAmount() * a[i].getPrice());
                //Nu beregnes den totale sum af produkter uden rabat
                sumWhitDiscount = sumWhitDiscount + a[i].getAmount() * a[i].getPrice();
           }
        }

        System.out.format("\n %-20s %20s %20.2f %20.2f\n", "Total","", sumWhitout, sumWhitDiscount);
        System.out.format("\n %-20s %20s %20.2s %20.2f\n", "Total rabat","", "", sumWhitout - sumWhitDiscount);*/

        try {
            PrintWriter out = new PrintWriter("Faktura.txt");
            System.out.format("\n %-20s %20s %20s %20s \n", "Varenavn", "Antal", "Samlet pris uden rabat", "Samlet pris");

            double sumWhitDiscount = 0.0;
            double sumWhitout = 0.0;

            for (int i = 0; i < amount; i++) {
                // %-20s %20s %20s skubber teksten

                //Her udregner vi den totale pris af alle varerne
                sumWhitout = sumWhitout + a[i].getAmount() * a[i].getPrice();

                if (a[i].getAmount() > 10){
                    System.out.format("\n %-20s %20d %20.2f %20.2f \n", a[i].getVarer(), a[i].getAmount(), a[i].getAmount() * a[i].getPrice(), (a[i].getAmount() * a[i].getPrice() * 0.85));
                    //Nu beregnes den totale sum af produkter med rabat
                    sumWhitDiscount = sumWhitDiscount + (a[i].getAmount() * a[i].getPrice() * 0.85);

                    //Printer ind i textfilen
                    out.print(a[i].getVarer() + "\t \t \t");
                    out.print(a[i].getAmount() + " ");
                    out.print(a[i].getAmount() * a[i].getPrice() + " ");
                    out.print(a[i].getAmount() * a[i].getPrice() * 0.85);
                    out.println();
                }

                else {
                    System.out.format("\n %-20s %20d %20.2f %20.2f\n", a[i].getVarer(), a[i].getAmount(), a[i].getAmount() * a[i].getPrice(), a[i].getAmount() * a[i].getPrice());
                    //Nu beregnes den totale sum af produkter uden rabat
                    sumWhitDiscount = sumWhitDiscount + a[i].getAmount() * a[i].getPrice();
                    //Printer ind i textfilen
                    out.print(a[i].getVarer() + " ");
                    out.print(a[i].getAmount() + " ");
                    out.print(a[i].getAmount() * a[i].getPrice() + " ");
                    out.print(a[i].getAmount() * a[i].getPrice());
                    out.println();
                }
            }
            out.print("Total uden rabat: " + sumWhitout + "\n");
            out.print("Total med rabat: " + sumWhitDiscount + "\n");
            out.print("Total rabat: " + (sumWhitout - sumWhitDiscount));
            out.close();

            System.out.format("\n %-20s %20s %20.2f %20.2f\n", "Total","", sumWhitout, sumWhitDiscount);
            System.out.format("\n %-20s %20s %20.2s %20.2f\n", "Total rabat","", "", sumWhitout - sumWhitDiscount);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
