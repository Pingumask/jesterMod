package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Leeches extends AbstractJesterCard {
    /*
     * Lose 12(9) life. Gain 6 Regeneration.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Leeches.class.getSimpleName());
    public static final String IMG = makeCardPath("Leeches.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int LIFE_COST = 12;
    private static final int UPGRADE_LIFE_COST = -3;
    private static final int REGEN = 6;
    // /STAT DECLARATION/

    public Leeches() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = LIFE_COST;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = REGEN;
        this.tags.add(CardTags.HEALING);
    }
    
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, defaultSecondMagicNumber), defaultSecondMagicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_LIFE_COST);
            initializeDescription();
        }
    }
}