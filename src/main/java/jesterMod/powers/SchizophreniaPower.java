package jesterMod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import jesterMod.actions.ChooseCardAction;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class SchizophreniaPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = jesterMod.makeID("Schizophrenia");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("jesterModResources/images/powers/Schizophrenia84.png");
    private static final Texture tex32 = TextureLoader.getTexture("jesterModResources/images/powers/Schizophrenia32.png");

    private static ArrayList< AbstractCard >redCards=new ArrayList();
    private static ArrayList< AbstractCard >greenCards=new ArrayList();
    private static ArrayList< AbstractCard >blueCards=new ArrayList();
    private static ArrayList< AbstractCard >purpleCards=new ArrayList();

    public SchizophreniaPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
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

        //Loading obtainable cards
        for (AbstractCard c :CardLibrary.getCardList(CardLibrary.LibraryType.RED)){
            if (!(c.hasTag(AbstractCard.CardTags.HEALING) || c.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))) redCards.add(c);
        }
        for (AbstractCard c :CardLibrary.getCardList(CardLibrary.LibraryType.GREEN)){
            if (!(c.hasTag(AbstractCard.CardTags.HEALING) || c.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))) greenCards.add(c);
        }
        for (AbstractCard c :CardLibrary.getCardList(CardLibrary.LibraryType.BLUE)){
            if (!(c.hasTag(AbstractCard.CardTags.HEALING) || c.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))) blueCards.add(c);
        }
        for (AbstractCard c :CardLibrary.getCardList(CardLibrary.LibraryType.PURPLE)){
            if (!(c.hasTag(AbstractCard.CardTags.HEALING) || c.hasTag(AbstractCard.CardTags.STARTER_DEFEND) || c.hasTag(AbstractCard.CardTags.STARTER_STRIKE))) purpleCards.add(c);
        }
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.flash();
        ArrayList< AbstractCard > cardOptions;
        for (int i=0;i<amount;i++){
            cardOptions=new ArrayList();
            AbstractCard redOption=redCards.get(cardRandomRng.random(redCards.size() - 1));
            AbstractCard greenOption=greenCards.get(cardRandomRng.random(greenCards.size() - 1));
            AbstractCard blueOption=blueCards.get(cardRandomRng.random(blueCards.size() - 1));
            AbstractCard purpleOption=purpleCards.get(cardRandomRng.random(purpleCards.size() - 1));
            cardOptions.add(redOption);
            cardOptions.add(greenOption);
            cardOptions.add(blueOption);
            cardOptions.add(purpleOption);
            this.addToBot(new ChooseCardAction(cardOptions, false, false, false));
        }
    }

    @Override
    public void updateDescription() {
        if (amount<2) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
        else{
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new SchizophreniaPower(owner, source, amount);
    }
}
