package com.number.to.word.NbrToWord;

import org.junit.Test;

import junit.framework.TestCase;

public class NumberToWordConverterTest extends TestCase {
	
	
	private NumberToWordConverter nbr2WordCn = new NumberToWordConverter();
	
	//Pass
    @Test
    public void testHelloWorld() {
        assertEquals("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine", nbr2WordCn.convert("999,999,999"));
    }

    //Fail
    @Test
    public void testNumber10() {
    	//Assert.assertNotEquals(unexpected, actual);
    
    	assertNotSame("Fails", nbr2WordCn.convert("67867"));
    }

}
