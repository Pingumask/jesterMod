package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jesterMod.jesterMod;
import jesterMod.powers.DisposophobePower;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class PhilosopherAmphora extends CustomRelic{
    /*
     * At the start of your turn, Apply 1 disposophobe to a random ennemy
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("PhilosopherAmphora");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("PhilosopherAmphora.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("PhilosopherAmphora.png"));

    public PhilosopherAmphora() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.SOLID);
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        flash();
        AbstractCreature m=AbstractDungeon.getRandomMonster();
        this.addToBot(new RelicAboveCreatureAction(m, this));
        addToBot(new ApplyPowerAction(m,(AbstractCreature)null,new DisposophobePower(m,(AbstractCreature)null,1),1));
    }
}
