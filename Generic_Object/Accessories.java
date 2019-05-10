package Generic_Object;

/*
    Accessories extends OutfitMethods meaning that Accessories is a subclass of 
    superclass OutfitMethods. Accessories defines methods relating to accessory 
    creation.  

    Accessories can provide a variety of effects once equipped which can range 
    from increasing attrbutes, altering experience points gained, to negating 
    the odds of the wearer being afflicted with certain kinds of status effects. 
    Accessories can also effect abilities used outside of battle although such 
    accessories may come at a heavy price to the wearer. 
*/

import Generic_Object.OutfitMethods;
import Universally_Used_Methods.StaticMethods;
import Move_Creation.StatusEffect;
import java.util.ArrayList;  
import java.util.HashMap;

public class Accessories extends OutfitMethods
{
    // accessory effect on attributes of the wearer 
    private double maxHealth, maxStamina, maxNano, attack, defense, dexterity, 
        critical, accuracy, nanoAttack, nanoDefense;

    // value denoting maximum number of status effects that accessory can negate 
    int maxNumberOfStatusEffectsThatCanBeNegated;
    
    // ArrayList contains names of status effect objects accessory can negate
    private final ArrayList<String> statusEffectsNegated = new ArrayList<String>();
    
    // denotes regeneration/degeneration rates for following gauges on per turn basis 
    private double currentHealthRegeneration, currentStaminaRegeneration, 
        currentNanoRegeneration; 
    
    // level-up related variables which are directly multiplied with values and rounded down 
    private double expMultiplier, expGrowthRateBonus, skillPointBonus;
    
    // subclass constructor with method(s) invoked upon object creation 
    public Accessories()
    {
        // need to set subclass name to enable slot/core equip feature for object 
        setSubclassName("Accessories");
    }



    // START: ACCESSORY ATTRIBUTES
    /*******************************************************************************/

    public double validateAttribute(double attribute)
    {
        if(attribute < -75)
        {
            attribute = -75;
        }
        else if(attribute > 75)
        {
            attribute = 75;
        }

        return attribute;
    }

    public void setMaxHealth(double maxHealth) 
    {
        this.maxHealth = validateAttribute(maxHealth); 
    } 

    public double getMaxHealth()
    {
        return maxHealth; 
    } 

    public void setMaxStamina(double maxStamina)
    {
        this.maxStamina = validateAttribute(maxStamina);
    }

    public double getMaxStamina()
    {
        return maxStamina; 
    } 
    
    public void setMaxNano(double maxNano)
    {
        this.maxNano = validateAttribute(maxNano);
    }

    public double getMaxNano()
    {
        return maxNano; 
    }
    
    public void setAttack(double attack)
    {
        this.attack = validateAttribute(attack);
    }

    public double getAttack()
    {
        return attack; 
    } 

    public void setDefense(double defense)
    {
        this.defense = validateAttribute(defense);
    }

    public double getDefense()
    {
        return defense; 
    } 

    public void setDexterity(double dexterity)
    {
        this.dexterity = validateAttribute(dexterity);
    }

    public double getDexterity()
    {
        return dexterity; 
    } 

    public void setCritical(double critical)
    {
        this.critical = validateAttribute(critical);
    }

    public double getCritical()
    {
        return critical; 
    } 

    public void setAccuracy(double accuracy)
    {
        this.accuracy = validateAttribute(accuracy);
    }

    public double getAccuracy()
    {
        return accuracy; 
    } 

    public void setNanoAttack(double nanoAttack)
    {
        this.nanoAttack = validateAttribute(nanoAttack);
    }

    public double getNanoAttack()
    {
        return nanoAttack; 
    } 

    public void setNanoDefense(double nanoDefense)
    {
        this.nanoDefense = validateAttribute(nanoDefense);
    }

    public double getNanoDefense()
    {
        return nanoDefense; 
    } 

    // END: ACCESSORY ATTRIBUTES
    /*******************************************************************************/

    

    // START: STATUS EFFECTS ACCESSORY NEGATES 
    /*******************************************************************************/
    
