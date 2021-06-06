package model;

import exceptions.InvalidChoiceException;
import exceptions.InvalidShiftKeyException;

import java.util.LinkedList;

import static java.lang.Character.*;

public class CipherAlgorithmAlg1 {
    private final String input;
    private String result;
    private static final Character EMPTY = ' ';
    private static final int LOWER_BOUNDARY = 0;
    private static final int UPPER_BOUNDARY = 26;
    private final LinkedList<Character> alphabetList;

    //MODIFIES: this
    //EFFECTS: CipherAlgorithm constructor
    public CipherAlgorithmAlg1(String userInput) {
        this.input = userInput;
        this.result = "";
        alphabetList = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: If a given shiftKey is < 0 or > 26, throw an InvalidShiftKeyException,
    //            else if the choice is either "encode" or "decode", run the cipher algorithm,
    //            else, throw an InvalidChoiceException.
    public boolean runCipher(String choice, int shiftKey) throws InvalidShiftKeyException, InvalidChoiceException {
        generateAlphabetList();
        if (shiftKey < LOWER_BOUNDARY || shiftKey > UPPER_BOUNDARY) {
            throw new InvalidShiftKeyException();
        } else if (choice.equalsIgnoreCase("encode") || choice.equalsIgnoreCase("decode")) {
            runAlgorithm(choice, shiftKey);
            return true;
        } else {
            throw new InvalidChoiceException();
        }
    }

    //MODIFIES: this
    //EFFECTS: If the choice is "encode", then the string is encoded according to the Cipher Algorithm
    //            in that every single character in the string is moved to right by a given shiftKey value.
    //         If the choice is "decode", then the string is decoded according to the Cipher Algorithm
    //            in that every single character in the string is moved to the left by a given shiftKey value.
    private void runAlgorithm(String choice, int shiftKey) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (c == EMPTY) {
                resultString.append(c);
            } else if (!isLetter(c)) {
                resultString.append(c);
            } else {
                int index = alphabetList.indexOf(toLowerCase(c));
                int shift = getShiftNum(choice, index, shiftKey);
                Character newC = alphabetList.get(shift);
                if (isUpperCase(c)) {
                    resultString.append(toUpperCase(newC));
                } else {
                    resultString.append(newC);
                }
            }
        }
        this.result = resultString.toString();
    }

    //EFFECTS: Returns the new shift value depending on whether a string is being encoded or decoded
    private int getShiftNum(String choice, int index, int shiftKey) {
        int shiftNum = 0;
        if (choice.equalsIgnoreCase("encode")) {
            shiftNum = (index + shiftKey) % 26;
        } else {
            int decodeShift = (index - shiftKey) % 26;
            if (decodeShift < 0) {
                decodeShift = decodeShift + alphabetList.size();
            }
            shiftNum = decodeShift;
        }
        return shiftNum;
    }

    //EFFECTS: Returns the result of encoding or decoding
    public String returnResult() {
        return this.result;
    }

    //MODIFIES: alphabetList
    //EFFECTS: Adds every single alphabet to the linked list as singular characters
    private void generateAlphabetList() {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < 26; i++) {
            alphabetList.add(alphabets.charAt(i));
        }
    }

}
