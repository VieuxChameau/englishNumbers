package org.vieuxchameau.englishNumbers;

public enum BigNumber {
	DECADE {
		@Override
		public SpellResult spell(final int numberToSpell) {
			return Number.spell(numberToSpell);
		}
	},
	HUNDRED("hundred", 100, DECADE),
	THOUSAND("thousand", 1_000, HUNDRED),
	MILLION("million", 1_000_000, THOUSAND);

	private final String literal;
	private final int numeric;
	private final BigNumber next;

	public static final BigNumber BIGGEST_NUMBER = MILLION;

	private BigNumber(final String literal, final int numeric, final BigNumber next) {
		this.literal = literal;
		this.numeric = numeric;
		this.next = next;
	}

	private BigNumber() {
		this("", 10, null);
	}

	public SpellResult spell(final int numberToSpell) {
		final int quotient = numberToSpell / numeric;
		final int remainder = numberToSpell % numeric;
		if (shouldSpellQuotient(quotient)) {
			final SpellResult quotientRes = next.spell(quotient).with(literal);
			if (remainder == 0) {
				return quotientRes;
			}
			return join(quotientRes, next.spell(remainder));
		}

		return next.spell(remainder);
	}

	/**
	 * If the number apply to the current range , has a quotient,  spell it
	 */
	private boolean shouldSpellQuotient(final int quotient) {
		return quotient > 0;
	}

	/**
	 * For hundred number, we use "and" to join to the remainder part.
	 * For other a space is used.
	 */
	private SpellResult join(final SpellResult quotientRes, final SpellResult remainderRes) {
		if (this == HUNDRED) {
			return quotientRes.and(remainderRes);
		}
		return quotientRes.with(remainderRes);
	}
}
