package org.vieuxchameau.englishNumbers;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class NumberTest {

	@Parameters({
			"0, zero",
			"1, one",
			"2, two",
			"3, three",
			"4, four",
			"5, five",
			"6, six",
			"7, seven",
			"8, eight",
			"9, nine",
			"10, ten",
			"11, eleven",
			"12, twelve",
			"13, thirteen",
			"14, fourteen",
			"15, fifteen",
			"16, sixteen",
			"17, seventeen",
			"18, eighteen",
			"19, nineteen",
			"20, twenty",
			"30, thirty",
			"40, forty",
			"42, forty two",
			"50, fifty",
			"60, sixty",
			"70, seventy",
			"80, eighty",
			"90, ninety",
	})
	@Test
	public void should_spell_number(final int numberToSpell, final String expectedResult) throws Exception {
		final SpellResult spell = Number.spell(numberToSpell);

		assertThat(spell.asString()).isEqualTo(expectedResult);
	}
}