package jesterMod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import static jesterMod.jesterMod.makeCardPath;

public class Taunt extends AbstractJesterCard {
    /*
     * Apply 1 Vulnerable to yourself and 3 Vulnerable to all enemies
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Taunt.class.getSimpleName());
    public static final String IMG = makeCardPath("Taunt.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int VULNE = 3;
    // /STAT DECLARATION/

    public Taunt() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = VULNE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster: AbstractDungeon.getMonsters().monsters){
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, p, new VulnerablePower(monster, magicNumber, false), magicNumber));
                }
            }
        }
        this.addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false), 1));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}