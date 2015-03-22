package org.vieuxchameau.englishNumbers;


import static java.lang.Math.abs;

public class Speller {
	private static final int MAX_THRESHOLD = 999_999_999;
	private static final int MIN_THRESHOLD = -999_999_999;

	/**
	 * Spell a number in british english words.
	 * The number should be superior to -999.999.999 and inferior to 999.999.999
	 *
	 * @param numberToSpell the number to spell
	 * @return the equivalent {@code numberToSpell} in british english words
	 */
	public String spell(final int numberToSpell) {
		if (MIN_THRESHOLD > numberToSpell || numberToSpell > MAX_THRESHOLD) {
			throw new IllegalArgumentException("Number out of bounds");
		}

		SpellResult spellResult = BigNumber.BIGGEST_NUMBER.spell(abs(numberToSpell));
		if (numberToSpell < 0) {
			spellResult = spellResult.minus();
		}

		return spellResult.asString();
	}
}