    public void setMaxNumberOfStatusEffectsThatCanBeNegated(int 
        maxNumberOfStatusEffectsThatCanBeNegated)
    {
        this.maxNumberOfStatusEffectsThatCanBeNegated = lowerUpperBounds(0, 7, 
            maxNumberOfStatusEffectsThatCanBeNegated);
    }

    public int getMaxNumberOfStatusEffectsThatCanBeNegated()
    {
        return maxNumberOfStatusEffectsThatCanBeNegated;
    }
    
    public void addStatusEffectNegated(String statusName)
    {
        if(statusName != null && statusEffectsNegated.size() != maxNumberOfStatusEffectsThatCanBeNegated)
        {
            if(StaticMethods.getStatAsValidString(statusName) != null)
            {
                statusEffectsNegated.add(statusName);
            }
        }
    }

    public void removeStatusEffectNegated(String statusName)
    {
        statusEffectsNegated.remove(statusName);
    }

    public ArrayList<String> getStatusEffectsNegated()
    {
        return statusEffectsNegated;
    }
    
    public boolean statusEffectNegated(StatusEffect status)
    {
        boolean isNegated = false;
        
        for(String element : statusEffectsNegated)
        {
            if(StaticMethods.getStatAsValidEnum(element) == StaticMethods.
                getStatAsValidEnum(status.getName()))
            {
                isNegated = true;
            }
        }
        
        return isNegated;        
    }
    
    // END: STATUS EFFECTS ACCESSORY NEGATES 
    /*******************************************************************************/

    /*
        Note on "getTotal" methods below: 
            methods get total value for a object (like attack, nano, ect.) by adding 
            object's power and core podoubles together only if the cores are the same 
            type as the String supplied as argument (if "Attack" is supplied then 
            add the current core podoubles of the cores with core type "Attack") 
    */

    // START: TOTAL ACCESSORY ATTRIBUTES WITH CORES SUPPLIED
    /*******************************************************************************/

    public double getTotalMaxHealth()
    {
        return getTotalOutfitValue(getMaxHealth(), "Max Health"); 
    }
    
    public double getTotalMaxStamina()
    {
        return getTotalOutfitValue(getMaxStamina(), "Stamina"); 
    }
    
    public double getTotalMaxNano()
    {
        return getTotalOutfitValue(getMaxNano(), "Nano"); 
    }
    
    public double getTotalAttack()
    {
        return getTotalOutfitValue(getAttack(), "Attack"); 
    }

    public double getTotalDefense()
    {
        return getTotalOutfitValue(getDefense(), "Defense"); 
    }
    
    public double getTotalDexterity()
    {
        return getTotalOutfitValue(getDexterity(), "Dexterity"); 
    }

    public double getTotalCritical()
    {
        return getTotalOutfitValue(getCritical(), "Critical"); 
    }

    public double getTotalAccuracy()
    {
        return getTotalOutfitValue(getAccuracy(), "Accuracy"); 
    }

    public double getTotalNanoAttack()
    {
        return getTotalOutfitValue(getNanoAttack(), "Nano Attack"); 
    }

    public double getTotalNanoDefense()
    {
        return getTotalOutfitValue(getNanoDefense(), "Nano Defense"); 
    }

    // END: TOTAL ACCESSORY ATTRIBUTES WITH CORES SUPPLIED
    /*******************************************************************************/
    
    
    
    // START: ACCESSORY REGENERATION (CURRENT GAUGE = CURRENT GAUGE + (MAX GAUGE * REGENERATION))
    /*******************************************************************************/
    
    public double accessoryGaugeEffect(double effect)
    {
        if(effect < -1.0)
        {
            effect = 1.0;
        }
        else if (effect > 1.0)
        {
            effect = 1.0;
        }
        
        return effect;
    }
    
    public void setCurrentHealthRegeneration(double currentHealthRegeneration)
    {
        this.currentHealthRegeneration = accessoryGaugeEffect(currentHealthRegeneration);
    }
    
    public double getCurrentHealthRegeneration()
    {
        return currentHealthRegeneration;
    }
    
