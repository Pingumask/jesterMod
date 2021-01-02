package jesterMod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class LaughingGas extends AbstractJesterCard {
    /*
     * One (All) Enemy lose 1 Strength. Elusive, Buried.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(LaughingGas.class.getSimpleName());
    public static final String IMG = makeCardPath("LaughingGas.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DEBUFF = -1;
    // /STAT DECLARATION/

    public LaughingGas() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEBUFF;
        this.exhaust=false;
        this.isElusive = true;
        GraveField.grave.set(this, true);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.addToBot(new ApplyPowerAction(m,p,new StrengthPower(m,magicNumber),magicNumber));
        } else {
            for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters){
                this.addToBot(new ApplyPowerAction(mon,p,new StrengthPower(mon,magicNumber),magicNumber));
            }
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.target = CardTarget.ALL_ENEMY;
            initializeDescription();
        }
    }
}