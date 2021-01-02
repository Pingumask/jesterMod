package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.NoBlockPower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class PerfectDodge extends AbstractJesterCard {
    /*
     * Gain 1 buffer, can't gain armor from cards, until end of next turn
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(PerfectDodge.class.getSimpleName());
    public static final String IMG = makeCardPath("PerfectDodge.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int POWER = 1;
    private static final int DEBUFF = 2;
    private static final int DEBUFF_STACK = 1;
    // /STAT DECLARATION/


    public PerfectDodge() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = POWER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BufferPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new NoBlockPower(p, DEBUFF, false), DEBUFF_STACK));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}