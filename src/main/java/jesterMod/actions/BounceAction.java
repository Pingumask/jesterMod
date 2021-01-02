package jesterMod.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static jesterMod.jesterMod.makeID;


public class BounceAction extends AbstractGameAction {

    public AbstractCard card;

    public BounceAction(AbstractCard card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.card=card;
    }

    public void update() {
        if (AbstractDungeon.player.hand.size()< BaseMod.MAX_HAND_SIZE) {
            if (AbstractDungeon.player.discardPile.contains(card)) AbstractDungeon.player.discardPile.moveToHand(card);
            else if (AbstractDungeon.player.drawPile.contains(card)) AbstractDungeon.player.drawPile.moveToHand(card);
            else if (AbstractDungeon.player.hand.contains(card)) addToBot(new DrawCardAction(1));
        }
        isDone=true;
    }
}
