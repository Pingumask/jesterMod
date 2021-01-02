package jesterMod;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.Tingsha;
import com.megacrit.cardcrawl.relics.ToughBandages;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import jesterMod.cards.*;
import jesterMod.characters.TheJester;
import jesterMod.relics.*;
import jesterMod.util.IDCheckDontTouchPls;
import jesterMod.util.TextureLoader;
import jesterMod.variables.DefaultCustomVariable;
import jesterMod.variables.SecondMagicNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
//TODO: DON'T MASS RENAME/REFACTOR
// Please don't just mass replace "theDefault" with "yourMod" everywhere.
// It'll be a bigger pain for you. You only need to replace it in 3 places.
// I comment those places below, under the place where you set your ID.

//TODO: FIRST THINGS FIRST: RENAME YOUR PACKAGE AND ID NAMES FIRST-THING!!!
// Right click the package (Open the project pane on the left. Folder with black dot on it. The name's at the very top) -> Refactor -> Rename, and name it whatever you wanna call your mod.
// Scroll down in this file. Change the ID from "theDefault:" to "yourModName:" or whatever your heart desires (don't use spaces). Dw, you'll see it.
// In the JSON strings (resources>localization>eng>[all them files] make sure they all go "yourModName:" rather than "theDefault". You can ctrl+R to replace in 1 file, or ctrl+shift+r to mass replace in specific files/directories (Be careful.).
// Start with the DefaultCommon cards - they are the most commented cards since I don't feel it's necessary to put identical comments on every card.
// After you sorta get the hang of how to make cards, check out the card template which will make your life easier

/*
 * With that out of the way:
 * Welcome to this super over-commented Slay the Spire modding base.
 * Use it to make your own mod of any type. - If you want to add any standard in-game content (character,
 * cards, relics), this is a good starting point.
 * It features 1 character with a minimal set of things: 1 card of each type, 1 debuff, couple of relics, etc.
 * If you're new to modding, you basically *need* the BaseMod wiki for whatever you wish to add
 * https://github.com/daviscook477/BaseMod/wiki - work your way through with this base.
 * Feel free to use this in any way you like, of course. MIT licence applies. Happy modding!
 *
 * And pls. Read the comments.
 */

@SpireInitializer
public class jesterMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber{
    // Make sure to implement the subscribers *you* are using (read basemod wiki). Editing cards? EditCardsSubscriber.
    // Making relics? EditRelicsSubscriber. etc., etc., for a full list and how to make your own, visit the basemod wiki.
    public static final Logger logger = LogManager.getLogger(jesterMod.class.getName());
    private static String modID;

    // Mod-settings settings. This is if you want an on/off savable button
    public static Properties jesterModDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enablePlaceholder";
    public static boolean enablePlaceholder = true; // The boolean we'll be setting on/off (true/false)

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Jester Mod";
    private static final String AUTHOR = "Pingumask";
    private static final String DESCRIPTION = "Adds the character 'The Jester' by Pingumask.";
    
    // =============== INPUT TEXTURE LOCATION =================
    
    // Colors (RGB)
    // Character Color
    public static final Color JESTER_ORANGE = CardHelper.getColor(255.0f, 136.0f, 77.0f);

    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
    // ONCE YOU CHANGE YOUR MOD ID (BELOW, YOU CAN'T MISS IT) CHANGE THESE PATHS!!!!!!!!!!!
  
    // Card backgrounds - The actual rectangular card.
    private static final String ATTACK_BG_PATH = "jesterModResources/images/512/attack.png";
    private static final String SKILL_BG_PATH = "jesterModResources/images/512/skill.png";
    private static final String POWER_BG_PATH = "jesterModResources/images/512/power.png";
    
    private static final String ENERGY_ORB_BG_PATH = "jesterModResources/images/512/card_orb.png";
    private static final String SMALL_ENERGY_ORB_PATH = "jesterModResources/images/512/card_small_orb.png";
    
    private static final String ATTACK_PORTRAIT_PATH = "jesterModResources/images/1024/attack.png";
    private static final String SKILL_PORTRAIT_PATH = "jesterModResources/images/1024/skill.png";
    private static final String POWER_PORTRAIT_PATH = "jesterModResources/images/1024/power.png";
    private static final String ENERGY_ORB_PORTRAIT_PATH = "jesterModResources/images/1024/orb.png";
    
