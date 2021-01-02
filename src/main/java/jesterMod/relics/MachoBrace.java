package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class MachoBrace extends CustomRelic implements OnReceivePowerRelic {
    /*
     * Gain 1 Energy. You can neither gain nor lose Strength and Dexterity
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("MachoBrace");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MachoBrace.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MachoBrace.png"));

    public MachoBrace() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.HEAVY);
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature){
        if (abstractPower.ID== StrengthPower.POWER_ID || abstractPower.ID==DexterityPower.POWER_ID) {
            this.flash();
            return false;
        }
        else if (abstractPower.ID== LoseStrengthPower.POWER_ID || abstractPower.ID== LoseDexterityPower.POWER_ID){
            return false;
        }
        return true;
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
