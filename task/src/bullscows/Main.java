package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gamestart = true;
        int count = 1;

        System.out.println("Input the length of the secret code:");
        String ex = scanner.nextLine();

        try {

            int extrac = Integer.parseInt(ex);
            System.out.println("Input the number of possible symbols in the code:");
            int parse = scanner.nextInt();

            //---------------------------------------------------------------------------------------------------------\\
            //---------------------------------------------------------------------------------------------------------\\
            String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
            StringBuilder s = new StringBuilder();
            s.append("*".repeat(Math.max(0, extrac)));
            try {
                alphabet = alphabet.substring(0, parse);

                if (extrac <= parse) {
                    if (parse > 10) {
                        System.out.printf("The secret is prepared: %s (0-%s, %s-%s).\n", s, alphabet.charAt(9), alphabet.charAt(10)
                                , alphabet.charAt(alphabet.length() - 1));
                    } else {

                        System.out.printf("The secret is prepared:  %s (0-%s).\n", s, alphabet.charAt(alphabet.length() - 1));
                    }


                    //---------------------------------------------------------------------------------------------------------\\
                    //---------------------------------------------------------------------------------------------------------\\
                    if (extrac > 0 && extrac <= 9) {
                        String[] code = extrac(extrac, parse).split("");
                        System.out.println("Okay, let's start a game!");
                        while (gamestart) {
                            System.out.printf("Turn %d:\n", count++);

                            String[] arr = scanner.next().split("");
                            if (Arrays.equals(arr, code)) {
                                bullCowGame(arr, code);
                                gamestart = false;

                            } else {
                                bullCowGame(arr, code);
                                System.out.println();
                            }


                        }
                    } else {
                        System.out.println("Error: can't generate a secret number with " +
                                "a length of 11 because there aren't enough unique digits.");

                    }

                } else {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.", extrac, parse);
                }

            } catch (Exception e) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            }

        } catch (Exception e) {
            System.out.printf("Error: \" %s \" isn't a valid number.",ex);
        }
    }
    //---------------------------------------------------------------------------------------------------------\\


    public static void bullCowGame(String[] arr, String[] code) {
        int countbull = 0;
        int countcows = 0;

        //countbull
        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equals(code[i])) {
                countbull++;
            } else if (Arrays.asList(code).contains(String.valueOf(arr[i]))) {
                countcows++;

            }


        }


        if (countbull == 0 && countcows > 0) {
            System.out.printf("Grade: %d cow\n", countcows);
        } else if (countcows == 0 && countbull > 0) {
            System.out.printf("Grade: %d bull\n", countbull);
        } else if (countbull > 0 && countcows > 0) {
            System.out.printf("Grade: %d bull and %d cow\n", countbull, countcows);
        } else {
            System.out.print("Grade: None\n");
        }

        if (countbull == code.length) {
            System.out.println("Congratulations! You guessed the secret code.");

        }


    }

    //---------------------------------------------------------------------------------------------------------\\


    public static String extrac(int extrac, int posiblie) {

        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random(alphabet.length());
        StringBuilder add = new StringBuilder();
        StringBuilder back = new StringBuilder();
        alphabet = alphabet.substring(0, posiblie);

        final int N = alphabet.length();
        for (int i = 0; i < extrac; i++) {

            add.append(alphabet.charAt(random.nextInt(N - 1) + 1));
        }

        System.out.println(String.valueOf(add));
        return String.valueOf(add);
    }
}