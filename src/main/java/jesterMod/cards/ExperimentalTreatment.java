package jesterMod.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class ExperimentalTreatment extends AbstractJesterCard {
    /*
     * Remove Vulnerable Weak and frail. Win as many HP.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(ExperimentalTreatment.class.getSimpleName());
    public static final String IMG = makeCardPath("ExperimentalTreatment.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    // /STAT DECLARATION/

    public ExperimentalTreatment() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
        GraveField.grave.set(this, true);
        tags.add(CardTags.HEALING);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        magicNumber=0;
        for (AbstractPower pow : AbstractDungeon.player.powers){
            if (pow.getClass()== WeakPower.class || pow.getClass()== FrailPower.class || pow.getClass()== VulnerablePower.class){
                magicNumber+=pow.amount;
                addToBot(new RemoveSpecificPowerAction(p,p,pow));
            }
        }
        this.addToBot(new HealAction(p, p, this.magicNumber));
    }
    /*
    @Override
    public void triggerWhenDrawn() {calculateDamageDisplay((AbstractMonster)null);}

    @Override
    public boolean isHoveredInHand(float scale) {
        this.calculateCardDamage();
        return super.isHoveredInHand(scale);
    }

    @Override
    public void calculateDamageDisplay(AbstractMonster mo) {
        super.calculateDamageDisplay(mo);
        this.calculateCardDamage();
    }

    public void calculateCardDamage() {
        magicNumber=0;
        for (AbstractPower pow : AbstractDungeon.player.powers){
            if (pow.getClass()== WeakPower.class || pow.getClass()== FrailPower.class || pow.getClass()== VulnerablePower.class){
                magicNumber+=pow.amount;
            }
        }
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }
    */

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
