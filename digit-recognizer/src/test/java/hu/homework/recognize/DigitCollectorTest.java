package hu.homework.recognize;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DigitCollectorTest {

	@Test
	public void test_collect_paralell_empty() {
		
		List<Digit> digits = new ArrayList<>();
		
		Assertions.assertEquals("", digits.stream().parallel().collect(DigitCollector.toCollectedString(digits.size())));
	}
	
	@Test
	public void test_collect() {
		
		List<Digit> digits = new ArrayList<>();
		

		Digit digit1 = new Digit(0, "   ");
		digit1.addLine("|_|");
		digit1.addLine("  |");
		Digit digit2 = new Digit(1, "   ");
		digit2.addLine("  |");
		digit2.addLine("  |");
		Digit digit3 = new Digit(2, " _ ");
		digit3.addLine("  |");
		digit3.addLine("  |");
		Digit digit4 = new Digit(3, " _ ");
		digit4.addLine("|_ ");
		digit4.addLine("|_|");
		Digit digit5 = new Digit(4, "   ");
		digit5.addLine("  |");
		digit5.addLine("  |");
		
		digits.add(digit5);
		digits.add(digit1);
		digits.add(digit3);
		digits.add(digit2);
		digits.add(digit4);
		
		Assertions.assertEquals("41761", digits.stream().collect(DigitCollector.toCollectedString(digits.size())));
	}
	
	@Test
	public void test_collect_paralell() {
		
		List<Digit> digits = new ArrayList<>();
		
		Digit digit1 = new Digit(0, "   ");
		digit1.addLine("|_|");
		digit1.addLine("  |");
		Digit digit2 = new Digit(1, "   ");
		digit2.addLine("  |");
		digit2.addLine("  |");
		Digit digit3 = new Digit(2, " _ ");
		digit3.addLine("  |");
		digit3.addLine("  |");
		Digit digit4 = new Digit(3, " _ ");
		digit4.addLine("|_ ");
		digit4.addLine("|_|");
		Digit digit5 = new Digit(4, "   ");
		digit5.addLine("  |");
		digit5.addLine("  |");
		
		digits.add(digit2);
		digits.add(digit1);
		digits.add(digit3);
		digits.add(digit5);
		digits.add(digit4);
		
		Assertions.assertEquals("41761", digits.stream().parallel().collect(DigitCollector.toCollectedString(digits.size())));
	}
	
	@Test
	public void test_collect_invalid() {
		
		List<Digit> digits = new ArrayList<>();

		Digit digit1 = new Digit(0, "---");
		
		Digit digit2 = new Digit(1, " _ ");
		digit2.addLine("|_ ");
		digit2.addLine(" _|");
		
		digits.add(digit2);
		digits.add(digit1);
		
		Assertions.assertEquals("?5 ILLEGAL", digits.stream().collect(DigitCollector.toCollectedString(digits.size())));
	}
}
