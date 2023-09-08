package hu.homework;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

public class DigitCollector implements Collector<Digit, StringBuilder, String> {

	private static final char EMPTY_CHAR = ' ';

	private boolean invalid = false;

	private final int size;

	public DigitCollector(int size) {
		this.size = size;
	}

	public static DigitCollector toCollectedString(int size) {
		return new DigitCollector(size);
	}

	@Override
	public Supplier<StringBuilder> supplier() {
		return () -> {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < size; i++) {
				s.append(EMPTY_CHAR);
			}
			return s;
		};
	}

	@Override
	public BiConsumer<StringBuilder, Digit> accumulator() {
		return (output, input) -> {

			output.setCharAt(input.getIndex(), input.getChar());

			if (input.isInvalid()) {
				invalid = true;
			}
		};
	}

	@Override
	public BinaryOperator<StringBuilder> combiner() {
		return (output1, output2) -> {

			StringBuilder combined = new StringBuilder();

			for (int i = 0; i < size; i++) {
				if (EMPTY_CHAR != output1.charAt(i)) {
					combined.append(output1.charAt(i));
				} else {
					combined.append(output2.charAt(i));
				}
			}
			return combined;
		};
	}

	@Override
	public Function<StringBuilder, String> finisher() {
		return (StringBuilder result) -> {

			if (invalid) {
				result.append(" ILLEGAL");
			}
			return result.toString();
		};
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Set.of(Characteristics.CONCURRENT);
	}
}
