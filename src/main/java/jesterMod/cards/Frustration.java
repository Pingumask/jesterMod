package jesterMod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Frustration extends AbstractJesterCard {
    /*
     * Deals 30(40) damage. Only playable if you are frail, weak or vulnerable.
     */

    // TEXT DECLARATION

    public static final String ID = jesterMod.makeID(Frustration.class.getSimpleName());
    public static final String IMG = makeCardPath("Frustration.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 3;
    private static final int DAMAGE = 30;
    private static final int UPGRADE_PLUS_DMG = 10;

    // /STAT DECLARATION/


    public Frustration() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        isMultiDamage= true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
                this.addToBot(new DamageAllEnemiesAction(p,damage,damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower("Frail") || p.hasPower("Weakened") || p.hasPower("Vulnerable")){
            return true;
        }
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void triggerOnGlowCheck() {
        if ((AbstractDungeon.player.hasPower("Frail") || AbstractDungeon.player.hasPower("Weakened") || AbstractDungeon.player.hasPower("Vulnerable"))) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}