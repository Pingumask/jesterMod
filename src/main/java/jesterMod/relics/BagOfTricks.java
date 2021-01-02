package jesterMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import jesterMod.cards.Prank;
import jesterMod.jesterMod;
import jesterMod.util.TextureLoader;

import static jesterMod.jesterMod.makeRelicOutlinePath;
import static jesterMod.jesterMod.makeRelicPath;

public class BagOfTricks extends CustomRelic{
    /*
     * At the start of battle, shuffle a prank to your hand, discard pile and draw pile.
     */

    // ID, images, text.
    public static final String ID = jesterMod.makeID("BagOfTricks");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BagOfTricks.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BagOfTricks.png"));

    public BagOfTricks() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        this.addToBot(new MakeTempCardInHandAction(new Prank(), 1));
        this.addToBot(new MakeTempCardInDrawPileAction(new Prank(), 1, true, true));
        this.addToBot(new MakeTempCardInDiscardAction(new Prank(), 1));
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
