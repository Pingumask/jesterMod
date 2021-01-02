package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class LoadedDice extends CustomRelic {
    /*
     * Jester's random discards are unrandomized
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("LoadedDice");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("LoadedDice.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("LoadedDice.png"));

    public LoadedDice() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
