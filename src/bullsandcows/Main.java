package bullsandcows;

import java.util.Scanner;

public class Main {

	private final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		String secretCodeLength, possibleCharactersNumber;
		int lengthOfSecretCode = 0, numOfPossibleCharacters = 0;

		System.out.println("Input the length of the secret code:");
		secretCodeLength = sc.nextLine();

		try {
			lengthOfSecretCode = Integer.parseInt(secretCodeLength);
		} catch (Exception e) {
			System.out.printf("Error: \"%s\" isn't a valid number.\n", secretCodeLength);
			return;
		}

		System.out.println("Input the number of possible symbols in the code:");
		possibleCharactersNumber = sc.nextLine();

		try {
			numOfPossibleCharacters = Integer.parseInt(possibleCharactersNumber);
		} catch (Exception e) {
			System.out.printf("Error: \"%s\" isn't a valid number.\n", numOfPossibleCharacters);
			return;
		}

		if (lengthOfSecretCode == 0 || lengthOfSecretCode > numOfPossibleCharacters) {
			System.out.printf("Error: it's not possible to generate a code with a " +
					"length of %d with %d unique symbols.\n", lengthOfSecretCode, numOfPossibleCharacters);
			return;
		}

		if (lengthOfSecretCode > 36 || numOfPossibleCharacters > 36) {
			System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
			return;
		}

		BullsAndCows game = new BullsAndCows(lengthOfSecretCode, numOfPossibleCharacters);
		game.playGame(lengthOfSecretCode);

	}

}
