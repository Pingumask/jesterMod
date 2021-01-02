package jesterMod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;

public interface SpecificDiscardRelicHook {
    void onSpecificDiscard(AbstractCard discardedCard);
}
