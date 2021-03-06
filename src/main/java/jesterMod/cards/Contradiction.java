package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.ContradictoryPower;
import static jesterMod.jesterMod.makeCardPath;

public class Contradiction extends AbstractJesterCard {
    /*
     * Weirdness Apply X (+1) keywords to yourself.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Contradiction.class.getSimpleName());
    public static final String IMG = makeCardPath("Contradiction.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int MAGIC = 1;
    // /STAT DECLARATION/

    public Contradiction() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber =MAGIC;
    }
    
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ContradictoryPower(p, p,magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.isInnate=true;
            initializeDescription();
        }
    }
}