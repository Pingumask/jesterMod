package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.ArrythmiaPower;
import static jesterMod.jesterMod.makeCardPath;
public class Arrythmia extends AbstractJesterCard {
    /*
     * Alternatively gain or lose Energy at the start of each turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Arrythmia.class.getSimpleName());
    public static final String IMG = makeCardPath("Arrythmia.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;

    // /STAT DECLARATION/

    public Arrythmia() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ArrythmiaPower(p,p, 1), 1));
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