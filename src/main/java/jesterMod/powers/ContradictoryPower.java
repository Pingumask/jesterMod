package jesterMod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

public class ContradictoryPower extends AbstractPower implements CloneablePowerInterface, OnReceivePowerPower {
    public AbstractCreature source;

    public static final String POWER_ID = jesterMod.makeID("Contradictory");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("jesterModResources/images/powers/Contradictory84.png");
    private static final Texture tex32 = TextureLoader.getTexture("jesterModResources/images/powers/Contradictory32.png");

    public ContradictoryPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source){
        if ((owner.isPlayer && target.isPlayer) || target==owner){
            if(power.getClass()== WeakPower.class || power.getClass() == VulnerablePower.class || power.getClass() == FrailPower.class){
                this.flash();
                this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
                return true;
            }
        }
        return true;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] ;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ContradictoryPower(owner, source, amount);
    }
}
