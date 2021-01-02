package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Inspiration extends AbstractJesterCard {
    /*
     * Unplayable. Gain 1 energy when drawn. (Elusive).
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Inspiration.class.getSimpleName());
    public static final String IMG = makeCardPath("Inspiration.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = -2;
    private static final int ENERGY = 1;
    // /STAT DECLARATION/

    public Inspiration() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ENERGY;
        isElusive=false;
    }

    @Override
    public void triggerWhenDrawn() {
        this.addToBot(new GainEnergyAction(magicNumber));
        super.triggerWhenDrawn();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isElusive = true;
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}