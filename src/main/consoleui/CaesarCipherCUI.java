package consoleui;

import exceptions.InvalidInputException;
import model.CipherAlgorithmAlg1;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CaesarCipherCUI {
    private Scanner scanner;
    private boolean shouldRun = true;
    private int decodeWithoutKeyShiftKey;

    //EFFECTS: Constructor and runs the Caesar Cipher
    public CaesarCipherCUI() {
        scanner = new Scanner(System.in);
        runCaesarCipher();
    }

    //MODIFIES: this
    //EFFECTS: Runs the Caesar Cipher
    private void runCaesarCipher() {
        scanner = new Scanner(System.in);

        while(shouldRun) {
            displayMenu();
            String userInput = scanner.next();

            if (userInput.equalsIgnoreCase("q")) {
                shouldRun = false;
            } else {
                processCommand(userInput);
            }
        }
        System.out.println("\n Thank you for using this application! \n");
    }

    //EFFECTS: Displays the application menu
    private void displayMenu() {
        System.out.println("\n Please select from:");
        System.out.println("\t e -> Encode a Text (with a shift key)");
        System.out.println("\t d -> Decode a Text (with a shift key)");
        System.out.println("\t w -> Decode a Text (without a given shift key)");
        System.out.println("\t q -> Quit the application");
    }

    //MODIFIES: this
    //EFFECTS: Processes user commands
    private void processCommand(String userInput) {
        if (userInput.equalsIgnoreCase("e")) {
            runEncoderWithShiftKey();
        } else if (userInput.equalsIgnoreCase("d")) {
            runDecoderWithShiftKey();
        } else if (userInput.equalsIgnoreCase("w")) {
            runDecoderWithoutShiftKey();
        } else {
            System.out.println("\n Invalid selection! \n");
        }
    }

    //MODIFIES: this
    //EFFECTS: Encodes a given text using a given shift key and the CaesarCipher algorithm
    private void runEncoderWithShiftKey() {
        String input = getInput();
        int shiftKey = getShiftKey();
        runCaesarCipherEncoderDecoderWithShiftKey("encode", input, shiftKey);
    }

    //MODIFIES: this
    //EFFECTS: Decodes a given text using a given shift key and the CaesarCipher algorithm
    private void runDecoderWithShiftKey() {
        String input = getInput();
        int shiftKey = getShiftKey();
        runCaesarCipherEncoderDecoderWithShiftKey("decode", input, shiftKey);
    }

    //MODIFIES: this
    //EFFECTS: Encodes or Decodes a given text with a given shift key using the CaesarCipher algorithm
    private void runCaesarCipherEncoderDecoderWithShiftKey(String choice, String input, int shiftKey) {
        boolean displayResult = false;
        CipherAlgorithmAlg1 cipherInput = new CipherAlgorithmAlg1(input);
        if (choice.equalsIgnoreCase("encode")) {
            try {
                cipherInput.runCipher("encode", shiftKey);
                displayResult = true;
            } catch (InvalidInputException e) {
                System.out.println("\n A given shift key must be in between 0 and 26 inclusive! \n");
            }
        } else {
            try {
                cipherInput.runCipher("decode", shiftKey);
                displayResult = true;
            } catch (InvalidInputException e) {
                System.out.println("\n A given shift key must be in between 0 and 26 inclusive! \n");
            }
        }

        if (displayResult) {
            String output = cipherInput.returnResult();
            display(input, output);
        }
    }

    //MODIFIES: this
    //EFFECTS: Decodes a given text without a given shift key, using the CaesarCipher algorithm to decode the text
    //           using every single possible shift key value, output all the possible results, and allow the user
    //           to pick the solution they wish.
    private void runDecoderWithoutShiftKey() {
        String input = getInput();
        System.out.println();
        CipherAlgorithmAlg1 decodeWithoutKey = new CipherAlgorithmAlg1(input);
        displayAllPossibleOptions(decodeWithoutKey);
        runCaesarCipherEncoderDecoderWithShiftKey("decode", input, decodeWithoutKeyShiftKey);
    }

    //MODIFIES this
    //EFFECTS: Displays all the possible outputs for decryption based on all possible values of the shift key
    //           (which in the case of the CaesarCipher algorithm are values that lie in [0, 26])
    private void displayAllPossibleOptions(CipherAlgorithmAlg1 decodeWithoutKey) {
        for (int i = 0; i <= 26; i++) {
            try {
                decodeWithoutKey.runCipher("decode", i);
                String num = Integer.toString(i);
                String output = decodeWithoutKey.returnResult();
                System.out.println("Shift Key (" + num + ") : " + output);
            } catch (InvalidInputException e) {
                System.out.println("\n A given shift key must be in between 0 and 26 inclusive! \n");
            }
        }

        boolean incorrectVal = true;
        while (incorrectVal) {
            System.out.println("\n For which value of the shift key would you like the input text to be decoded to?");
            scanner = new Scanner(System.in);
            String shift = scanner.nextLine();
            try {
                int shiftValue = parseInt(shift);
                if (shiftValue < 0 || shiftValue > 26) {
                    System.out.println("\n Invalid selection!");
                } else {
                    decodeWithoutKeyShiftKey = shiftValue;
                    System.out.println();
                    incorrectVal = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("\n Invalid selection!");
            }
        }
    }

    //EFFECTS: Gets user input string
    private String getInput() {
        System.out.println("\n Please input the text:");
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    //EFFECTS: Get user input shift key
    private int getShiftKey() {
        System.out.println("\n Please input a shift key:");
        scanner = new Scanner(System.in);
        String shift = scanner.nextLine();
        return parseInt(shift);
    }

    //MODIFIES: this
    //EFFECTS: Displays the input and output text of the CaesarCipher algorithm
    private void display(String input, String output) {
        System.out.println("Input: " + input);
        System.out.println("Output: " + output + " \n");
        System.out.println();
    }

}
