package jesterMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import java.util.ArrayList;
import java.util.Iterator;

public class ChooseCardAction extends AbstractGameAction {

    private final ArrayList<AbstractCard> generatedCards;
    private final Boolean skippable;
    private final Boolean noCostForTurn;
    private final Boolean noCostForCombat;
    private boolean retrieveCard;

    public ChooseCardAction(ArrayList<AbstractCard> cardOptions, Boolean skippable, Boolean noCostForTurn, Boolean noCostForCombat) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.generatedCards=cardOptions;
        this.skippable=skippable;
        this.noCostForTurn=noCostForTurn;
        this.noCostForCombat=noCostForCombat;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.hasPower("MasterRealityPower")){
                for (AbstractCard tmpCard : generatedCards) {
                    tmpCard.upgrade();
                }
            }
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], this.skippable);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    if (this.noCostForTurn){
                        disCard.setCostForTurn(0);
                    }
                    if (this.noCostForCombat){
                        disCard.modifyCostForCombat(0);
                    }
                    disCard.current_x = -1000.0F * Settings.scale;
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    }
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;
            }
            this.tickDuration();
        }
    }    
}
