package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class JokerCard extends CustomRelic {
    /*
     * Every 4 times you shuffle your deck, gain 1 intangible
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("JokerCard");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("JokerCard.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("JokerCard.png"));

    public JokerCard() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        this.counter = 0;
    }

    @Override
    public void onShuffle() {
        ++this.counter;
        if (this.counter == 4) {
            this.counter = 0;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, 1), 1));
        }

    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
