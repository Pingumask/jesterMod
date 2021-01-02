package jesterMod.powers;

import basemod.BaseMod;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jesterMod.cards.Prank;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

public class PranksterPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = jesterMod.makeID("Prankster");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private static final Texture tex84 = TextureLoader.getTexture("jesterModResources/images/powers/Prankster84.png");
    private static final Texture tex32 = TextureLoader.getTexture("jesterModResources/images/powers/Prankster32.png");

    public PranksterPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.type = PowerType.BUFF;
        this.isTurnBased = false;

        updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        this.addToBot(new MakeTempCardInHandAction(new Prank(), this.amount));
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
        } else {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new MomentumPower(owner, source, amount);
    }
}

