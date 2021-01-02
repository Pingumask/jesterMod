package jesterMod.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.StartActSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import java.util.ArrayList;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;


public class IroncladEssence extends CustomRelic implements StartActSubscriber {

    /*
     * Add red cards to the pool
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("IroncladEssence");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("UnknownRelic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("UnknownRelic.png"));

    public IroncladEssence() {
        super(ID, IMG, OUTLINE, RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() {
        BaseMod.subscribe(this);
        addToPool();
    }

    @Override
    public void receiveStartAct(){
        addToPool();
    }

    public void addToPool(){
        ArrayList<AbstractCard> tmpPool = CardLibrary.getCardList(CardLibrary.LibraryType.RED);
        for(AbstractCard tmpCard : tmpPool){
            switch (tmpCard.rarity){
                case COMMON:
                    AbstractDungeon.commonCardPool.group.add(tmpCard);
                    break;
                case UNCOMMON:
                    AbstractDungeon.uncommonCardPool.group.add(tmpCard);
                    break;
                case RARE:
                    AbstractDungeon.rareCardPool.group.add(tmpCard);
                    break;
            }
        }
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
