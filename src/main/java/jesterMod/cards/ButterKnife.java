package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class ButterKnife extends AbstractJesterCard {
    /*
     * Deal 9(12) damage. Elusive.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(ButterKnife.class.getSimpleName());
    public static final String IMG = makeCardPath("ButterKnife.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 9;
    private static final int UPGRADE_PLUS_DMG = 3;
    // /STAT DECLARATION/

    public ButterKnife() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        isElusive = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
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