    // Character assets
    private static final String THE_JESTER_BUTTON = "jesterModResources/images/charSelect/CharacterButton.png";
    private static final String THE_JESTER_PORTRAIT = "jesterModResources/images/charSelect/CharacterPortraitBG.png";
    public static final String THE_JESTER_DEFAULT_POSE = "jesterModResources/images/char/jesterCharacter/default_pose.png";
    public static final String THE_JESTER_SHOULDER_1 = "jesterModResources/images/char/jesterCharacter/shoulder.png";
    public static final String THE_JESTER_SHOULDER_2 = "jesterModResources/images/char/jesterCharacter/shoulder2.png";
    public static final String THE_JESTER_CORPSE = "jesterModResources/images/char/jesterCharacter/corpse.png";
    
    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "jesterModResources/images/Badge.png";
    
    // Atlas and JSON files for the Animations
    public static final String THE_JESTER_SKELETON_ATLAS = "jesterModResources/images/char/jesterCharacter/Jester.atlas";
    public static final String THE_JESTER_SKELETON_JSON = "jesterModResources/images/char/jesterCharacter/Jester.json";
    
    // =============== MAKE IMAGE PATHS =================
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    // =============== /MAKE IMAGE PATHS/ =================
    
    // =============== /INPUT TEXTURE LOCATION/ =================
    
    
    // =============== SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE =================
    
    public jesterMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
        
      /*
           (   ( /(  (     ( /( (            (  `   ( /( )\ )    )\ ))\ )
           )\  )\()) )\    )\()))\ )   (     )\))(  )\()|()/(   (()/(()/(
         (((_)((_)((((_)( ((_)\(()/(   )\   ((_)()\((_)\ /(_))   /(_))(_))
         )\___ _((_)\ _ )\ _((_)/(_))_((_)  (_()((_) ((_|_))_  _(_))(_))_
        ((/ __| || (_)_\(_) \| |/ __| __| |  \/  |/ _ \|   \  |_ _||   (_)
         | (__| __ |/ _ \ | .` | (_ | _|  | |\/| | (_) | |) |  | | | |) |
          \___|_||_/_/ \_\|_|\_|\___|___| |_|  |_|\___/|___/  |___||___(_)
      */
      
        setModID("jesterMod");
        // cool
        // TODO: NOW READ THIS!!!!!!!!!!!!!!!:
        
        // 1. Go to your resources folder in the project panel, and refactor> rename theDefaultResources to
        // yourModIDResources.
        
        // 2. Click on the localization > eng folder and press ctrl+shift+r, then select "Directory" (rather than in Project)
        // replace all instances of theDefault with yourModID.
        // Because your mod ID isn't the default. Your cards (and everything else) should have Your mod id. Not mine.
        
        // 3. FINALLY and most importantly: Scroll up a bit. You may have noticed the image locations above don't use getModID()
        // Change their locations to reflect your actual ID rather than theDefault. They get loaded before getID is a thing.
        
        logger.info("Done subscribing");
        
        logger.info("Creating the color " + TheJester.Enums.JESTER_ORANGE.toString());
        
        BaseMod.addColor(TheJester.Enums.JESTER_ORANGE, JESTER_ORANGE, JESTER_ORANGE, JESTER_ORANGE,
                JESTER_ORANGE, JESTER_ORANGE, JESTER_ORANGE, JESTER_ORANGE,
                ATTACK_BG_PATH, SKILL_BG_PATH, POWER_BG_PATH, ENERGY_ORB_BG_PATH,
                ATTACK_PORTRAIT_PATH, SKILL_PORTRAIT_PATH, POWER_PORTRAIT_PATH,
                ENERGY_ORB_PORTRAIT_PATH, SMALL_ENERGY_ORB_PATH);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");
        // This loads the mod settings.
        // The actual mod Button is added below in receivePostInitialize()
        jesterModDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE"); // This is the default setting. It's actually set...
        try {
            SpireConfig config = new SpireConfig("jesterMod", "jesterModConfig", jesterModDefaultSettings); // ...right here
            // the "fileName" parameter is the name of the file MTS will create where it will save our setting.
            config.load(); // Load the setting and set the boolean to equal it
            enablePlaceholder = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = jesterMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = jesterMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = jesterMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    
    
    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Jester Mod. Hi. =========================");
        jesterMod jestermod = new jesterMod();
        logger.info("========================= /Jester Mod Initialized. =========================");
    }
    