    public void setCurrentStaminaRegeneration(double currentStaminaRegeneration)
    {
        this.currentStaminaRegeneration = accessoryGaugeEffect(currentStaminaRegeneration);
    }
    
    public double getCurrentStaminaRegeneration()
    {
        return currentStaminaRegeneration;
    }
    
    public void setCurrentNanoRegeneration(double currentNanoRegeneration)
    {
        this.currentNanoRegeneration = accessoryGaugeEffect(currentNanoRegeneration);
    }
    
    public double getCurrentNanoRegeneration()
    {
        return currentNanoRegeneration;
    }
    
    // END: ACCESSORY REGENERATION (CURRENT GAUGE = CURRENT GAUGE + (MAX GAUGE * REGENERATION))
    /*******************************************************************************/

    
    
    // START: LEVELING RELATED EFFECTS (Ex: exp *= expMultiplier)
    /*******************************************************************************/
    
    public void setExpMultiplier(double expMultiplier)
    {
        this.expMultiplier = lowerUpperBounds(0.0, 3.0, expMultiplier);
    }
    
    public double getExpMultiplier()
    {
        return expMultiplier;
    }
    
    public void setExpGrowthRateBonus(double expGrowthRateBonus)
    {
        this.expGrowthRateBonus = lowerUpperBounds(0.0, 2.5, expGrowthRateBonus);
    }
    
    public double getGrowthExpRateBonus()
    {
        return expGrowthRateBonus;
    }
    
    public void setSkillPointBonus(double skillPointBonus)
    {
        this.skillPointBonus = lowerUpperBounds(0.0, 1.75, skillPointBonus);
    }
    
    public double getSkillPointBonus()
    {
        return skillPointBonus;
    }
    
    // END: LEVELING RELATED EFFECTS 
    /*******************************************************************************/
    
