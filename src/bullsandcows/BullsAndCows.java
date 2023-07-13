package bullsandcows;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
    private static final Scanner sc = new Scanner(System.in);
    private String secretCode;
    private String guessedNumber;
    private ArrayList<Integer> bullsIndicies, cowsIndicies;

    public BullsAndCows(int lengthOfSecretCode, int numOfPossibleCharacters) {
        secretCode = "";
        generateSecretCode(lengthOfSecretCode, numOfPossibleCharacters);
        bullsIndicies = new ArrayList<>();
        cowsIndicies = new ArrayList<>();
    }

    private void generateSecretCode(int lengthOfSecretCode, int numOfPossibleCharacters) {

        String stringOfAllPossibleCharacters = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder code = new StringBuilder();

        for (int i = 1; i <= lengthOfSecretCode; ) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(numOfPossibleCharacters);//randomIndex will be [0,numOfPossibleCharacters)
            char tempChar = stringOfAllPossibleCharacters.charAt(randomIndex);
            if (!code.toString().contains(String.valueOf(tempChar))) {
                code.append(tempChar);
                i++;
            }
            //System.out.println("Current Secret code : " + secretCode);
        }
        secretCode = code.toString();
        printCodeIsGeneratedLine(lengthOfSecretCode, numOfPossibleCharacters, stringOfAllPossibleCharacters);
    }

    private void printCodeIsGeneratedLine(int lengthOfSecretCode, int numOfPossibleCharacters, String stringOfAllPossibleCharacters) {
        System.out.print("The secret code is prepared: " + "*".repeat(lengthOfSecretCode) + " ");
        //To print something like this  (0-9, a-f).
        if (numOfPossibleCharacters <= 10) {
            System.out.println("(0-9).");
        } else {
            int numOfCharacters = numOfPossibleCharacters - 10;
            System.out.println("(0-9, a-" + stringOfAllPossibleCharacters.charAt(9 + numOfCharacters) + ").");
        }
        System.out.print("Okay, let's start a game!");
    }

    private void takeInput(int lengthOfSecretCode, int turn) {
        guessedNumber = sc.nextLine();
    }

    private int countOfBulls(int lengthOfScretCode) {
        int bullsCount = 0;
        if (lengthOfScretCode == 1) {
            if (secretCode.charAt(0) == guessedNumber.charAt(0)) {
                bullsCount++; // basically return 1
            }
        } else {
            for (int i = 0; i < lengthOfScretCode; i++) {
                if (secretCode.charAt(i) == guessedNumber.charAt(i)) {
                    bullsIndicies.add(i); cowsIndicies.add(i);
                    bullsCount++;
                }
            }
        }
        return bullsCount;
    }

    private int countOfCows(int lengthOfSecretCode) {
        int cowsCount = 0;
        if (lengthOfSecretCode == 1) {
            return 0;
        }
        for (int i = 0; i < lengthOfSecretCode; i++) { // iterating through user input code guess
            if (!bullsIndicies.contains(i)) { // if i is not in bullsIndicies then only check it for cows
                for (int j = 0; j < lengthOfSecretCode; j++) { // for the given i, iterating through secret code
                    if (!cowsIndicies.contains(j)) { // if cowsIndicies contains j, it means that character has already been counted for cows
                        char ch = secretCode.charAt(j);
                        if (guessedNumber.charAt(i) == ch) {
                            cowsCount++;
                            cowsIndicies.add(j);
                        } // end of if
                    } // end of if
                } // end of for
            } // end of if
        } // end of for

        return cowsCount;
    }

    private void printResult(int countVariable, String animal) {
        if (countVariable == 1) {
            System.out.print(animal);
        } else {
            System.out.print(animal + "s");
        }
    }

    private void printGrade(int bullsCount, int cowsCount) {
        System.out.print("Grade: ");
        if (bullsCount > 0) {
            System.out.print(bullsCount + " ");
            printResult(bullsCount, "bull");
            if (cowsCount > 0) {
                System.out.print(" and " + cowsCount + " ");
                printResult(cowsCount, "cow");
            }
        }
        if (bullsCount == 0) {
            if (cowsCount > 0) {
                System.out.print(cowsCount + " ");
                printResult(cowsCount, "cow");
            }
            if (cowsCount == 0) {
                System.out.print("None");
            }
        }
    }

    protected void playGame(int lengthOfSecretCode) {

        int countTurn = 0, bullsCount = 0;
        //System.out.println("The secret code is prepared: " + secretCode);

        while (bullsCount != lengthOfSecretCode) {
            countTurn++;
            System.out.println("\nTurn " + countTurn + ":");

            bullsIndicies.clear();
            cowsIndicies.clear();

            takeInput(lengthOfSecretCode, countTurn);

            bullsCount = countOfBulls(lengthOfSecretCode);
            if (bullsCount == lengthOfSecretCode) {
                System.out.print("Grade: " + bullsCount + " ");
                printResult(bullsCount, "bull");
                System.out.println("\nCongratulations! You guessed the secret code.");
                break;
            }

            int cowsCount = countOfCows(lengthOfSecretCode);
            printGrade(bullsCount, cowsCount);
            //System.out.println(".");
        }
    }


}
