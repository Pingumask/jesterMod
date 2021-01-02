package jesterMod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import jesterMod.cards.Prank;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class DrunkennessPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = jesterMod.makeID("Drunkenness");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int str;
    private int dex;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("jesterModResources/images/powers/Drunkenness84.png");
    private static final Texture tex32 = TextureLoader.getTexture("jesterModResources/images/powers/Drunkenness32.png");

    public DrunkennessPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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
    public void atStartOfTurn(){
        this.flash();
        str = cardRandomRng.random(amount*-2,amount*3);
        dex = cardRandomRng.random(amount*-2,amount*3);
        if (str!=0) this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, str), str));
        if (dex!=0) this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner, dex), dex));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount*-2 + DESCRIPTIONS[1] + amount*3 + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new DrunkennessPower(owner, source, amount);
    }
}
