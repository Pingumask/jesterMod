package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class JugglingBall extends AbstractJesterCard {
    /*
     * Unplayable. Deal 5(7) Damage to a random enemy when exhausted. Ethereal.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(JugglingBall.class.getSimpleName());
    public static final String IMG = makeCardPath("JugglingBall.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = -2;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DAMAGE = 2;
    // /STAT DECLARATION/

    public JugglingBall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE;
        isEthereal=true;
        isBouncing=true;
    }

    @Override
    public void triggerOnExhaust() {
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(null, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void triggerOnManualDiscard() {
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(null, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        super.triggerOnManualDiscard();
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