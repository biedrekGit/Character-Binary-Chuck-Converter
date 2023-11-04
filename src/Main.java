import java.util.Scanner;

public class Main {
    public static void theMenu () {
        System.out.println("""
                Please choose one of the options by typing a corresponding number:
                1. Convert a string of characters into binary and chuck code.
                2. Convert a Chuck code into a binary code and string of characters.""");
        int inputMenu = new Scanner(System.in).nextInt();
        switch (inputMenu) {
            case 1 :
                stringToChuck();
            case 2:
                chuckToString();
            default :
                System.out.println("\nWrong number. Please try again.\n");
                theMenu();
        }
    }

    public static void stringToChuck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        int l = input.length();
        System.out.println("\nThe result:");
        String b = "";
        for (int i = 0; i < l; i++) {
            int n = input.charAt(i);
            String c = String.format("%7s", Integer.toBinaryString(n)).replace(' ', '0');
            //System.out.println(input.charAt(i) + " = " + b);
            b = b + c;
        }
        System.out.println("Binary: " + b); //String to binary
        int nTemp = 1;
        System.out.print("Chuck: ");
        for (int j = 0; j < b.length() - 1; j++) {
            char[] temp = new char[nTemp];
            for (int k = 0; k < nTemp; k++) {
                temp[k] = '0';
            }
            String s = String.valueOf(temp);
            if (b.charAt(j) != b.charAt(j + 1) && j != b.length() - 2) {
                if (b.charAt(j) == '1') {
                    System.out.print("0 " + s + " ");
                    nTemp = 1;
                } else {
                    System.out.print("00 " + s + " ");
                    nTemp = 1;
                }
            } else if (b.charAt(j) != b.charAt(j + 1) && j == b.length() - 2) {
                if (b.charAt(j) == '1') {
                    System.out.print("0 " + s + " " + "00 0 ");
                    nTemp = 1;
                } else {
                    System.out.print("00 " + s + " " + "0 0 ");
                    nTemp = 1;
                }
            } else if (b.charAt(j) == b.charAt(j + 1)) {
                nTemp++;
                if (j == b.length() - 2) {
                    if (b.charAt(j) == '1') {
                        System.out.print("0 " + s + "0 ");
                        nTemp = 1;
                    } else {
                        System.out.print("00 " + s + "0 ");
                        nTemp = 1;
                    }
                }
            }
        }
        System.out.println('\n');
        theMenu();
    }

    public static void chuckToString () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Chuck code:");
        String inputCTS = scanner.nextLine();
        String[] array = inputCTS.split(" ");
        int l = array.length;
        String chuckToBinary = "";
        if (array[0].equals("0")) {
            int tempCounter = 0;
            for (int i = 0; i < l; i++) {
                if (i % 2 != 0 && tempCounter == 0) {
                    for (int j = 0; j < array[i].length(); j++) {
                        //System.out.print("1");
                        chuckToBinary += "1";
                    }
                    tempCounter = 1;
                } else if (i % 2 != 0 && tempCounter == 1) {
                    for (int j = 0; j < array[i].length(); j++) {
                        //System.out.print("0");
                        chuckToBinary += "0";
                    }
                    tempCounter = 0;
                }
            }
        } else if (array[0].equals("00")) {
            int tempCounter = 0;
            for (int i = 0; i < l; i++) {
                if (i % 2 != 0 && tempCounter == 0) {
                    for (int j = 0; j < array[i].length(); j++) {
                        //System.out.print("0");
                        chuckToBinary += "0";
                    }
                    tempCounter = 1;
                } else if (i % 2 != 0 && tempCounter == 1) {
                    for (int j = 0; j < array[i].length(); j++) {
                        //System.out.print("1");
                        chuckToBinary += "1";
                    }
                    tempCounter = 0;
                }
            }
        } //converting Chuck to binary

        System.out.println("\nThe result:");
        System.out.println("Binary: " + chuckToBinary);
        String[] biArr = new String[chuckToBinary.length() / 7];
        for (int i = 0; i < chuckToBinary.length() / 7; i++) {
            biArr[i] = chuckToBinary.substring(i * 7, 7 * (i + 1));
        }
        //System.out.println(Arrays.toString(biArr));
        System.out.print("String: ");
        for (int i = 0; i < biArr.length; i++) {
            int temp = Integer.parseInt(biArr[i], 2);
            char tempChar = (char) temp;
            System.out.print(tempChar);
        }
        System.out.println('\n');
        theMenu();



    }

    public static void main(String[] args) {
        System.out.println("""
                HELLO stranger!
                This is a character-binary-chuck converter.
                What would you like to do?
                """);
        do {
            theMenu();
        } while (true);
    }
}