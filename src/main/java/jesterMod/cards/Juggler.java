package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.JugglerPower;
import static jesterMod.jesterMod.makeCardPath;

public class Juggler extends AbstractJesterCard {
    /*
     * When you discard a card, add a JugglinBall to your hand.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Juggler.class.getSimpleName());
    public static final String IMG = makeCardPath("Juggler.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;
    // /STAT DECLARATION/

    public Juggler() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        cardsToPreview = new JugglingBall();
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new JugglerPower(p,p, 1), 1));
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