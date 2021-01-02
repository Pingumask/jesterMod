package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.actions.JesterRecycleAction;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class DesperateStrike extends AbstractJesterCard {
    /*
     * Deal 18(22) damage. Add a Hopeless to your hand.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(DesperateStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("DesperateStrike.png");
    // /TEXT DECLARATION/


    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 18;
    private static final int UPGRADE_PLUS_DMG = 4;
    // /STAT DECLARATION/

    public DesperateStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.STRIKE);
        damage = baseDamage = DAMAGE;
        this.cardsToPreview = new Hopeless();
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.POISON));
        addToBot(new MakeTempCardInHandAction( new Hopeless(), 1 ));
        addToBot(new JesterRecycleAction(1));
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
