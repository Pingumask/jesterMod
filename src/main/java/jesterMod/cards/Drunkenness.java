package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.DrunkennessPower;
import static jesterMod.jesterMod.makeCardPath;

public class Drunkenness extends AbstractJesterCard {
    /*
     * Gain -2 to +3 Strength and Dexterity at the start of each turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Drunkenness.class.getSimpleName());
    public static final String IMG = makeCardPath("Drunkenness.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 2;
    private static final int MIN = -2;
    private static final int MAX = 3;
    // /STAT DECLARATION/

    public Drunkenness() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = MIN;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = MAX;
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DrunkennessPower(p,p, 1), 1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate=true;
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}