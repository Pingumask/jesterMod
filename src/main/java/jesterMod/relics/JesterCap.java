package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class JesterCap extends CustomRelic {

    /*
     * Draw a card on shuffle. (max 3 times per turn)
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("JesterCap");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("JesterCap.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("JesterCap.png"));

    public JesterCap() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onShuffle(){
        if (counter>0) {
            flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 1));
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

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
