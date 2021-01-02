package jesterMod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

public class ArrythmiaPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = jesterMod.makeID("Arrythmia");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static boolean accelerando=true;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture texplus84 = TextureLoader.getTexture("jesterModResources/images/powers/ArrythmiaPlus84.png");
    private static final Texture texmin84 = TextureLoader.getTexture("jesterModResources/images/powers/ArrythmiaMinus84.png");
    private static final Texture texplus32 = TextureLoader.getTexture("jesterModResources/images/powers/ArrythmiaPlus32.png");
    private static final Texture texmin32 = TextureLoader.getTexture("jesterModResources/images/powers/ArrythmiaMinus32.png");

    public ArrythmiaPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(texplus84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(texplus32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        if (accelerando){
            this.addToBot(new GainEnergyAction(amount+1));
            this.region128 = new TextureAtlas.AtlasRegion(texmin84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(texmin32, 0, 0, 32, 32);
        }
        else{
            this.addToBot(new LoseEnergyAction(1));
            this.region128 = new TextureAtlas.AtlasRegion(texplus84, 0, 0, 84, 84);
            this.region48 = new TextureAtlas.AtlasRegion(texplus32, 0, 0, 32, 32);
        }
        this.flash();
        accelerando=!accelerando;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
        for (int i=0;i<amount+1;i++){
            description+="[E] ";
        }
        description+=DESCRIPTIONS[1];
        if (accelerando){
            description+=DESCRIPTIONS[2];
        }
        else{
            description+=DESCRIPTIONS[3];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ArrythmiaPower(owner, source, amount);
    }
}
