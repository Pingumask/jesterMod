package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.PranksterPower;
import static jesterMod.jesterMod.makeCardPath;

public class Prankster extends AbstractJesterCard {
    /*
     * Add a prank to your hand each turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Prankster.class.getSimpleName());
    public static final String IMG = makeCardPath("Prankster.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int PRANKSTER = 1;
    // /STAT DECLARATION/

    public Prankster() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = PRANKSTER;
        this.cardsToPreview = new Prank();
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PranksterPower(p,p, magicNumber), magicNumber));
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