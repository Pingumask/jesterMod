package jesterMod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.HandOfGreed;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.actions.ChooseCardAction;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import java.util.ArrayList;
import static jesterMod.jesterMod.makeCardPath;

public class Pillage extends AbstractJesterCard {
    /*
     * Choose Hand of Greed, Boil Down, Feed or Lesson Learned to add to your hand. (Retain)
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Pillage.class.getSimpleName());
    public static final String IMG = makeCardPath("Pillage.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private final ArrayList<AbstractCard> cardsChoice= new ArrayList();
    // /STAT DECLARATION/

    public Pillage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        cardsChoice.add(new Feed());
        cardsChoice.add(new LessonLearned());
        cardsChoice.add(new BoilDown());
        cardsChoice.add(new HandOfGreed());
        cardsToPreview = new BoilDown();
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChooseCardAction(this.cardsChoice, true, false, false));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain=true;
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}