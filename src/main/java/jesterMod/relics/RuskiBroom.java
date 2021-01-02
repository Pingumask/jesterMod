package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.Keyword;
import com.megacrit.cardcrawl.localization.KeywordStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class RuskiBroom extends CustomRelic implements SpecificDiscardRelicHook {
    /*
     * When you discard a curse or status, exhaust it
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("RuskiBroom");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("RuskiBroom.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("RuskiBroom.png"));

    public RuskiBroom() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.FLAT);
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onSpecificDiscard(AbstractCard discardedCard) {
        if(discardedCard.type== AbstractCard.CardType.CURSE || discardedCard.type == AbstractCard.CardType.STATUS){
            this.flash();
            addToBot(new ExhaustSpecificCardAction(discardedCard, AbstractDungeon.player.discardPile));
            addToBot(new ExhaustSpecificCardAction(discardedCard, AbstractDungeon.player.hand));
            addToBot(new ExhaustSpecificCardAction(discardedCard, AbstractDungeon.player.drawPile));
        }
    }
}
