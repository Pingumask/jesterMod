package jesterMod.cards;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.actions.BoilAction;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class BoilDown extends AbstractJesterCard {
    /*
     * Deal 18(22) Damage. If this kills a non-minion Enemy gain a potion.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(BoilDown.class.getSimpleName());
    public static final String IMG = makeCardPath("BoilDown.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 2;
    private static final int DAMAGE = 18;
    private static final int UPGRADE_DAMAGE = 4;
    // /STAT DECLARATION/

    public BoilDown() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage= baseDamage = DAMAGE;
        tags.add(CardTags.HEALING);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new BoilAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}