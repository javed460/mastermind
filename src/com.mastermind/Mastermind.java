package com.mastermind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.mastermind.RandomGeneratorAPI.getRandomNumberCombination;

public class Mastermind {

    public static void main(String[] args) {
        String[] randomNumberCombination = getRandomNumberCombination();
        //String[] randomNumberCombination = new String[]{"1", "1", "1", "2"};

        int[] computerSelectedNums = new int[randomNumberCombination.length];
        for (int i = 0; i < randomNumberCombination.length; i++) {
            computerSelectedNums[i] = Integer.parseInt(randomNumberCombination[i]);
        }

        System.out.println("You have 10 attempts to guess the number");

        for (int attemptCounter = 1; attemptCounter <= 10; attemptCounter++) {
            System.out.println();
            System.out.println("Guess the number - Attempt " + attemptCounter);
            Scanner userNumberScan = new Scanner(System.in);
            String userNumber = userNumberScan.nextLine();

            String[] userInputStr = userNumber.split(" ");

            while (userInputStr.length < 4) {
                System.out.println("You did not enter 4 digit number correctly, please enter again");
                userNumber = userNumberScan.nextLine();
                userInputStr = userNumber.split(" ");
            }

            int[] userEnteredNum = new int[userInputStr.length];
            for (int j = 0; j < userInputStr.length; j++) {
                userEnteredNum[j] = Integer.parseInt(userInputStr[j]);
            }

            if (allDigitsMatch(userEnteredNum, computerSelectedNums)) {
                System.out.println("Superb!. All numbers are correct.");
                System.out.println("Computer Number generated was : " + Arrays.stream(randomNumberCombination).toList());
                System.exit(1);
            }

            int correctDigits = 0;
            int correctLocationCounter = countCorrectLocations(computerSelectedNums, userEnteredNum);

            Map<Integer, String> userDigitDetectedMap = initializeToN(userEnteredNum);

            for (int computerIdx = 0; computerIdx < computerSelectedNums.length; computerIdx++) {
                for (int userIdx = 0; userIdx < userEnteredNum.length; userIdx++) {
                    if (computerSelectedNums[computerIdx] == userEnteredNum[userIdx]) {
                        if (userDigitAlreadyDetected(userIdx, userDigitDetectedMap)) {
                            continue;
                        } else {
                            correctDigits++;
                            updatedUserDigitMap(userIdx, userDigitDetectedMap);
                            break;
                        }
                    }
                }
            }

            if (correctDigits == 0 && correctLocationCounter == 0) {
                System.out.println("All incorrect");
            } else {
                System.out.println(correctDigits + " correct number(s) and " + correctLocationCounter + " correct location(s)");
            }

        }

        System.out.println("Computer Number generated was : " + Arrays.stream(randomNumberCombination).toList());
    }

    private static Map<Integer, String> initializeToN(int[] userEnteredNum) {
        Map<Integer, String> map = new HashMap<>();
        for (int index = 0; index < userEnteredNum.length; index++) {
            map.put(index, "N");
        }
        return map;
    }

    private static void updatedUserDigitMap(int userIdx, Map<Integer, String> userDigitDetectedMap) {
        userDigitDetectedMap.put(userIdx, "Y");
    }

    private static boolean userDigitAlreadyDetected(int userIdx, Map<Integer, String> userDigitDetectedMap) {
        if (userDigitDetectedMap.isEmpty())
            return false;

        String detected = userDigitDetectedMap.get(userIdx);
        return detected.equals("Y");
    }


    private static int countCorrectLocations(int[] computerSelectedNums, int[] userEnteredNum) {
        int correctLocations = 0;
        for (int i = 0; i < computerSelectedNums.length; i++) {
            if (computerSelectedNums[i] == userEnteredNum[i]) {
                correctLocations++;
            }
        }
        return correctLocations;
    }

    private static boolean allDigitsMatch(int[] userEnteredNum, int[] computerSelectedNums) {
        for (int i = 0; i < userEnteredNum.length; i++) {
            if (userEnteredNum[i] != computerSelectedNums[i]) {
                return false;
            }
        }
        return true;
    }

}
