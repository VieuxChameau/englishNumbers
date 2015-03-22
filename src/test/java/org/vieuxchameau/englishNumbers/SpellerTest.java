package org.vieuxchameau.englishNumbers;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SpellerTest {
	private final Speller speller = new Speller();

	@Test(expected = IllegalArgumentException.class)
	public void should_not_spell_for_number_over_bound() throws Exception {
		// given
		final int numberToSpell = Integer.MIN_VALUE;

		// when
		speller.spell(numberToSpell);

		// then error expected
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_not_spell_for_number_under_bound() throws Exception {
		// given
		final int numberToSpell = Integer.MAX_VALUE;

		// when
		speller.spell(numberToSpell);

		// then error expected
	}

	@Parameters({
			"-56945781, minus fifty six million nine hundred and forty five thousand seven hundred and eighty one",
			"0, zero",
			"105, one hundred and five",
			"400, four hundred",
			"420, four hundred and twenty",
			"4000, four thousand",
			"4200, four thousand two hundred",
			"40000, forty thousand",
			"42000, forty two thousand",
			"400000, four hundred thousand",
			"420000, four hundred and twenty thousand",
			"4000000, four million",
			"4200000, four million two hundred thousand",
			"40000000, forty million",
			"42000000, forty two million",
			"56945781, fifty six million nine hundred and forty five thousand seven hundred and eighty one",
			"400000000, four hundred million",
			"420000000, four hundred and twenty million",
			"987654321, nine hundred and eighty seven million six hundred and fifty four thousand three hundred and twenty one",
			"123456789, one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine"})
	@Test
	public void should_spell(final int numberToSpell, final String expectedResult) throws Exception {
		// given

		// when
		final String spellingResult = speller.spell(numberToSpell);

		// then error expected
		assertThat(spellingResult).isEqualTo(expectedResult);
	}
}