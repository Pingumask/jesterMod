package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Feint extends AbstractJesterCard {
    /*
     * Deal 7(10) damage. Draw a card for each card discarded this turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Feint.class.getSimpleName());
    public static final String IMG = makeCardPath("Feint.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;
    // /STAT DECLARATION/

    public Feint() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (GameActionManager.totalDiscardedThisTurn > 0){
            this.addToBot(new DrawCardAction(GameActionManager.totalDiscardedThisTurn));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (GameActionManager.totalDiscardedThisTurn > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

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
        magicNumber = baseMagicNumber = GameActionManager.totalDiscardedThisTurn;
        rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
