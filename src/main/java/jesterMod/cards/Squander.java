package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Squander extends AbstractJesterCard {
    /*
     * Discard your draw pile. Exhaust.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Squander.class.getSimpleName());
    public static final String IMG = makeCardPath("Squander.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 0;
    private static final int DISCARD = 3;
    private static final int UPGRADE_DISCARD = 1;
    // /STAT DECLARATION/

    public Squander() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DISCARD;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count=0;
        for (AbstractCard thrown : AbstractDungeon.player.drawPile.group){
            if(++count>magicNumber){break;}
            addToBot(new DiscardSpecificCardAction(thrown, AbstractDungeon.player.drawPile));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_DISCARD);
            this.initializeDescription();
        }
    }
}