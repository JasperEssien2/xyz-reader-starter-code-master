package com.example.android.primesofttest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AtmCardValidationCheck {
    private static String cardNumberStr, cardExpiringDate;
    private static List<Integer> listOfCardNumbersSkiped = new ArrayList<>();

    public static void main(String arg[]) {
//        CoinChangerToy coinChangerToy = new CoinChangerToy();
//        coinChangerToy.runCoinChangerToy();
        getValues();

        if (checkIfCardIsValid(convertToArrayList())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");

            Date d = new Date();
            try {
                d = dateFormat.parse(cardExpiringDate);
            } catch (ParseException e) {
                System.out.println("Exception occured while parsing date- hint Invalid date");
                System.out.println("*********************************************");
                e.printStackTrace();
            }
            System.out.println(d.toString());
            if (isExpiringDateOfCardValid(d)) {
                System.out.println("*********************************************");
                System.out.println("Congrats -- Your card is valid -- Card type:  " + getCardTypeByNumberStart(cardNumberStr));
            } else {
                System.out.println("The Card you inputted is an expired card -- ");
                System.out.println("*********************************************");
            }

        } else {
            System.out.println("*********************************************");
            System.out.println("Not Valid ooooooh.. Scammmer");
        }

    }

    private static List<Integer> convertToArrayList() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < cardNumberStr.length(); i++) {
            list.add(Character.getNumericValue(cardNumberStr.charAt(i)));


        }
//        System.out.println("List ooh ---- " + list.toString());
        return list;
    }

    /**
     * This method prompts and store the required values from command line to a variabble
     */
    private static void getValues() {
        Scanner input = new Scanner(System.in);
        System.out.println("************************************************************");
        System.out.println("**** Please input your card details following the format below ****");
        System.out.println("**** CardNumber****");

        cardNumberStr = input.next();
        System.out.println("**** Expiring Date: Format(MM/YY) ****");
        cardExpiringDate = input.next();
        System.out.println("************************************************************");

    }
//    378282246310005

    /**
     * If the an index of the card number is multiplied by 2 and it product length is more than 2
     * it is passed to this method to sum the individual number
     *
     * @param num number required
     * @return int of the sum
     */
    private static int getAdditionOfProduct(int num) {
        String s = String.valueOf(num);
        int sum = 0;
        for (int i = 0; i <= s.length() - 1; i++) {
            sum += Character.getNumericValue(s.charAt(i));
        }
//        System.out.println("Sum ooh --- " + sum);
        return sum;
    }

    /**
     * This method checks if the card is valid or not takin in a list of the crad numbers
     *
     * @param cardNumbers list of card numbers
     * @return true is card is valid else false
     */
    private static boolean checkIfCardIsValid(List<Integer> cardNumbers) {
        int sumOfProduct = 0;
        int cardNumbersSize = cardNumbers.size();
//    System.out.println("CardNUmberSize --- " + cardNumbersSize);
        for (int i = cardNumbersSize; i > 0; ) {
            int previousIndex = i - 1;
            i = i - 2;

            if (i != -1) {
                int multipleBy2 = (cardNumbers.get(i) * 2);
                if (String.valueOf(multipleBy2).length() >= 2) {
                    sumOfProduct += getAdditionOfProduct(multipleBy2);
                } else {
                    sumOfProduct += multipleBy2;
                }
//                System.out.println("CurrentIndex -- " + i + "-- cardNumber: " + cardNumbers.get(i) +
//                        ", -- cardNumber multitply " + (cardNumbers.get(i) * 2) + "Sum OF Product -- " + sumOfProduct);
            }

            listOfCardNumbersSkiped.add(cardNumbers.get(previousIndex));

//            System.out.println("int i --- " + i + "-- Previous Index " + previousIndex);
        }
        int totalSum = sumOfProduct + getSumOfSkippedNumbers();
//        System.out.println("totalSum ---- " + totalSum + "-- sumOfProduct " + sumOfProduct  +
//                "sumOfSkippedNumbers --- " + getSumOfSkippedNumbers());
        String totalSumStr = String.valueOf(totalSum);
        return totalSumStr.endsWith("0");

    }

    /**
     * This method checks if the card provided is expired or not
     *
     * @param cardExpiringDate expiring date of card of type Date
     * @return true if card isn't expired else false if its expired
     */
    private static boolean isExpiringDateOfCardValid(Date cardExpiringDate) {
        Date currentDate = new Date();

        return !cardExpiringDate.before(currentDate);
    }

    /**
     * This returns the sum of the cardNumbers skipped
     *
     * @return sum
     */
    private static int getSumOfSkippedNumbers() {
        int sum = 0;

        for (int i = 0; i <= listOfCardNumbersSkiped.size() - 1; i++) {
            sum += listOfCardNumbersSkiped.get(i);
        }
        return sum;
    }

    /**
     * It returns type of card using the start of the card number
     */
    private static String getCardTypeByNumberStart(String cardNumber) {
        String firstTwoDigit = cardNumber.substring(0, 2);
        System.out.println("First Two Digit are --- " + firstTwoDigit);
        switch (firstTwoDigit) {
            case "34":
            case "37":
                return "American Express";
            case "51":
            case "52":
            case "53":
            case "54":
            case "55":
                return " MasterCard";
            default:
                if (cardNumber.substring(0, 1).equals("4"))
                    return "Visa";
                else return null;

        }
    }

    /**
     * It returns the type of card using the total numbr of digits
     */
    private String getCardTypeByNumberOfDigits(int totalNumber) {
        switch (totalNumber) {
            case 13:
                return "Visa";
            case 15:
                return "American Express";
            case 16:
                if (getCardTypeByNumberStart(cardNumberStr).equals("Visa")) {
                    return "Visa";
                } else
                    return " MasterCard";
            default:
                return null;
        }
    }
}
