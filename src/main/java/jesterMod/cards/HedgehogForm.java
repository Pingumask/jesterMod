package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.HedgehogPower;
import static jesterMod.jesterMod.makeCardPath;

public class HedgehogForm extends AbstractJesterCard {
    /*
     * Gain 2(3) Thorns at the start of each turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(HedgehogForm.class.getSimpleName());
    public static final String IMG = makeCardPath("HedgehogForm.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 3;
    private static final int AMOUNT = 2;
    private static final int UPGRADE_AMOUNT = 1;
    // /STAT DECLARATION/

    public HedgehogForm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new HedgehogPower(p,p, magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_AMOUNT);
            initializeDescription();
        }
    }
}