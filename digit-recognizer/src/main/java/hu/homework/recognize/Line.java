package hu.homework.recognize;

import java.util.Arrays;

public class Line {

	public static final int SIZE = 9;

	private Digit[] digits = new Digit[SIZE];

	public Line(String[] lines) {
		super();

		for (int i = 0; i < SIZE; i++) {

			int begin = i * 3;
			Digit digit = new Digit(i, lines[0].substring(begin, begin + 3));
			if (!digit.isInvalid()) {
				digit.addLine(lines[1].substring(begin, begin + 3));
			}
			if (!digit.isInvalid()) {
				digit.addLine(lines[2].substring(begin, begin + 3));
			}

			digits[i] = digit;
		}
	}

	public String recognize() {
		return Arrays.stream(digits).parallel().collect(DigitCollector.toCollectedString(Line.SIZE));
	}

}
