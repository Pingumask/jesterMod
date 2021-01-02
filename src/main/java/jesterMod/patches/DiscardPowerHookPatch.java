package jesterMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import jesterMod.powers.DiscardPowerHook;



public class DiscardPowerHookPatch {// Don't worry about the "never used" warning - *You* usually don't use/call them anywhere. Mod The Spire does.

    @SpirePatch(
            clz = GameActionManager.class, // This is the class where the method we will be patching is.
            method = "incrementDiscard" // This is the name of the method we will be patching.
    )
    public static class GameActionManagerPatch{
        @SpireInsertPatch(locator=Locator.class)
        public static void Insert(boolean endOfTurn) {
            for (AbstractPower pow:AbstractDungeon.player.powers){
                if (pow instanceof DiscardPowerHook){
                    ((DiscardPowerHook) pow).onManualDiscard();
                }
            }
            for (AbstractCreature mon:AbstractDungeon.getCurrRoom().monsters.monsters){
                for (AbstractPower pow:mon.powers){
                    if (pow instanceof DiscardPowerHook){
                        ((DiscardPowerHook) pow).onManualDiscard();
                    }
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