    /* 
        IO (I.O., "Input/Output"/"Inside"/"Outside" System) Outside of combat skills 
        (skill checks) range from 0 - 100 (pt 1), 100-150 (pt 2), 150-200 (pt 3)]
            Intelligence  // requires an exceptional degree of understanding to perform correctly 
                General     // skills that everyone is capable of doing but seldom well enough 
                    Lock Pick   // ability to unlock containers, doors, and other things locked with physical locks 
                        1. unlock physical locks ONLY (locked containers, doors, safes, ect.)
                        2. improving skill reduces odds of lock picks being broken 
                        3. skill level determines how fast lock is picked and how discreetly 
                        4. improves "brute force" opening technique for incredibly difficult lockpicks 
                        5. improves chance of reusing a lock pick if attempt fails 
                        6. reduces odds of tripping alarm or security measures tied to locked object
                    Loot        // ability to search things/people for valuables quickly/efficiently 
                        1. search is performed much more thoroughly thus increasing odds to find dropped loot    
                        2. reduce odds of loot being destroyed in battle thus increasing number of objects dropped 
                        3. increase odss of gaining a rare object from opposition 
                        4. better chance of gaining more objects from containers, ect.
                        5. better yield when searching environments for materials 
                        6. improved odds of discovering hidden compartments or locations due to looting experience
                    Pilfer      // ability to swipe a weapon/armor/accessory from a target discreetly/forcefully 
                        1. improves odds of disarming a weapon or stripping armors/accessories from target 
                        1. improves odds of taking a weapon or taking armors/accessories from target 
                    Repair      // ability to dix the durability or damaged state of an object using tools or other objects 
                        1. allows repairing objects by breaking down objects from SAME SuperType ONLY 
                        2. chance that repairing object can boost overall durability as well as stats tied to object 
                        3. fixing broken parts/machinery/ect. requires parts and less parts are needed the higher repair is 
                        4. chance that materials used to repair an object may not be used up 
                        5. chance for properties of object broken down to repair another object is passed on 
                        6. improves odds of repair being successful and recovering greater durabilityfor object 
                    Service     // ability to perfrom general purpose services people rarely feel like doing 
                        1. improves odds of success for performing service and odds for gaining an object for a job well done 
                        2. more likely to get more options on mission line by getting on target's good side 
                        3. better chance to get to know your team, allies, or friends by servicing them 
                        4. chance to improve another skill or gain skill points FOR FREE depending on service performed 
                    Steal       // ability to take an object away from a target discreetly/forcefully 
                        1. improves chance to steal an object from a target or object in the environment 
                        2. allows for different steal techniques which allows character to steal in different situations discreetly
                        3. boosts odds of stealling multiple objects at once 
                        4. reduces chance of incurring a steal penalty on repeated attempts whic reduces steal odds on future attempts
                        5. can allow character another chance to steal an object 
                        6. allows character to swap an object in inventory for object chosen for steal attempt (more luck based)
                Specialist  // worthwhile skills requiring a great deal of effort to master 
                    Craft       // ability to create objects from other objects 
                        1. objects can be crafted by using scraps/parts/objects/ect. ONLY at crafting tables 
                        2. it is not possible to craft health/status items since their require very specific knowledge/objects/recipes 
                        3. it is possible for some of the objects used in crafting to be saved (as in not used for crafting at all)
                        4. to craft objects, a crafting recipe must be bought or put together by analzing parts from breaking object down 
                        5. objects crafted are always stronger durability-wise and stat-wise than their store counterparts 
                        6. in rare cases, it is possible to craft the same object again FOR FREE
                        7. it is possible to craft items or other objects that another person needs or that the mission requires
                    Hack        // ability to use one's tech skills to alter the state of another piece of tecnology 
                        1. improves chance of using technology to achieve an end (gurad using turrets, control robots, ect.)
                        2. chance to bypass electronic locks which can lead to loot or extra locations 
                        3. grants character the ability to manipulate electronic devices to improve/harm them (includes A.I.M.S. apparatus)
                        4. access additional features/information normally unavailable to electronic devices at their current state 
                        5. counter-hack those attempting to hack you or someone important to the current mission 
                        6. have a greater understanding of what technology can do and how to use it to your advantage 
                    Firearms    // ability to utilize firearms to their full potential in a given scenario 
                        1. improves the use of firearms in various situations ranging from sports to intense situations
                        2. faster reaction time allowing character to fre quicker shots alongisde improving accuracy 
                        3. easier to understand how firearms and other long range weapons function for easier use 
                        4. maintenance is improved greatly meaning that gun lasts longer and jams/fails to fire less
                        5. improves odds of scoring ricochets and splash damage by allowing character to better aim shots 
                        6. allows for more precise aiming meant for disarming targets or for scoring 
                    Melee       // ability to utilize melee weapons to their full potential in a given scenario 
                        1. allows for the use of melee or close quarters combat weapons to slve certain problems 
                        2. chance of attacking a target before battle even begins forheavy damage 
                        3. strikink an object with enough force can provide a variety of effects based on object and environment 
                        4. greatly improves chances of opening locked containers or areas by using physical force
                        5. objects struck for opening are less likely to have many of its contents destroyed 
                    Medicine    // ability to use medicine in a positive or negative way resulting in more powerful effects
                        1. allows for crafting items for healing, causing status effects, or both if recipe exists 
                        2. effects of crafted items are greatly boosted in terms of effect 
                        3. allows character to provide medical attention or provide medical insight on a given problem 
                        4. allows for detection of texins and evaluation of a target/thing/ect. from a medical perspective 
                        5. extensive medical knowledge can very useful in/out of academic environments since it shows intelligence     
                    Nano Tech   // ability to use nanomachines in imaginative ways that could help or hurt the situation by using nano 
                        1. strengthens the ability to manipulate nanomachines allqing for different problem solutions 
                        2. allows access to machines that cannot be tampered with by normal means 
                        3. improves detection rate of nano moves and how to effectively counter them 
                        4. can temporirly boost or reduce the state of the character or target for a brief time 
            Observation   // requires understanding not only state of oneself but one's environment 
                Charisma    // involves using charm and words to manipulate targets 
                    Barter      // ability to engage in trades with another entity and negotiate using objects as pawns 
                        1. improves odds of initiating a successful trade 
                        2. improves negotiating skills out in the field since character has a better idea of what must be offered 
                        3. allows character another chance to barter if first attemot fails 
                        4. allows access to unique quests and interactions with merchants and tradesmen 
                        5. may allow character to avoid a battle situation if certain objects opposition desires can be offered 
                    Cunning     // ability to use the situation at hand to one's (or party's) own advantage 
                        1. allows character to identify potential solutions to problems which stand to benefit character the most 
                        2. character can contend against other cunning characters in the game world using cunning
                        3. improves situational awareness allowing character to act with grace and likeability 
                        4. character is able to determine potential solutions to a mission thus allowing character to branch out more 
                        5. allows character to outsmart the opposition by allowing character to prepare an environment for combat 
                    Haggle      // ability to obtain more favorable rewards and alter the price of objects through negotiation
                        1. allows character to engage in a spirited debate with another character over an object or service 
                        2. money for object can be decreased greatly or be reduced to 0 for transaction is service is provided in exchange 
                        3. may allow character to avoid a battle situation if haggle with opposition is successful 
                        4. allows access to unique quests and interactions with merchants and tradesmen 
                        5. allows character another chance to haggle if sirt attempt fails 
                    Speech      // ability to use one's own words in an effective manner resulting in a variety of effects 
                        1. improves chance for character to convince another character to perform an action supposedly for their own benefit 
                        2. rally techniques or talking to audiences to convince them to perform a desired action is improved
                        3. a silver tongue can open many doors/scenarios that may or may not be normally available to all players 
                Perception  // involves observing the environment and the people within it 
                    Athletics   // ability to perform athletic feats which can involve jumping, running, and scaling walls by using stamina 
                        1. overall fitness of the character is improved allowing character to perform athletic feats with ease 
                        2. chance for stamina cost of move to be reduced if not outright negated 
                        3. allows character to access areas which may be too dangerous to access normally if at all 
                        4. enables character to exhaust the opposition by outlasting them in competitions or out in the field 
                    Analyze     // ability to determine the current state of the situation as well as the state of peole involved 
                        1. allows character to determine the state of the current situation allowing character to understand what is going on 
                        2. analysis includes analyzing not only the environment but also the entities within it 
                        3. allows character to identify features not normally seen by many characters 
                        4. investigation into the environment is improved allowing character to easily use it to their advantage 
                        5. character can ask more probing questions which can lead to varying/questionable results 
                    Caution     // ability to determine whether situations, people, or things pose an immediate threat to user 
                        1. character is more wary of entities within a given environment resulting in chance for positive status effects 
                        2. chance to be discretly alerted that a battle is at hand, a character is dangerous, or if situation is dangerous 
                        3. chance to detect traps or other unnatural things in an environment resulting in a lower chance of being unprepared 
                    Sneak       // ability to creep through an area undetected with the possibilty of avoiding combat   
                        1. chance to bypass combat scenario completely at the cost of missing valuable experience 
                        2. sneaking through an environment undetected becomes a viable strategy 
                        3. possible to combine sneak scenarios with pilfer and steal for maximum shenanigans 
                        4. possible to have situations where character is virtually invisible resulting in unique scenarios 
                        5. possible to cleverly use objects and the enviornment to distract the opposition out in the field     */
    
