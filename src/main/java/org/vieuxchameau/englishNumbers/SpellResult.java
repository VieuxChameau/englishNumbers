package org.vieuxchameau.englishNumbers;

public class SpellResult {
	private static final String MINUS = "minus";
	private static final String SPACE = " ";
	private static final String AND = " and ";
	private final String spellResult;

	private SpellResult(final String spellResult) {
		this.spellResult = spellResult;
	}

	public static SpellResult from(final String number) {
		return new SpellResult(number);
	}

	public SpellResult minus() {
		return new SpellResult(MINUS + SPACE + spellResult);
	}

	public SpellResult with(final SpellResult rhSpell) {
		return new SpellResult(this.spellResult + SPACE + rhSpell.spellResult);
	}

	public SpellResult with(final String literal) {
		return new SpellResult(this.spellResult + SPACE + literal);
	}

	public SpellResult and(final SpellResult rhSpell) {
		return new SpellResult(this.spellResult + AND + rhSpell.spellResult);
	}

	public String asString() {
		return spellResult;
	}

}
