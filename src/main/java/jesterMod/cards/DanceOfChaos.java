package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class DanceOfChaos extends AbstractJesterCard {
    /*
     * Play the top X cards of your draw pile. Exhaust (no Exhaust).
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(DanceOfChaos.class.getSimpleName());
    public static final String IMG = makeCardPath("DanceOfChaos.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = -1;
    // /STAT DECLARATION/


    public DanceOfChaos() {
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
        for (int i=0;i<effect;++i){
            this.addToBot(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), false));
        }
        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust=false;
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
