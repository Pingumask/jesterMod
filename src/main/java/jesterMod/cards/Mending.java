package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.MendingPower;
import static jesterMod.jesterMod.makeCardPath;

public class Mending extends AbstractJesterCard {
    /*
     * Whenever you discard a card, gain 1 Armor.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Mending.class.getSimpleName());
    public static final String IMG = makeCardPath("Mending.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int ARMOR = 1;
    // /STAT DECLARATION/

    public Mending() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber= baseMagicNumber = ARMOR;
    }
    
    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MendingPower(p,p, magicNumber), magicNumber));
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