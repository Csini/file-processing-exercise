package hu.homework;

import java.util.Arrays;

public class Line {

	public static final int SIZE = 9;

	private Digit[] digits = new Digit[SIZE];

	public Line(String[] lines) {
		super();

//		Pattern.compile(".{1,3}").matcher(text).results().map(MatchResult::group).collect(Collectors.toList());

		// line1
//		lines[0].chars()

		for (int i = 0; i < SIZE; i++) {

			int begin = i * 3;
			Digit digit = new Digit(i, lines[0].substring(begin, begin + 3));
			if (!digit.isInvalid()) {
				digit.initSecondLine(lines[1].substring(begin, begin + 3));
			}
			if (!digit.isInvalid()) {
				digit.initThirdLine(lines[2].substring(begin, begin + 3));
			}

			digits[i] = digit;
		}
	}

	public String recognize() {
		return Arrays.stream(digits).parallel().collect(DigitCollector.toCollectedString(Line.SIZE));
	}

}
