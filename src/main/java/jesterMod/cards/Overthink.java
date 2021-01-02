package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.actions.JesterRecycleAction;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Overthink extends AbstractJesterCard {
    /*
     * Cycle 3(5) times. Gain 1(2) Energy.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Overthink.class.getSimpleName());
    public static final String IMG = makeCardPath("Overthink.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 0;
    private static final int CYCLE = 3;
    private static final int ENERGY = 1;
    private static final int UPGRADE_PLUS_ENERGY = 1;
    // /STAT DECLARATION/

    public Overthink() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CYCLE;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = ENERGY;
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new JesterRecycleAction(magicNumber));
        addToBot(new GainEnergyAction(defaultSecondMagicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_ENERGY);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}