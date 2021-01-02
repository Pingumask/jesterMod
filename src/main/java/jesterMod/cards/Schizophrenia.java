package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.SchizophreniaPower;
import static jesterMod.jesterMod.makeCardPath;

public class Schizophrenia extends AbstractJesterCard {
    /*
     * Choose a red, green, blue or purle card to add to your hand at the start of each turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Schizophrenia.class.getSimpleName());
    public static final String IMG = makeCardPath("Schizophrenia.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    // /STAT DECLARATION/

    public Schizophrenia() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SchizophreniaPower(p,p, 1), 1));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}