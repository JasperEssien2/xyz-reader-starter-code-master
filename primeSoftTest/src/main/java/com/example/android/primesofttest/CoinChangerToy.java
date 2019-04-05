package com.example.android.primesofttest;

import java.util.Scanner;

public class CoinChangerToy {

    void runCoinChangerToy() {
        /** quarter = 25cents
         dime = 10cents
         nickel = 5cents
         penny = 1cent
         */

        int cent;
        int quarter = 25, dime = 10, nickel = 5, penny = 1;
        int division = 0, division1 = 0, division2 = 0, division3 = 0;
        int change = 0;
        int coins_needed = 0;
        float rounded_floats;
        float dollars;


        ///get amount in dollars
        Scanner scanner = new Scanner(System.in);
        System.out.println("how much change in dollar required?");
        dollars = scanner.nextFloat();

        ///convert dollars to cent
        rounded_floats = Math.round(dollars * 100);  //round floats
//        System.out.println("Rounded floats -- " + rounded_floats);
        cent = (int) rounded_floats;   //cast to integer
//        System.out.println("Cents -- " + cent);

   /*checks how much quarter coins can be given if
      change greater than quarter*/
        if (cent >= quarter) {
            division = cent / quarter;
            coins_needed = division;
            change = cent % quarter;

            if (change < quarter && change >= dime) {
                division1 = change / dime;
                coins_needed = division + division1;
                change = change % dime;

                if (change < dime && change >= nickel) {
                    division2 = change / nickel;
                    coins_needed = division + division1 + division2;
                    change = change % nickel;
                }
                if (change < nickel && change >= penny) {
                    division3 = change / penny;
                    coins_needed = division + division1 + division2 + division3;
                    change = change % penny;
                }

            }  /*checks how much dime coins can be given if change owed
             is lesser than quarter coins*/
        } else if (cent < quarter && cent >= dime) {
            division = cent / dime;
            coins_needed = division;
            change = cent % dime;

            if (change < dime && change >= nickel) {
                division1 = change / nickel;
                coins_needed = division + division1;
                change = change % nickel;
            }
            if (change < nickel && change >= penny) {
                division2 = change / penny;
                coins_needed = division + division1 + division2;
                change = change % penny;
            } /* checks how much nickel coins can be given if
               change is lesser than dime coins*/
        } else if (cent < dime && cent >= nickel) {
            division = cent / nickel;
            coins_needed = division;
            change = change % nickel;

            if (change < nickel && change >= penny) {
                division1 = change / penny;
                coins_needed = division + division1;
                change = change % penny;
            }
            /* checks how much penny coins can be given if
              change lesser than nickel*/
        } else if (cent < nickel && cent >= penny) {
            division = cent / penny;
            coins_needed = division;
            change = cent % penny;
        }
        System.out.println(" coins needed is: " + coins_needed + " -- Coins"); ///Prints out the amount of maximum coins required for change

    }

}
