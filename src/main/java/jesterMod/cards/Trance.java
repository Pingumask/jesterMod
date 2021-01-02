package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Trance extends AbstractJesterCard {
    /*
     * Deal 12(16) Damage to a random enemy. Discard the top card of your draw pile, if this is a skill card, Transe is played again.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Trance.class.getSimpleName());
    public static final String IMG = makeCardPath("Trance.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 4;
    // /STAT DECLARATION/

    public Trance() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (AbstractDungeon.player.drawPile.size()>0) {
            AbstractCard thrown = AbstractDungeon.player.drawPile.getTopCard();
            addToBot(new DiscardSpecificCardAction(thrown, AbstractDungeon.player.drawPile));
            if (thrown.type == CardType.SKILL && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                GameActionManager.queueExtraCard(this.makeCopy(), (AbstractMonster) null);
            }
        }
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
