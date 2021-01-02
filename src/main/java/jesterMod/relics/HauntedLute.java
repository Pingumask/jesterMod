package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class HauntedLute extends CustomRelic {
    /*
     * 1 More energy. Discard a random card at the start of every turn
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("HauntedLute");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("HauntedLute.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("HauntedLute.png"));

    public HauntedLute() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void atTurnStartPostDraw(){
        this.flash();
        this.addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, 1, true));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
