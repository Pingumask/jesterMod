package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.*;

public class HarlequinCap extends CustomRelic {

    /*
     * Choose a card to draw on shuffle. Replaces JesterCap.(max 3 times per turn)
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("HarlequinCap");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HarlequinCap.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("HarlequinCap.png"));

    public HarlequinCap() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onShuffle(){
        if (counter>0) {
            flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new BetterDrawPileToHandAction(1));
            counter--;
            if (counter<=0) grayscale=true;
        }
    }

    @Override
    public void atTurnStart() {
        counter = 3;
        grayscale=false;
    }

    @Override
    public void onVictory() {
        super.onVictory();
        counter = 3;
        grayscale=false;
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(JesterCap.ID);
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(JesterCap.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(JesterCap.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
