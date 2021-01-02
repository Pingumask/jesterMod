package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class MirrorMask extends CustomRelic implements OnReceivePowerRelic {
    /*
     * When you receive a debuff, apply the same debuff to a random ennemy
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("MirrorMask");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MirrorMask.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MirrorMask.png"));

    public MirrorMask() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature){
        if (abstractPower.ID=="Vulnerable" || abstractPower.ID=="Weakened") {
            this.flash();
            AbstractCreature m = AbstractDungeon.getRandomMonster();
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, abstractPower, abstractPower.amount));
        }
        return true;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
