package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Juggle extends AbstractJesterCard {
    /*
     * Discard your hand. Ads that many Juggling balls(+) to your hand.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Juggle.class.getSimpleName());
    public static final String IMG = makeCardPath("Juggle.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 2;
    // /STAT DECLARATION/

    public Juggle() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.cardsToPreview = new JugglingBall();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int number = AbstractDungeon.player.hand.size()-1;
        this.addToBot(new DiscardAction(p, p, number, true));
        AbstractCard cardToAdd= new JugglingBall();
        if (upgraded){
            cardToAdd.upgrade();
        }
        this.addToBot(new MakeTempCardInHandAction(cardToAdd,number));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            AbstractCard upCard=new JugglingBall();
            upCard.upgrade();
            this.cardsToPreview=upCard;
            this.initializeDescription();
        }
    }
}