    // START: OUTSIDE OF COMBAT SKILLS MANAGEMENT USING HASHMAP
    /*******************************************************************************/
    
    // contains skills that are considered valid 
    public enum ValidSkills
    {
        LOCKPICK, LOOT, PILFER, REPAIR, SERVICE, STEAL, CRAFT, HACK, FIREARMS, 
        MELEE, MEDICINE, NANO_TECH, BARTER, CUNNING, HAGGLE, SPEECH, ATHLETICS, 
        ANALYZE, CAUTION, SNEAK;
    }
    
    // get String representing ValidSkills enum based on ValidSkills enum passed 
    public static String getValidSkillAsStringUsingEnum(ValidSkills skills)
    {
        String result = null; 
        
        switch(skills)
        {
            case LOCKPICK:
                result = "Lockpick";
                    break;
            case LOOT:
                result = "Loot";
                    break;
            case PILFER:
                result = "Pilfer";
                    break;
            case REPAIR:
                result = "Repair";
                    break;
            case SERVICE:
                result = "Service";
                    break;
            case STEAL:
                result = "Steal";
                    break;
            case CRAFT:
                result = "Craft";
                    break;
            case HACK:
                result = "Hack";
                    break;
            case FIREARMS:
                result = "Firearms";
                    break;
            case MELEE:
                result = "Melee";
                    break;
            case MEDICINE:
                result = "Medicine";
                    break;
            case NANO_TECH:
                result = "Nano Tech";
                    break;
            case BARTER:
                result = "Barter";
                    break;
            case CUNNING:
                result = "Cunning";
                    break;
            case HAGGLE:
                result = "Haggle";
                    break;
            case SPEECH:
                result = "Speech";
                    break;   
            case ATHLETICS:
                result = "Athletics";
                    break;
            case CAUTION:
                result = "Caution";
                    break;     
            case SNEAK:
                result = "Sneak";
                    break;
        }
        
        return result; 
    }
    
