package model;

import exceptions.InvalidChoiceException;
import exceptions.InvalidInputException;
import exceptions.InvalidShiftKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CipherAlgorithmAlg1Tests {
    private final String testInput = "My name is Rohan Joseph! - 2002 ;)";
    private final String testOutput = "Vh wjvn rb Axqjw Sxbnyq! - 2002 ;)";
    private final String encode = "encode";
    private final String decode = "decode";
    private final String invalidChoice = "invalidChoice";
    private final int validShiftKey = 9;
    private final int invalidShiftKeyUpperBound = 29;
    private final int invalidShiftKeyLowerBound = -5;
    private CipherAlgorithmAlg1 testEncodeAlgorithm;
    private CipherAlgorithmAlg1 testDecodeAlgorithm;

    @BeforeEach
    public void setup() {
        testEncodeAlgorithm = new CipherAlgorithmAlg1(testInput);
        testDecodeAlgorithm = new CipherAlgorithmAlg1(testOutput);
    }

    @Test
    public void testEncodeAlgorithm() {
        try {
            assertTrue(testEncodeAlgorithm.runCipher(encode, validShiftKey));
            assertEquals(testEncodeAlgorithm.returnResult(), testOutput);
        } catch (InvalidInputException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testEncodeAlgorithmInvalidShiftKeyExceptionThrown() {
        try {
            assertFalse(testEncodeAlgorithm.runCipher(encode, invalidShiftKeyLowerBound));
            fail("Exception should have been thrown!");
        } catch (InvalidInputException e) {
            //pass
        }
        try {
            assertFalse(testEncodeAlgorithm.runCipher(encode, invalidShiftKeyUpperBound));
            fail("Exception should have been thrown!");
        } catch (InvalidInputException e) {
            //pass
        }
        try {
            assertFalse(testEncodeAlgorithm.runCipher(invalidChoice, validShiftKey));
            fail("Exception should have been thrown!");
        } catch (InvalidInputException e) {
            //pass
        }
    }

    @Test
    public void testDecodeAlgorithm() {
        try {
            assertTrue(testDecodeAlgorithm.runCipher(decode, validShiftKey));
            assertEquals(testDecodeAlgorithm.returnResult(), testInput);
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testDecodeAlgorithmInvalidShiftKeyExceptionThrown() {
        try {
            assertFalse(testDecodeAlgorithm.runCipher(decode, invalidShiftKeyLowerBound));
            fail("Exception should have been thrown!");
        } catch (InvalidInputException e) {
            //pass
        }
        try {
            assertFalse(testDecodeAlgorithm.runCipher(decode, invalidShiftKeyUpperBound));
            fail("Exception should have been thrown!");
        } catch (InvalidInputException e) {
            //pass
        }
        try {
            assertFalse(testDecodeAlgorithm.runCipher(invalidChoice, validShiftKey));
            fail("Exception should have been thrown");
        } catch (InvalidInputException e) {
            //pass
        }
    }

    @Test
    public void testReturnOutputAfterEncode() {
        try {
            testEncodeAlgorithm.runCipher(encode, validShiftKey);
            assertEquals(testEncodeAlgorithm.returnResult(), testOutput);
        } catch (InvalidInputException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testReturnInputAfterDecode() {
        try {
            testDecodeAlgorithm.runCipher(decode, validShiftKey);
            assertEquals(testDecodeAlgorithm.returnResult(), testInput);
        } catch (InvalidInputException e) {
            fail("Exception should not have been caught!");
        }
    }

}
