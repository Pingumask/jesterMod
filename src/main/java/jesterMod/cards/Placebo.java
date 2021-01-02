package jesterMod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Placebo extends AbstractJesterCard {
    /*
     * Gain 1 artifact
     * Elusive, Grave (no grave)
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Placebo.class.getSimpleName());
    public static final String IMG = makeCardPath("Placebo.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;
    private static final int COST = 0;
    // /STAT DECLARATION/

    public Placebo() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        GraveField.grave.set(this, true);
        isElusive=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, 1), 1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isElusive=false;
            this.rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}