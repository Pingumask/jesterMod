package jesterMod.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import jesterMod.jesterMod;

import static jesterMod.jesterMod.makeEventPath;

public class IdentityCrisisEvent extends AbstractImageEvent {


    public static final String ID = jesterMod.makeID("IdentityCrisisEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("IdentityCrisisEvent.png");

    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;

    public IdentityCrisisEvent() {
        super(NAME, DESCRIPTIONS[0], IMG);
        // Get options for the non-current classes.
        imageEventText.setDialogOption(OPTIONS[0],AbstractDungeon.player.chosenClass==AbstractPlayer.PlayerClass.IRONCLAD,RelicLibrary.getRelic(jesterMod.makeID("IroncladEssence")));
        imageEventText.setDialogOption(OPTIONS[1],AbstractDungeon.player.chosenClass==AbstractPlayer.PlayerClass.THE_SILENT,RelicLibrary.getRelic(jesterMod.makeID("SilentEssence")));
        imageEventText.setDialogOption(OPTIONS[2],AbstractDungeon.player.chosenClass==AbstractPlayer.PlayerClass.DEFECT,RelicLibrary.getRelic(jesterMod.makeID("DefectEssence")));
        imageEventText.setDialogOption(OPTIONS[3],AbstractDungeon.player.chosenClass==AbstractPlayer.PlayerClass.WATCHER,RelicLibrary.getRelic(jesterMod.makeID("WatcherEssence")));
        imageEventText.setDialogOption(OPTIONS[4],RelicLibrary.getRelic("PrismaticShard"));

    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        AbstractRelic relicToAdd=RelicLibrary.getRelic("Circlet");
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0:
                        relicToAdd= RelicLibrary.getRelic(jesterMod.makeID("IroncladEssence")).makeCopy();
                        break;
                    case 1:
                        relicToAdd= RelicLibrary.getRelic(jesterMod.makeID("SilentEssence")).makeCopy();
                        break;
                    case 2:
                        relicToAdd= RelicLibrary.getRelic(jesterMod.makeID("DefectEssence")).makeCopy();
                        break;
                    case 3:
                        relicToAdd= RelicLibrary.getRelic(jesterMod.makeID("WatcherEssence")).makeCopy();
                        break;
                    case 4:
                        relicToAdd= RelicLibrary.getRelic("PrismaticShard").makeCopy();
                        break;
                }
                this.imageEventText.updateBodyText(DESCRIPTIONS[1]); // Update the text of the event
                this.imageEventText.updateDialogOption(0, OPTIONS[5]); // Change the first button to the [Leave] button
                this.imageEventText.clearRemainingOptions(); // Remove all other buttons
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), relicToAdd);
                screenNum = 1;
                break;
            case 1: // Welcome to screenNum = 1;
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }



}
