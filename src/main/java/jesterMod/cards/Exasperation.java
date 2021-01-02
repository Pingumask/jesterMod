package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.ExasperationPower;
import static jesterMod.jesterMod.makeCardPath;

public class Exasperation extends AbstractJesterCard {
    /*
     * Deal 7(10) damage to all enemies when shuffling your draw pile.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Exasperation.class.getSimpleName());
    public static final String IMG = makeCardPath("Exasperation.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 3;
    // /STAT DECLARATION/

    public Exasperation() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber= baseMagicNumber = DAMAGE;
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ExasperationPower(p,p, magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}