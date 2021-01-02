package jesterMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.actions.JesterRecycleAction;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Prank extends AbstractJesterCard {
    /*
     * Cycle 2. Exhaust. Bouncing. (Retain)
     */

    // TEXT DECLARATION

    public static final String ID = jesterMod.makeID(Prank.class.getSimpleName());
    public static final String IMG = makeCardPath("Prank.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;
    private static final int RECYCLE = 2;
    // /STAT DECLARATION/

    public Prank() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust=true;
        isBouncing=true;
        magicNumber = baseMagicNumber = RECYCLE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JesterRecycleAction(magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            selfRetain=true;
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}