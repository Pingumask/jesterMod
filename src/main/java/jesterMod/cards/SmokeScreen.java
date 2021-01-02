package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import java.util.Iterator;
import static jesterMod.jesterMod.makeCardPath;

public class SmokeScreen extends AbstractJesterCard {
    /*
     * Apply 1 weak to yourself and 2(3) weak to all enemies
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(SmokeScreen.class.getSimpleName());
    public static final String IMG = makeCardPath("SmokeScreen.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int WEAK = 2;
    private static final int UPGRADE_WEAK = 1;
    // /STAT DECLARATION/

    public SmokeScreen() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = WEAK;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            Iterator var3 = AbstractDungeon.getMonsters().monsters.iterator();

            while(var3.hasNext()) {
                AbstractMonster monster = (AbstractMonster)var3.next();
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, magicNumber, false), magicNumber));
                }
            }
        }
        this.addToBot(new ApplyPowerAction(p, p, new WeakPower(p, 1, false), 1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_WEAK);
            initializeDescription();
        }
    }
}