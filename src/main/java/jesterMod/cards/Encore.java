package jesterMod.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import java.util.ArrayList;
import static jesterMod.jesterMod.makeCardPath;

public class Encore extends AbstractJesterCard {
    /*
     * Replays the previous card, exhaust, (bouncing)
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Encore.class.getSimpleName());
    public static final String IMG = makeCardPath("Encore.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    // /STAT DECLARATION/

    public Encore() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> usedPile = (AbstractDungeon.actionManager.cardsPlayedThisCombat);
        for (int i=usedPile.size()-1;i>=0;i--){
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i).getClass()!=this.getClass()){
                GameActionManager.queueExtraCard(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i).makeStatEquivalentCopy(), m);
                return;
            }
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size()==0){
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.cardUpdate();
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.cardUpdate();
    }

    public void cardUpdate(){
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size()>0) {
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).cardID!="jesterMod:Encore") {
                this.cardsToPreview = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1).makeStatEquivalentCopy();
            }
            else if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size()>1){
                this.cardsToPreview = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2).makeStatEquivalentCopy();
            }
            this.target=cardsToPreview.target;
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isBouncing=true;
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}