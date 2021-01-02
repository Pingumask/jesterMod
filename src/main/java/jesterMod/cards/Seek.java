package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Seek extends AbstractJesterCard {
    /*
     * Deal 8(11) damage. Add a hide to your hand. Exhaust.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Seek.class.getSimpleName());
    public static final String IMG = makeCardPath("Seek.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;
    // /STAT DECLARATION/

    public Seek() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.purgeOnUse=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractCard toAdd= new Hide();
        if (upgraded){
            toAdd.upgrade();
        }
        this.addToBot(new MakeTempCardInDiscardAction( toAdd,1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
