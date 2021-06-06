package consoleui;

import exceptions.InvalidInputException;
import model.CipherAlgorithm;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CaesarCipherCUI {
    private Scanner scanner;
    private boolean shouldRun = true;
    private int shiftKey;
    private int decodeWithoutKeyShiftKey;

    //NOTE: The following Instants are used simply to test the speed if the
    //      CipherAlgorithmAlg1, and to be used to compare against the CipherAlgorithmAlg2.
    private Instant startEncode;
    private Instant endEncode;
    private Instant startDecodeWithKey;
    private Instant endDecodeWithKey;
    private Instant startDecodeWithoutKeyPart1;
    private Instant endDecodeWithoutKeyPart1;
    private Instant startDecodeWithoutKeyPart2;
    private Instant endDecodeWithoutKeyPart2;

    //NOTE: Saves all of the possible decoded strings for when the runDecoderWithoutShiftKey()
    //      method is run. This saves time, in that we do not need to pass an encoded value
    //      again through the algorithm.
    private final HashMap<Integer, String> allPossibleDecodedOptions;

    //EFFECTS: Constructor and runs the Caesar Cipher
    public CaesarCipherCUI() {
        scanner = new Scanner(System.in);
        allPossibleDecodedOptions = new HashMap<>();
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
        getShiftKey();
        int key = shiftKey;
        startEncode = Instant.now();
        runCaesarCipherEncoderDecoderWithShiftKey("encode", input, key);
    }

    //MODIFIES: this
    //EFFECTS: Decodes a given text using a given shift key and the CaesarCipher algorithm
    private void runDecoderWithShiftKey() {
        String input = getInput();
        getShiftKey();
        int key = shiftKey;
        startDecodeWithKey = Instant.now();
        runCaesarCipherEncoderDecoderWithShiftKey("decode", input, key);
    }

    //MODIFIES: this
    //EFFECTS: Encodes or Decodes a given text with a given shift key using the CaesarCipher algorithm
    private void runCaesarCipherEncoderDecoderWithShiftKey(String choice, String input, int shiftKey) {
        boolean displayResult;
        long encodeTimeElapsed = 0;
        long decodeTimeElapsed = 0;
        CipherAlgorithm cipherInput = new CipherAlgorithm(input);
        if (choice.equalsIgnoreCase("encode")) {
            displayResult = displayCipherResult(shiftKey, cipherInput, "encode");
            endEncode = Instant.now();
            encodeTimeElapsed = Duration.between(startEncode, endEncode).toMillis();
        } else {
            displayResult = displayCipherResult(shiftKey, cipherInput, "decode");
            endDecodeWithKey = Instant.now();
            decodeTimeElapsed = Duration.between(startDecodeWithKey, endDecodeWithKey).toMillis();
        }
        String output = cipherInput.returnResult();
        if (displayResult && choice.equalsIgnoreCase("encode")) {
            display(input, output, encodeTimeElapsed);
        } else if (displayResult && choice.equalsIgnoreCase("decode")) {
            display(input, output, decodeTimeElapsed);
        }
    }

    //MODIFIES: this
    //EFFECTS: Runs the CipherAlgorithm on a given input and shift key, and returns true.
    //         If the InvalidInputException is caught, print the shift key out of bounds
    //         message and return false.
    private boolean displayCipherResult(int shiftKey, CipherAlgorithm cipherInput, String input) {
        boolean displayResult = false;
        try {
            cipherInput.runCipher(input, shiftKey);
            displayResult = true;
        } catch (InvalidInputException e) {
            printOutOfBoundsShiftKeyMessage();
        }
        return displayResult;
    }

    //MODIFIES: this
    //EFFECTS: Decodes a given text without a given shift key, using the CaesarCipher algorithm to decode the text
    //           using every single possible shift key value, output all the possible results, and allow the user
    //           to pick the solution they wish.
    private void runDecoderWithoutShiftKey() {
        String input = getInput();
        System.out.println();
        startDecodeWithoutKeyPart1 = Instant.now();
        CipherAlgorithm decodeWithoutKey = new CipherAlgorithm(input);
        displayAllPossibleOptions(decodeWithoutKey);
        String output = allPossibleDecodedOptions.get(decodeWithoutKeyShiftKey);
        endDecodeWithoutKeyPart2 = Instant.now();
        long decodeWithoutKeyTimeElapsed =
                Duration.between(startDecodeWithoutKeyPart1, endDecodeWithoutKeyPart1).toMillis()
                + Duration.between(startDecodeWithoutKeyPart2, endDecodeWithoutKeyPart2).toMillis();
        display(input, output, decodeWithoutKeyTimeElapsed);
    }

    //MODIFIES this
    //EFFECTS: Displays all the possible outputs for decryption based on all possible values of the shift key
    //           (which in the case of the CaesarCipher algorithm are values that lie in [0, 26])
    private void displayAllPossibleOptions(CipherAlgorithm decodeWithoutKey) {
        for (int i = 0; i <= 26; i++) {
            try {
                decodeWithoutKey.runCipher("decode", i);
                String num = Integer.toString(i);
                String output = decodeWithoutKey.returnResult();
                System.out.println("Shift Key (" + num + ") : " + output);
                allPossibleDecodedOptions.put(i, output);
            } catch (InvalidInputException e) {
                printOutOfBoundsShiftKeyMessage();
            }
        }
        endDecodeWithoutKeyPart1 = Instant.now();
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
                    startDecodeWithoutKeyPart2 = Instant.now();
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
    private void getShiftKey() {
        System.out.println("\n Please input a shift key:");
        scanner = new Scanner(System.in);
        String shift = scanner.nextLine();
        try {
            shiftKey = parseInt(shift);
        } catch (NumberFormatException e) {
            System.out.println("\n Invalid shift key!");
        }
    }

    //MODIFIES: this
    //EFFECTS: Displays the input and output text of the CaesarCipher algorithm
    private void display(String input, String output, long duration) {
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        System.out.println("Duration: " + duration + " milliseconds \n");
        System.out.println();
    }

    //EFFECTS: Prints a console message indicating that a given shift key value is
    //         out of bounds.
    private void printOutOfBoundsShiftKeyMessage() {
        System.out.println("\n A given shift key must be in between 0 and 26 inclusive! \n");
    }

}
