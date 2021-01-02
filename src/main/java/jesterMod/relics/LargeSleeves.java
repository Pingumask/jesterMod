package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class LargeSleeves extends CustomRelic {
    /*
     * Every 10 cards discarded, draw a card
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("LargeSleeves");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LargeSleeves.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LargeSleeves.png"));

    public LargeSleeves() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }

    @Override
    public void onManualDiscard() {
        ++this.counter;
        if (this.counter >= 10) {
            this.counter -= 10;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(1));
        }

    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
