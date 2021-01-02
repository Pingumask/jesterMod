package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.actions.JesterRecycleAction;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Sickle extends AbstractJesterCard {
    /*
     * Deal 3 damage, cycle 1(2).
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Sickle.class.getSimpleName());
    public static final String IMG = makeCardPath("Sickle.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 0;
    private static final int DAMAGE = 3;
    private static final int RECYCLE = 1;
    private static final int UPGRADE_RECYCLE = 1;
    // /STAT DECLARATION/


    public Sickle() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber= baseMagicNumber = RECYCLE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new JesterRecycleAction(magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_RECYCLE);
            initializeDescription();
        }
    }
}