package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Flee extends AbstractJesterCard {
    /*
     * Unplayable. Gain 7 Block when discarded. (Bouncing)
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Flee.class.getSimpleName());
    public static final String IMG = makeCardPath("Flee.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = -2;
    private static final int BLOCK = 7;
    // /STAT DECLARATION/

    public Flee() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        block = baseBlock = BLOCK;
    }

    @Override
    public void triggerOnManualDiscard() {
        this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
        super.triggerOnManualDiscard();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isBouncing=true;
            this.rawDescription= cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}