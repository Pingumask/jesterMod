package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Detonate extends AbstractJesterCard {
    /*
     * Unplayable. Deal 5(7) Damage to all enemies when drawn. Ethereal.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Detonate.class.getSimpleName());
    public static final String IMG = makeCardPath("Detonate.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DAMAGE = 4;
    // /STAT DECLARATION/

    public Detonate() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE;
        isEthereal=true;
        this.isMultiDamage=true;
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        this.addToBot(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_DAMAGE);
            this.initializeDescription();
        }
    }
}