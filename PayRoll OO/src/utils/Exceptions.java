package utils;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Exceptions {
    static Scanner input = new Scanner(System.in);

    public static int inputInteger() {
        while (true) {
            try {
                int in = input.nextInt();
                input.nextLine();
                return in;
            } catch (InputMismatchException e) {
                input.nextLine(); // Trash
                System.out.println("WARNING: Input is incompatible");
                System.out.println("       | Please, type an Integer");
            }
        }
    }

    public static double inputDouble() {
        while (true) {
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("##### WARNING: Input is incompatible");
                System.out.println("             | Please, type an Floating Point number");
            }
            input.nextLine(); // Trash
        }
    }

    public static int inputIntegerBounds(int lowerBound, int upperBound) {
        boolean flag = true;
        while (true) {
            try {
                int digit = input.nextInt();
                if (digit >= lowerBound && digit <= upperBound) {
                    input.nextLine();
                    return digit;
                } else {
                    System.out.println("##### CAUTION: Input is incompatible with variable bound");
                    System.out.println("             | Please, type an Integer between " + lowerBound + " and " + upperBound);
                }
            } catch (InputMismatchException e) {
                System.out.println("##### WARNING: Input is incompatible");
                System.out.println("             | Please, type an Integer");
            }
            input.nextLine(); // trash
        }
    }

    public static int inputIntegerBounds(int lowerBound) {
        boolean flag = true;
        while (true) {
            try {
                int digit = input.nextInt();
                if (digit >= lowerBound) {
                    input.nextLine();
                    return digit;
                } else {
                    System.out.println("##### CAUTION: Input is incompatible with variable bound");
                    System.out.println("             | Please, type an Integer equal or greater than " + lowerBound);
                }
            } catch (InputMismatchException e) {
                System.out.println("##### WARNING: Input is incompatible");
                System.out.println("             | Please, type an Integer");
            }
            input.nextLine(); // trash
        }
    }

    public static double inputDoubleBounds(double lowerBound, double upperBound) {
        boolean flag = true;
        while (true) {
            try {
                double digit = input.nextDouble();
                if (digit >= lowerBound && digit <= upperBound) {
                    return digit;
                } else {
                    System.out.println("##### CAUTION: Input is incompatible with variable bound");
                    System.out.println("             | Please, type a Floating Pointer between " + lowerBound + " and " + upperBound);
                }
            } catch (InputMismatchException e) {
                System.out.println("##### WARNING: Input is incompatible");
                System.out.println("             | Please, type a Floating Number");
            }
            input.nextLine(); // trash
        }
    }

    public static int castToInteger(String data) {
        try {
            return Integer.parseInt(data);
        } catch (NumberFormatException e) {
            System.out.println("##### ERROR: The String couldn't be parsed to Int.");
            System.out.println("           | Error Code: 101");
//            System.out.println("Local ERROR: "+ e.getStackTrace()[0].getLineNumber());
            return 0;
        }
    }

    public static String inputString() {
        return input.nextLine();
    }
}

