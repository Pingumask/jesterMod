package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class JugglerBlade extends AbstractJesterCard {
    /*
     * Deal 4(5) damage to a random enemy for each card discarded this turn.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(JugglerBlade.class.getSimpleName());
    public static final String IMG = makeCardPath("JugglerBlade.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DMG = 1;
    // /STAT DECLARATION/

    public JugglerBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i=0;i<GameActionManager.totalDiscardedThisTurn;i++) {
            this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (GameActionManager.totalDiscardedThisTurn > 0) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        calculateDamageDisplay((AbstractMonster)null);}

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
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
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