    // ============== /SUBSCRIBE, CREATE THE COLOR_GRAY, INITIALIZE/ =================
    
    
    // =============== LOAD THE CHARACTER =================
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + TheJester.Enums.THE_JESTER.toString());
        
        BaseMod.addCharacter(new TheJester("the Jester", TheJester.Enums.THE_JESTER),
                THE_JESTER_BUTTON, THE_JESTER_PORTRAIT, TheJester.Enums.THE_JESTER);
        
        receiveEditPotions();
        logger.info("Added " + TheJester.Enums.THE_JESTER.toString());
    }
    
    // =============== /LOAD THE CHARACTER/ =================
    
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        
        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();
        
        // Create the on/off button:
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("This is the text which goes next to the checkbox.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont, // Position (trial and error it), color, font
                enablePlaceholder, // Boolean it uses
                settingsPanel, // The mod panel in which this button will be in
                (label) -> {}, // thing??????? idk
                (button) -> { // The actual button:
            
            enablePlaceholder = button.enabled; // The boolean true/false will be whether the button is enabled or not
            try {
                // And based on that boolean, set the settings and save them
                SpireConfig config = new SpireConfig("jestermod", "jesterModConfig", jesterModDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enablePlaceholder);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton); // Add the button to the settings panel. Button is a go.
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        
        // =============== EVENTS =================
        
        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"

        //BaseMod.addEvent(IdentityCrisisEvent.ID, IdentityCrisisEvent.class);
        
        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================
    
    
    // ================ ADD POTIONS ===================
    
    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        
        // Class Specific Potion. If you want your potion to not be class-specific,
        // just remove the player class at the end (in this case the "TheDefaultEnum.THE_DEFAULT".
        // Remember, you can press ctrl+P inside parentheses like addPotions)

        //BaseMod.addPotion(PlaceholderPotion.class, PLACEHOLDER_POTION_LIQUID, PLACEHOLDER_POTION_HYBRID, PLACEHOLDER_POTION_SPOTS, PlaceholderPotion.POTION_ID, TheJester.Enums.THE_JESTER);
        
        logger.info("Done editing potions");
    }
    
    // ================ /ADD POTIONS/ ===================
    
    
    // ================ ADD RELICS ===================
    
    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        
        // This adds a character specific relic. Only when you play with the mentioned color, will you get this relic.
        BaseMod.addRelicToCustomPool(new Tingsha(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new ToughBandages(), TheJester.Enums.JESTER_ORANGE);

        BaseMod.addRelicToCustomPool(new JesterCap(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new JesterMask(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new JokerCard(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new HauntedLute(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new LoadedDice(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new RuskiBroom(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new PhilosopherAmphora(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new HarlequinCap(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new LargeSleeves(), TheJester.Enums.JESTER_ORANGE);
        BaseMod.addRelicToCustomPool(new BagOfTricks(), TheJester.Enums.JESTER_ORANGE);

        // This adds a relic to the Shared pool. Every character can find this relic.
        //BaseMod.addRelic(new BottledPlaceholderRelic(), RelicType.SHARED);
        BaseMod.addRelic(new IroncladEssence(), RelicType.SHARED);
        BaseMod.addRelic(new SilentEssence(), RelicType.SHARED);
        BaseMod.addRelic(new DefectEssence(), RelicType.SHARED);
        BaseMod.addRelic(new WatcherEssence(), RelicType.SHARED);
        BaseMod.addRelic(new MirrorMask(), RelicType.SHARED);
        BaseMod.addRelic(new MachoBrace(), RelicType.SHARED);

        // Mark relics as seen (the others are all starters so they're marked as seen in the character file
        UnlockTracker.markRelicAsSeen(IroncladEssence.ID);
        UnlockTracker.markRelicAsSeen(SilentEssence.ID);
        UnlockTracker.markRelicAsSeen(DefectEssence.ID);
        UnlockTracker.markRelicAsSeen(WatcherEssence.ID);
        UnlockTracker.markRelicAsSeen(JesterMask.ID);
        UnlockTracker.markRelicAsSeen(JokerCard.ID);
        UnlockTracker.markRelicAsSeen(HauntedLute.ID);
        UnlockTracker.markRelicAsSeen(LoadedDice.ID);
        UnlockTracker.markRelicAsSeen(MirrorMask.ID);
        UnlockTracker.markRelicAsSeen(RuskiBroom.ID);
        UnlockTracker.markRelicAsSeen(PhilosopherAmphora.ID);
        UnlockTracker.markRelicAsSeen(HarlequinCap.ID);
        UnlockTracker.markRelicAsSeen(LargeSleeves.ID);
        UnlockTracker.markRelicAsSeen(MachoBrace.ID);
        UnlockTracker.markRelicAsSeen(BagOfTricks.ID);
        logger.info("Done adding relics!");
    }
    // ================ /ADD RELICS/ ===================

    
    // ================ ADD CARDS ===================
    
    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        //Ignore this
        pathCheck();
        // Add the Custom Dynamic Variables
        logger.info("Add variabls");
        // Add the Custom Dynamic variabls
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        
        logger.info("Adding cards");
        // Add the cards

        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Dodge());
        BaseMod.addCard(new Sickle());
        BaseMod.addCard(new DancingMad());
        BaseMod.addCard(new OblivionStrike());
        BaseMod.addCard(new Frenzy());
        BaseMod.addCard(new DaggerRain());
        BaseMod.addCard(new Overthink());
        BaseMod.addCard(new Swiftness());
        BaseMod.addCard(new RecklessStrike());
        BaseMod.addCard(new FlameDance());
        BaseMod.addCard(new Contradiction());
        BaseMod.addCard(new SmokeScreen());
        BaseMod.addCard(new TrickyDodge());
        BaseMod.addCard(new Leeches());
        BaseMod.addCard(new BoilDown());
        BaseMod.addCard(new Tinker());
        BaseMod.addCard(new SwiftDodge());
        BaseMod.addCard(new Discretion());
        BaseMod.addCard(new Prankster());
        BaseMod.addCard(new Prank());
        BaseMod.addCard(new Haste());
        BaseMod.addCard(new Flee());
        BaseMod.addCard(new Unleash());
        BaseMod.addCard(new Dilapidation());
        BaseMod.addCard(new JackInTheBox());
        //BaseMod.addCard(new Sacrifice());
        BaseMod.addCard(new Drunkenness());
        BaseMod.addCard(new Momentum());
        BaseMod.addCard(new MudBall());
        BaseMod.addCard(new Patience());
        BaseMod.addCard(new Detonate());
        BaseMod.addCard(new LightTheWick());
        BaseMod.addCard(new Talion());
        BaseMod.addCard(new Frustration());
        BaseMod.addCard(new Loot());
        BaseMod.addCard(new Inspiration());
        BaseMod.addCard(new Hide());
        BaseMod.addCard(new Seek());
        BaseMod.addCard(new Twirl());
        BaseMod.addCard(new JugglerBlade());
        BaseMod.addCard(new Procrastination());
        BaseMod.addCard(new JugglingBall());
        BaseMod.addCard(new Juggle());
        BaseMod.addCard(new Anticipation());
        BaseMod.addCard(new Pause());
        BaseMod.addCard(new DanceOfChaos());
        BaseMod.addCard(new Illness());
        BaseMod.addCard(new ButterflyDance());
        BaseMod.addCard(new Feint());
        BaseMod.addCard(new Trance());
        BaseMod.addCard(new HitAndRun());
        BaseMod.addCard(new QuickStrike());
        BaseMod.addCard(new Juggler());
        BaseMod.addCard(new Precipitation());
        BaseMod.addCard(new Arrythmia());
        BaseMod.addCard(new Pillage());
        BaseMod.addCard(new Schizophrenia());
        BaseMod.addCard(new Disposophobia());
        BaseMod.addCard(new HedgehogForm());
        BaseMod.addCard(new Finale());
        BaseMod.addCard(new Placebo());
        BaseMod.addCard(new Squander());
        BaseMod.addCard(new Encore());
        BaseMod.addCard(new ButterKnife());
        BaseMod.addCard(new Taunt());
        BaseMod.addCard(new Exasperation());
        BaseMod.addCard(new Mending());
        BaseMod.addCard(new Hopeless());
        BaseMod.addCard(new DesperateStrike());
        BaseMod.addCard(new SelfMockery());
        BaseMod.addCard(new Rebuff());
        BaseMod.addCard(new ExperimentalTreatment());
        BaseMod.addCard(new BouncingKnife());
        BaseMod.addCard(new PerfectDodge());
        BaseMod.addCard(new LaughingGas());

        logger.info("Making sure the cards are unlocked.");
        // Unlock the cards
        UnlockTracker.unlockCard(Strike.ID);
        UnlockTracker.unlockCard(Defend.ID);
        UnlockTracker.unlockCard(Dodge.ID);
        UnlockTracker.unlockCard(Sickle.ID);
        UnlockTracker.unlockCard(OblivionStrike.ID);
        UnlockTracker.unlockCard(DancingMad.ID);
        UnlockTracker.unlockCard(Frenzy.ID);
        UnlockTracker.unlockCard(DaggerRain.ID);
        UnlockTracker.unlockCard(Overthink.ID);
        UnlockTracker.unlockCard(Swiftness.ID);
        UnlockTracker.unlockCard(RecklessStrike.ID);
        UnlockTracker.unlockCard(FlameDance.ID);
        UnlockTracker.unlockCard(Contradiction.ID);
        UnlockTracker.unlockCard(SmokeScreen.ID);
        UnlockTracker.unlockCard(TrickyDodge.ID);
        UnlockTracker.unlockCard(Leeches.ID);
        UnlockTracker.unlockCard(BoilDown.ID);
        UnlockTracker.unlockCard(Tinker.ID);
        UnlockTracker.unlockCard(SwiftDodge.ID);
        UnlockTracker.unlockCard(Discretion.ID);
        UnlockTracker.unlockCard(Prankster.ID);
        UnlockTracker.unlockCard(Prank.ID);
        UnlockTracker.unlockCard(Haste.ID);
        UnlockTracker.unlockCard(Flee.ID);
        UnlockTracker.unlockCard(Unleash.ID);
        UnlockTracker.unlockCard(Dilapidation.ID);
        UnlockTracker.unlockCard(JackInTheBox.ID);
        //UnlockTracker.unlockCard(Sacrifice.ID);
        UnlockTracker.unlockCard(Drunkenness.ID);
        UnlockTracker.unlockCard(Momentum.ID);
        UnlockTracker.unlockCard(MudBall.ID);
        UnlockTracker.unlockCard(Detonate.ID);
        UnlockTracker.unlockCard(LightTheWick.ID);
        UnlockTracker.unlockCard(Talion.ID);
        UnlockTracker.unlockCard(Frustration.ID);
        UnlockTracker.unlockCard(Loot.ID);
        UnlockTracker.unlockCard(Inspiration.ID);
        UnlockTracker.unlockCard(Hide.ID);
        UnlockTracker.unlockCard(Seek.ID);
        UnlockTracker.unlockCard(Twirl.ID);
        UnlockTracker.unlockCard(JugglerBlade.ID);
        UnlockTracker.unlockCard(Procrastination.ID);
        UnlockTracker.unlockCard(JugglingBall.ID);
        UnlockTracker.unlockCard(Juggle.ID);
        UnlockTracker.unlockCard(Anticipation.ID);
        UnlockTracker.unlockCard(Pause.ID);
        UnlockTracker.unlockCard(DanceOfChaos.ID);
        UnlockTracker.unlockCard(Illness.ID);
        UnlockTracker.unlockCard(ButterflyDance.ID);
        UnlockTracker.unlockCard(Feint.ID);
        UnlockTracker.unlockCard(Trance.ID);
        UnlockTracker.unlockCard(HitAndRun.ID);
        UnlockTracker.unlockCard(QuickStrike.ID);
        UnlockTracker.unlockCard(Juggler.ID);
        UnlockTracker.unlockCard(Precipitation.ID);
        UnlockTracker.unlockCard(Arrythmia.ID);
        UnlockTracker.unlockCard(Pillage.ID);
        UnlockTracker.unlockCard(Schizophrenia.ID);
        UnlockTracker.unlockCard(Disposophobia.ID);
        UnlockTracker.unlockCard(HedgehogForm.ID);
        UnlockTracker.unlockCard(Finale.ID);
        UnlockTracker.unlockCard(Placebo.ID);
        UnlockTracker.unlockCard(Squander.ID);
        UnlockTracker.unlockCard(Encore.ID);
        UnlockTracker.unlockCard(ButterKnife.ID);
        UnlockTracker.unlockCard(Taunt.ID);
        UnlockTracker.unlockCard(Exasperation.ID);
        UnlockTracker.unlockCard(Mending.ID);
        UnlockTracker.unlockCard(Hopeless.ID);
        UnlockTracker.unlockCard(DesperateStrike.ID);
        UnlockTracker.unlockCard(SelfMockery.ID);
        UnlockTracker.unlockCard(Rebuff.ID);
        UnlockTracker.unlockCard(ExperimentalTreatment.ID);
        UnlockTracker.unlockCard(BouncingKnife.ID);
        UnlockTracker.unlockCard(PerfectDodge.ID);
        UnlockTracker.unlockCard(LaughingGas.ID);

        logger.info("Done adding cards!");
    }
    
    // There are better ways to do this than listing every single individual card, but I do not want to complicate things
    // in a "tutorial" mod. This will do and it's completely ok to use. If you ever want to clean up and
    // shorten all the imports, go look take a look at other mods, such as Hubris.
    
    // ================ /ADD CARDS/ ===================
    
    
    // ================ LOAD THE TEXT ===================
    
    @Override
    public void receiveEditStrings() {
        //Loading Localization strings
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        String langToLoad ="eng";
        ArrayList<String> availableLangs= new ArrayList<>();
        availableLangs.add("FRA");
        availableLangs.add("ENG");
        if (availableLangs.contains(Settings.language.toString())){
            langToLoad = Settings.language.toString().toLowerCase();
        }
        BaseMod.loadCustomStringsFile(CardStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Card-Strings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Power-Strings.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Relic-Strings.json");
        BaseMod.loadCustomStringsFile(EventStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Event-Strings.json");
        BaseMod.loadCustomStringsFile(PotionStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Potion-Strings.json");
        BaseMod.loadCustomStringsFile(CharacterStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Character-Strings.json");
        BaseMod.loadCustomStringsFile(OrbStrings.class,getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Orb-Strings.json");
        logger.info("Done editing strings");
    }
    // ================ /LOAD THE TEXT/ ===================


    // ================ LOAD THE KEYWORDS ===================
    @Override
    public void receiveEditKeywords() {
        // Keywords on cards are supposed to be Capitalized, while in Keyword-String.json they're lowercase
        //
        // Multiword keywords on cards are done With_Underscores
        //
        // If you're using multiword keywords, the first element in your NAMES array in your keywords-strings.json has to be the same as the PROPER_NAME.
        // That is, in Card-Strings.json you would have #yA_Long_Keyword (#y highlights the keyword in yellow).
        // In Keyword-Strings.json you would have PROPER_NAME as A Long Keyword and the first element in NAMES be a long keyword, and the second element be a_long_keyword
        String langToLoad ="eng";
        ArrayList<String> availableLangs= new ArrayList<>();
        availableLangs.add("FRA");
        availableLangs.add("ENG");
        if (availableLangs.contains(Settings.language.toString())){
            langToLoad = Settings.language.toString().toLowerCase();
        }

        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/"+langToLoad+"/JesterMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }
    
    // ================ /LOAD THE KEYWORDS/ ===================    
    
    // this adds "ModName:" before the ID of any card/relic/power etc.
    // in order to avoid conflicts if any other mod uses the same ID.
    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
