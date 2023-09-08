package hu.homework;

public class Digit {

	private boolean invalid;

	private final int index;

	private String readed = "";

	public Digit(int index, String firstLine) {

//		System.out.println("firstLine: " + firstLine);
		this.index = index;

		if (!" _ ".equals(firstLine) && !"   ".equals(firstLine)) {
			invalid = true;
		} else {
			readed += firstLine;
		}

	}

	public boolean isInvalid() {
		return invalid;
	}

	public int getIndex() {
		return index;
	}

	public void initSecondLine(String secondLine) {
		readed += secondLine;

	}

	public void initThirdLine(String thirdLine) {
		readed += thirdLine;
	}

	public char getChar() {

//		System.out.println("readed (" + index + "):" + readed);

		if (invalid) {
			return '?';
		}

		long emptycount = readed.chars().filter(character -> ' ' == character).count();

		// do the recognize

		if (2 == emptycount) {
			if (" _ |_||_|".equals(readed)) {
				return '8';
			}
			invalid = true;
			return '?';
		} else if (7 == emptycount) {
			if ("     |  |".equals(readed)) {
				return '1';
			}
			invalid = true;
			return '?';
		} else if (6 == emptycount) {
			if (" _   |  |".equals(readed)) {
				return '7';
			}
			invalid = true;
			return '?';
		} else if (5 == emptycount) {
			if ("   |_|  |".equals(readed)) {
				return '4';
			}
			invalid = true;
			return '?';
		} else

		if (4 == emptycount) {

			if (" _  _||_ ".equals(readed)) {
				return '2';
			}

			else if (" _  _| _|".equals(readed)) {
				return '3';
			} else if (" _ |_  _|".equals(readed)) {
				return '5';
			}

			invalid = true;
			return '?';
		} else

		if (3 == emptycount) {

			if (" _ |_ |_|".equals(readed)) {
				return '6';
			} else if (" _ |_| _|".equals(readed)) {
				return '9';
			} else if (" _ | ||_|".equals(readed)) {
				return '0';
			}

			invalid = true;
			return '?';
		}

		invalid = true;
		return '?';
	}

}
