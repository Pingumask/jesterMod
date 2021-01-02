package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class ButterflyDance extends AbstractJesterCard {
    /*
     * Add X attacks, skills and powers to your draw pile. They cost 0 this battle. Exhaust.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(ButterflyDance.class.getSimpleName());
    public static final String IMG = makeCardPath("ButterflyDance.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = -1;
    // /STAT DECLARATION/


    public ButterflyDance() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect=EnergyPanel.totalCount;
        if (p.hasRelic("Chemical X")){
            effect+=2;
        }
        CardType[] types= {CardType.ATTACK, CardType.SKILL, CardType.POWER};
        for (CardType ct : types) {
            for(int i = 0; i < effect; ++i) {
                AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(ct).makeCopy();
                if (card.cost > 0) {
                    card.cost = 0;
                    card.costForTurn = 0;
                    card.isCostModified = true;
                }
                this.addToBot(new MakeTempCardInDrawPileAction(card, 1, true, true));
            }
        }
        p.energy.use(EnergyPanel.totalCount);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            isInnate=true;
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