    // returns array of enum values for ValidSkills as array of Strings 
    public static String[] validSkillsAsStrings()
    {
        String[] array = new String[StaticMethods.getNumberOfEnumElements(ValidSkills.values())];
        
        for(int i = 0; i < ValidSkills.values().length; i++)
        {
            array[i] =  getValidSkillAsStringUsingEnum(ValidSkills.values()[i]);
        }

        return array;
    }
    
    // returns ValidSkills enum form of String passed so long as String is valid 
    public ValidSkills getValidSkillEnumUsingString(String argument)
    {
        ValidSkills result = null;
        
        if(StaticMethods.isStringValid(validSkillsAsStrings(), argument))
        {
            result = ValidSkills.valueOf(StaticMethods.stringToEnum(argument));
        }
        
        return result;
    }
    
    // skillsAndValues HashMap creation and population through static means 
    private static final HashMap<ValidSkills, Integer> skillsAndValues =
        new HashMap<ValidSkills, Integer>();
        static 
        {
            for (ValidSkills skill : ValidSkills.values()) 
            {
                skillsAndValues.put(skill, 0);
            }
        }
    
    // return HashMap key representing skill name as String based on String passed 
    public String getSkillAsString(String argument) 
    {
        String result = null;
        
        for(ValidSkills key : skillsAndValues.keySet())
        {
            if(key == getValidSkillEnumUsingString(argument))
            {
                result = getValidSkillAsStringUsingEnum(key);
            }
        }
        
        return result;
    } 
    
    // return HashMap key representing skill name as ValidSkills enum based on String passed 
    public ValidSkills getSkillAsEnum(String argument) 
    {
        ValidSkills result = null;
        
        for(ValidSkills key : skillsAndValues.keySet())
        {
            if(key == getValidSkillEnumUsingString(argument))
            {
                result = key;
            }
        }
        
        return result;
    } 
    
    // return value associated with skill based on String passed 
    public int getSkillValue(String argument) 
    {
        return skillsAndValues.get(getValidSkillEnumUsingString(argument));
    }
    
    // ensure that skill provided by accessory to appropriate skill 
    public int validateSkillValues(int argument)
    {
        if(argument < 0)
        {
            argument = 0;
        } 
        else if(argument > 100)
        {
            argument = 100;
        }
        
        return argument;
    }
    
    // update value for desired skill of HashMap skillsAndValues
    public void updateValueForDesiredKey(String argument, int value)
    {
        if(getValidSkillEnumUsingString(argument) != null)
        {
            skillsAndValues.put(getValidSkillEnumUsingString(argument),
                (Integer)validateSkillValues(value));
        }
    }
    
    // return HashMap containing skills and skill values 
    public HashMap<ValidSkills, Integer> getAccessorySkillsHashMap()
    {
        return skillsAndValues;
    }
    
    // END: OUTSIDE OF COMBAT SKILLS MANAGEMENT USING HASHMAP
    /*******************************************************************************/
}