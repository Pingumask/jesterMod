package jesterMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;
import jesterMod.relics.SpecificDiscardRelicHook;


public class SpecificDiscardRelicHookPatch {// Don't worry about the "never used" warning - *You* usually don't use/call them anywhere. Mod The Spire does.

    @SpirePatch(
            clz = DiscardSpecificCardAction.class, // This is the class where the method we will be patching is.
            method = "update"// This is the name of the method we will be patching.

    )
    public static class DiscardSpecificCardActionPatch{
        @SpireInsertPatch(locator=Locator.class,localvars ={"targetCard"})
        public static void Insert(AbstractCard targetCard) {
            for (AbstractRelic relic:AbstractDungeon.player.relics){
                if (relic instanceof SpecificDiscardRelicHook){
                    ((SpecificDiscardRelicHook) relic).onSpecificDiscard(targetCard);
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class,"triggerOnManualDiscard");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = DiscardAction.class, // This is the class where the method we will be patching is.
            method = "update"// This is the name of the method we will be patching.

    )
    public static class DiscardActionPatch{
        @SpireInsertPatch(locator=Locator.class,localvars ={"c"})
        public static void Insert(AbstractCard targetCard) {
            for (AbstractRelic relic:AbstractDungeon.player.relics){
                if (relic instanceof SpecificDiscardRelicHook){
                    ((SpecificDiscardRelicHook) relic).onSpecificDiscard(targetCard);
                }
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class,"triggerOnManualDiscard");
                return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }


}