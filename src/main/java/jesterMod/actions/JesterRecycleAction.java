package jesterMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import static jesterMod.jesterMod.makeID;


public class JesterRecycleAction extends AbstractGameAction {

    public JesterRecycleAction(int amount) {
        this.amount=amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, amount, !AbstractDungeon.player.hasRelic(makeID("LoadedDice"))));
        addToBot(new DrawCardAction(AbstractDungeon.player, amount));
        isDone=true;
    }
}
