package jesterMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import jesterMod.powers.ShufflePowerHook;


public class ShufflePowerHookPatch {// Don't worry about the "never used" warning - *You* usually don't use/call them anywhere. Mod The Spire does.
    @SpirePatch(
            clz = EmptyDeckShuffleAction.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class EmptyDeckShuffleActionPatch{
        @SpireInsertPatch(locator=Locator.class)
        public static void Insert() {
            for (AbstractPower pow:AbstractDungeon.player.powers){
                if (pow instanceof ShufflePowerHook){
                    ((ShufflePowerHook) pow).onShuffle();
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }



}