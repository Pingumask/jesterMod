package jesterMod.cards;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jesterMod.characters.TheJester;
import jesterMod.jesterMod;
import jesterMod.powers.DisposophobePower;
import static jesterMod.jesterMod.makeCardPath;

public class Disposophobia extends AbstractJesterCard {
    /*
     * Deal 3(4) damage any time you discard a card.
     */

    // TEXT DECLARATION
    public static final String ID = jesterMod.makeID(Disposophobia.class.getSimpleName());
    public static final String IMG = makeCardPath("Disposophobia.png");
    // /TEXT DECLARATION/

    // STAT DECLARATION
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheJester.Enums.JESTER_ORANGE;

    private static final int COST = 1;
    private static final int DAMAGE = 3;
    // /STAT DECLARATION/

    public Disposophobia() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DAMAGE;
        this.exhaust=true;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            this.addToBot(new ApplyPowerAction(m,p,new DisposophobePower(m,p,magicNumber),magicNumber));
        } else {
            for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters){
                this.addToBot(new ApplyPowerAction(mon,p,new DisposophobePower(mon,p,magicNumber),magicNumber));
            }
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription=cardStrings.UPGRADE_DESCRIPTION;
            this.target = CardTarget.ALL_ENEMY;
            initializeDescription();
        }
    }
}