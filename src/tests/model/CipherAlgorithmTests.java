package model;

import exceptions.InvalidChoiceException;
import exceptions.InvalidShiftKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CipherAlgorithmTests {
    private final String testInput = "My name is Rohan Joseph";
    private final String testOutput = "Vh wjvn rb Axqjw Sxbnyq";
    private final String encode = "encode";
    private final String decode = "decode";
    private final String invalidChoice = "invalidChoice";
    private final int validShiftKey = 9;
    private final int invalidShiftKeyUpperBound = 29;
    private final int invalidShiftKeyLowerBound = -5;
    private CipherAlgorithm testEncodeAlgorithm;
    private CipherAlgorithm testDecodeAlgorithm;

    @BeforeEach
    public void setup() {
        testEncodeAlgorithm = new CipherAlgorithm(testInput);
        testDecodeAlgorithm = new CipherAlgorithm(testOutput);
    }

    @Test
    public void testEncodeAlgorithm() {
        try {
            assertTrue(testEncodeAlgorithm.runCipher(encode, validShiftKey));
            assertEquals(testEncodeAlgorithm.returnResult(), testOutput);
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testEncodeAlgorithmInvalidShiftKeyExceptionThrown() {
        try {
            assertFalse(testEncodeAlgorithm.runCipher(encode, invalidShiftKeyLowerBound));
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            //pass
        }
        try {
            assertFalse(testEncodeAlgorithm.runCipher(encode, invalidShiftKeyUpperBound));
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            //pass
        }
        try {
            assertFalse(testEncodeAlgorithm.runCipher(invalidChoice, validShiftKey));
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
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
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            //pass
        }
        try {
            assertFalse(testDecodeAlgorithm.runCipher(decode, invalidShiftKeyUpperBound));
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            //pass
        }
        try {
            assertFalse(testDecodeAlgorithm.runCipher(invalidChoice, validShiftKey));
            fail("Exception should have been thrown");
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            //pass
        }
    }

    @Test
    public void testReturnOutputAfterEncode() {
        try {
            testEncodeAlgorithm.runCipher(encode, validShiftKey);
            assertEquals(testEncodeAlgorithm.returnResult(), testOutput);
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testReturnInputAfterDecode() {
        try {
            testDecodeAlgorithm.runCipher(decode, validShiftKey);
            assertEquals(testDecodeAlgorithm.returnResult(), testInput);
        } catch (InvalidShiftKeyException | InvalidChoiceException e) {
            fail("Exception should not have been caught!");
        }
    }

}
