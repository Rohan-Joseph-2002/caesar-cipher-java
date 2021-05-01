package model;

import exceptions.InvalidShiftKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CipherAlgorithmTests {
    private final String testInput = "My name is Rohan Joseph";
    private final String testOutput = "Vh wjvn rb Axqjw Sxbnyq";
    private final String encode = "encode";
    private final String decode = "decode";
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
            assertTrue(testEncodeAlgorithm.algorithm(encode, validShiftKey));
        } catch (InvalidShiftKeyException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testEncodeAlgorithmInvalidShiftKeyExceptionThrown() {
        try {
            testEncodeAlgorithm.algorithm(encode, invalidShiftKeyLowerBound);
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException e) {
            //pass
        }
        try {
            testEncodeAlgorithm.algorithm(encode, invalidShiftKeyUpperBound);
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException e) {
            //pass
        }
    }

    @Test
    public void testDecodeAlgorithm() {
        try {
            assertTrue(testDecodeAlgorithm.algorithm(decode, validShiftKey));
        } catch (InvalidShiftKeyException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testDecodeAlgorithmInvalidShiftKeyExceptionThrown() {
        try {
            testDecodeAlgorithm.algorithm(decode, invalidShiftKeyLowerBound);
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException e) {
            //pass
        }
        try {
            testDecodeAlgorithm.algorithm(decode, invalidShiftKeyUpperBound);
            fail("Exception should have been thrown!");
        } catch (InvalidShiftKeyException e) {
            //pass
        }
    }

    @Test
    public void testReturnOutputAfterEncode() {
        try {
            testEncodeAlgorithm.algorithm(encode, validShiftKey);
            assertEquals(testEncodeAlgorithm.returnResult(), testOutput);
        } catch (InvalidShiftKeyException e) {
            fail("Exception should not have been caught!");
        }
    }

    @Test
    public void testReturnInputAfterDecode() {
        try {
            testDecodeAlgorithm.algorithm(decode, validShiftKey);
            assertEquals(testDecodeAlgorithm.returnResult(), testInput);
        } catch (InvalidShiftKeyException e) {
            fail("Exception should not have been caught!");
        }
    }

}
