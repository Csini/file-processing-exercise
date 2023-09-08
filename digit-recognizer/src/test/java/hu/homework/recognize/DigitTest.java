package hu.homework.recognize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DigitTest {

	@Test
	public void test_0() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine("| |");
		digit.addLine("|_|");
		
		Assertions.assertEquals('0', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_1() {
		Digit digit = new Digit(-1, "   ");
		digit.addLine("  |");
		digit.addLine("  |");
		
		Assertions.assertEquals('1', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_2() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine(" _|");
		digit.addLine("|_ ");
		
		Assertions.assertEquals('2', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_3() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine(" _|");
		digit.addLine(" _|");
		
		Assertions.assertEquals('3', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_4() {
		Digit digit = new Digit(-1, "   ");
		digit.addLine("|_|");
		digit.addLine("  |");
		
		Assertions.assertEquals('4', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_5() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine("|_ ");
		digit.addLine(" _|");
		
		Assertions.assertEquals('5', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_6() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine("|_ ");
		digit.addLine("|_|");
		
		Assertions.assertEquals('6', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_7() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine("  |");
		digit.addLine("  |");
		
		Assertions.assertEquals('7', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_8() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine("|_|");
		digit.addLine("|_|");
		
		Assertions.assertEquals('8', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_9() {
		Digit digit = new Digit(-1, " _ ");
		digit.addLine("|_|");
		digit.addLine(" _|");
		
		Assertions.assertEquals('9', digit.getAsChar());
		
		Assertions.assertFalse(digit.isInvalid());
	}
	
	@Test
	public void test_invalid() {
		Digit digit = new Digit(-1, "   ");
		digit.addLine("|_|");
		digit.addLine("|_|");
		
		Assertions.assertEquals('?', digit.getAsChar());
		
		Assertions.assertTrue(digit.isInvalid());
	}
	
	@Test
	public void test_invalid_firstline() {
		Digit digit = new Digit(-1, "  |");
		digit.addLine("|_|");
		digit.addLine("|_|");
		
		Assertions.assertEquals('?', digit.getAsChar());
		
		Assertions.assertTrue(digit.isInvalid());
	}
	
	@Test
	public void test_invalid_thirdline() {
		Digit digit = new Digit(-1, "   ");
		digit.addLine("   ");
		digit.addLine(" _ ");
		
		Assertions.assertEquals('?', digit.getAsChar());
		
		Assertions.assertTrue(digit.isInvalid());
	}
}