package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Talion extends AbstractJesterCard {
    /*
     * Deal 5(8) damage. Deal 2(3) additional damage per health loss this battle.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Talion.class.getSimpleName());
    public static final String IMG = makeCardPath("Talion.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int GROWTH = 3;
    private static final int UPGRADE_PLUS_GROWTH = 1;
    // /STAT DECLARATION/

    public Talion() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = GROWTH;
        magicNumber= baseMagicNumber = 0;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        baseMagicNumber = AbstractDungeon.player.damagedThisCombat;
        magicNumber=baseMagicNumber;
        for(int i = 0; i < this.magicNumber; ++i) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public void tookDamage() {
        baseMagicNumber = AbstractDungeon.player.damagedThisCombat;
        calculateCardDamage((AbstractMonster)null);
    }

    @Override
    public void triggerWhenDrawn() {
        calculateCardDamage((AbstractMonster)null);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        magicNumber = baseMagicNumber = AbstractDungeon.player.damagedThisCombat;
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_GROWTH);
            initializeDescription();
        }
    }
}
