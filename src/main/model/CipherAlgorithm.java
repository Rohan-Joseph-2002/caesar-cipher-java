package model;

import exceptions.InvalidShiftKeyException;

public class CipherAlgorithm {
    private String input;
    private String result;
    private static final int LOWER_BOUNDARY = 0;
    private static final int UPPER_BOUNDARY = 0;
    public CipherAlgorithm(String userInput) {
        this.input = userInput;
        this.result = "";
    }

    public boolean algorithm(String choice, int shiftKey) throws InvalidShiftKeyException {
        if (shiftKey < LOWER_BOUNDARY || shiftKey > UPPER_BOUNDARY) {
            throw new InvalidShiftKeyException();
        } else {
            return false;
        }
    }

    public String returnResult() {
        return this.result;
    }


}
