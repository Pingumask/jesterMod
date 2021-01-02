package jesterMod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.localization.CardStrings;
import jesterMod.actions.BounceAction;
import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public abstract class AbstractJesterCard extends CustomCard {
    public int defaultSecondMagicNumber;
    public int defaultBaseSecondMagicNumber;
    public boolean upgradedDefaultSecondMagicNumber;
    public boolean isDefaultSecondMagicNumberModified;
    public boolean isElusive;
    public boolean isBouncing;
    public final CardStrings cardStrings;

    public AbstractJesterCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {
        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);
        this.cardStrings = languagePack.getCardStrings(id);
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        isDefaultSecondMagicNumberModified = false;
        isElusive = false;
        isBouncing = false;
    }

    public void displayUpgrades() { // Display the upgrade - when you click a card to upgrade it
        super.displayUpgrades();
        if (upgradedDefaultSecondMagicNumber) { // If we set upgradedDefaultSecondMagicNumber = true in our card.
            defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Show how the number changes, as out of combat, the base number of a card is shown.
            isDefaultSecondMagicNumberModified = true; // Modified = true, color it green to highlight that the number is being changed.
        }

    }

    public void upgradeDefaultSecondMagicNumber(int amount) { // If we're upgrading (read: changing) the number. Note "upgrade" and NOT "upgraded" - 2 different things. One is a boolean, and then this one is what you will usually use - change the integer by how much you want to upgrade.
        defaultBaseSecondMagicNumber += amount; // Upgrade the number by the amount you provide in your card.
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber; // Set the number to be equal to the base value.
        upgradedDefaultSecondMagicNumber = true; // Upgraded = true - which does what the above method does.
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        if (isElusive){
            if (cardRandomRng.randomBoolean(0.5f)){
                addToBot(new DiscardSpecificCardAction(this));
                addToBot(new DrawCardAction(1));
            }
        }
    }

    @Override
    public void triggerOnManualDiscard() {
        if (this.isBouncing){
            addToBot(new BounceAction(this));
        }
    }
}