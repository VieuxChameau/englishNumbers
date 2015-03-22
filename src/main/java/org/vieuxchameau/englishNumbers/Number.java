package org.vieuxchameau.englishNumbers;

import java.util.Arrays;
import java.util.List;

import static org.vieuxchameau.englishNumbers.SpellResult.from;

public enum Number {

	ZERO("zero", "ten"),
	FIRST("one", "eleven"),
	SECOND("two", "twelve", "twenty"),
	THIRD("three", "thirteen", "thirty"),
	FOURTH("four", "fourteen", "forty"),
	FIFTH("five", "fifteen", "fifty"),
	SIXTH("six", "sixteen", "sixty"),
	SEVENTH("seven", "seventeen", "seventy"),
	EIGHTH("eight", "eighteen", "eighty"),
	NINETH("nine", "nineteen", "ninety");

	private static final int DIVISOR = 10;
	/**
	 * Numbers inferior to ten are in the first column
	 */
	private static final int BASIC_NUMBER = 0;
	/**
	 * Second column for 10 <= number < 20
	 */
	private static final int FIRST_DECADE_NUMBER = 1;
	/**
	 * Third column for decade => 20
	 */
	private static final int DECADE_NUMBER = 2;

	private final List<String> literals;

	private Number(final String... number) {
		this.literals = Arrays.asList(number);
	}

	public static SpellResult spell(final int numberToSpell) {
		final Number[] numbers = values();
		if (isBasicNumber(numberToSpell)) {
			return from(numbers[numberToSpell].basicNumber());
		}

		final int decade = numberToSpell / DIVISOR;
		final int unit = numberToSpell % DIVISOR;
		if (isFirstDecadeNumber(decade)) {
			return from(numbers[unit].firstDecadeNumber());
		}

		if (unit != 0) {
			return from(numbers[decade].decadeNumber()).with(spell(unit));
		}
		return from(numbers[decade].decadeNumber());
	}


	/**
	 * 10 <= NumberToSpell < 20
	 */
	private static boolean isFirstDecadeNumber(final int quotient) {
		return quotient == 1;
	}

	/**
	 * 0 <= NumberToSpell < 10
	 */
	private static boolean isBasicNumber(final int numberToSpell) {
		return numberToSpell < 10;
	}

	private String basicNumber() {
		return literals.get(BASIC_NUMBER);
	}

	private String firstDecadeNumber() {
		return literals.get(FIRST_DECADE_NUMBER);
	}

	private String decadeNumber() {
		return literals.get(DECADE_NUMBER);
	}
}
