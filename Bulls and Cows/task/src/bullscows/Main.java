package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int length = 0;
        System.out.println("Please enter length of the secret code:");
        input = scanner.nextLine();
        try {
            length = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", input);
            System.exit(1);
        }
        if (length < 1 || length > 36) {
            System.out.println("Error: The entered number should be from 1 and 36\n");
            System.exit(1);
        }
        int numSymbols = 0;
        System.out.println("Input the number of possible symbols:");
        input = scanner.nextLine();
        try {
            numSymbols = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", input);
            System.exit(1);
        }
        if (numSymbols < length || numSymbols > 36) {
                System.out.printf("Error: The entered number should be from %d and 36\n", length);
                System.exit(1);
        }
        String secret = createRandomNumber(length, numSymbols);
        System.out.println("OK, let's start a game!");
        System.out.println(secret);
        int turn = 0;
        while (true) {
            System.out.printf("Turn %d:\n", ++turn);
            String guess = scanner.nextLine();
            if (gradeGuess(secret, guess)) {
                break;
            }
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static String createRandomNumber(int length, int numSymbols) {
        System.out.print("The secret code is prepared: ");
        Random random = new Random();
        List<Character> symbols = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            char symbol = ' ';
            do {
                int code = random.nextInt(numSymbols - 1);
                symbol = (char) (code < 10 ? code + 48 : code + 87);
            } while (symbols.contains(symbol));
            sb.append(symbol);
            symbols.add(symbol);
            System.out.print("*");
        }
        if (numSymbols == 0) {
            System.out.print(" (0)\n");
        } else if (numSymbols <= 10) {
            System.out.printf(" (0-%d)\n", numSymbols - 1);
        } else if (numSymbols == 11) {
            System.out.print(" (0-9, a)\n");
        } else {
            System.out.printf(" (0-9, a-%s)\n", (char) (numSymbols + 86));
        }
        return sb.toString();
    }

    public static boolean gradeGuess(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); ++i) {
            for (int j = 0; j < guess.length(); ++j) {
                if (guess.charAt(j) == secret.charAt(i)) {
                    if (i == j) {
                        ++bulls;
                    } else {
                        ++cows;
                    }
                }
            }
        }
        printGrade(bulls, cows);
        System.out.println();
        return bulls == String.valueOf(secret).length();
    }

    public static void printGrade(int bulls, int cows) {
        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s). ", bulls, cows);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s). ", bulls);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cow(s). ", cows);
        } else {
            System.out.print("None. ");
        }
    }
}
