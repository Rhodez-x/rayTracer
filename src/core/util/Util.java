package core.util;

import core.Globals;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Gabriel Jadderson on 14-05-2017.
 */
public class Util
{

    public static void takeScreenShot()
    {
        File[] files = new File(System.getProperty("user.dir")).listFiles();
        ArrayList<String> knownNames = new ArrayList<>();
        Arrays.stream(files).filter(x -> x.getName().endsWith(".png")).forEach(x -> knownNames.add(x.getName()));
        String uniqueName = generateUniqueName();
        if (!knownNames.contains(uniqueName))
        {
            try
            {
                ImageIO.write(Globals.outputRenderedImage, "png", new File(uniqueName + ".png"));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            takeScreenShot();
        }
    }

    private static String generateUniqueName()
    {
        String[] adjectives = {"abhorrent", "ablaze", "abnormal", "abrasive", "acidic", "alluring", "ambiguous", "amuck", "apathetic", "aquatic", "auspicious", "axiomatic", "barbarous", "bawdy", "belligerent", "berserk", "bewildered", "billowy", "boorish", "brainless", "bustling", "cagey", "calculating", "callous", "capricious", "ceaseless", "chemical", "chivalrous", "cloistered", "coherent", "colossal", "combative", "cooing", "cumbersome", "cynical", "daffy ", "damaged", "deadpan", "deafening", "debonair", "decisive", "defective", "defiant", "demonic", "delerious", "deranged", "devilish", "didactic", "diligent", "direful", "disastrous", "disillusioned", "dispensable", "divergent", "domineering", "draconian", "dynamic", "earsplitting", "earthy", "eatable", "efficacious", "elastic", "elated", "elfin", "elite", "enchanted", "endurable", "erratic", "ethereal", "evanescent ", "exuberant", "exultant", "fabulous", "fallacious ", "fanatical", "fearless", "feeble", "feigned ", "fierce", "flagrant", "fluttering", "frantic", "fretful", "fumbling", "furtive", "gainful", "gamy", "garrulous", "gaudy", "glistening", "grandiose", "grotesque", "gruesome", "guiltless", "guttural", "habitual", "hallowed", "hapless", "harmonious", "hellish", "hideous", "highfalutin", "hissing", "holistic", "hulking", "humdrum", "hypnotic", "hysterical", "icky", "idiotic", "illustrious", "immense", "immenent", "incandescent ", "industrious", "infamous", "inquisitive", "insidious", "invincible", "jaded", "jazzy", "jittery", "judicious", "jumbled", "juvenile", "kaput", "keen", "knotty ", "knowing", "lackadaisical ", "lamentable", "languid", "lavish", "lewd", "longing", "loutish", "ludicrous", "lush", "luxuriant", "lyrical", "macabre", "maddening", "mammoth", "maniacal", "meek", "melodic", "merciful", "mere", "miscreant", "momentous", "nappy", "nebulous", "nimble", "nippy", "nonchalant", "nondescript", "noxious", "numberless", "oafish", "obeisant", "obsequious ", "oceanic", "omniscient ", "onerous", "optimal", "ossified", "overwrought", "paltry", "parched", "parsimonious ", "penitent ", "perpetual", "picayune", "piquant", "placid", "plucky", "prickly", "probable", "profuse", "psychedelic ", "quack", "quaint", "quarrelsome", "questionable", "quirky", "quixotic", "quizzical ", "rambunctious ", "rampat", "raspy", "recondite ", "resolute", "rhetorical ", "ritzy", "ruddy", "sable", "sassy", "savory", "scandalous", "scintillating ", "sedate", "shaggy", "shrill", "smoggy", "somber", "sordid", "spiffy", "spurious", "squalid", "statuesque", "steadfast ", "stupendous", "succinct ", "swanky", "sweltering", "taboo", "tacit", "tangy", "tawdry", "tedious", "tenuous", "testy", "thundering", "tightfisted", "torpid", "trite", "truculent", "ubiquitous", "ultra", "unwieldy", "uppity", "utopian", "utter", "vacuous", "vagabond", "vengeful", "venomous", "verdant", "versed", "victorious", "vigorous", "vivacious", "voiceless", "volatile", "voracious", "vulgar", "wacky", "waggish", "wakeful", "warlike", "wary", "whimsical", "whispering", "wiggly", "wiry", "wistful", "woebegone", "woozy", "wrathful", "wretched", "xenial ", "xenophilic", "yummy", "yappy", "yielding", "zany", "zazzy", "zealous", "zesty", "zippy", "zoetic", "zoic ", "zonked"};
        String[] animalNames = {"Aardvark", "Aardwolf", "Albatross", "Alligator", "Alpaca", "Robin", "Anaconda", "Angelfish", "Anglerfish", "Ant", "Anteater", "Antelope", "Antlion", "Ape", "Aphid", "ArabianLeopard", "ArcticFox", "ArcticWolf", "Armadillo", "Baboon", "Badger", "BaldEagle", "Bandicoot", "Barnacle", "Barracuda", "Basilisk", "Bass", "Bat", "BeakedWhale", "Bear", "Beaver", "Bedbug", "Bee", "Beetle", "Bird", "Bison", "BlackBird", "BlackPanther", "BlackWidowSpider", "Sialia", "BlueBird", "BlueJay", "BlueWhale", "Boar", "Bobcat", "Bobolink", "Bonobo", "Booby", "BoxJellyfish", "Bovid", "Buffalo", "Bison", "Arthropod", "Bug", "Butterfly", "Buzzard", "Camel", "Canid", "Capybara", "Cardinal", "Caribou", "Carp", "Cat", "Catshark", "Caterpillar", "Catfish", "Cattle", "Centipede", "Cephalopod", "Chameleon", "Cheetah", "Chickadee", "Chicken", "Chimpanzee", "Chinchilla", "Chipmunk", "Cicada", "Clam", "Clownfish", "Cobra", "Cockroach", "Cod", "Condor", "Coral", "Cougar", "Cow", "Coyote", "Coypu", "Crab", "Crane", "CraneFly", "Crawdad", "Crayfish", "Cricket", "Crocodile", "Crow", "Cuckoo", "Damselfly", "Deer", "Dhole", "Dingo", "Dinosaur", "Dog", "Dolphin", "Dormouse", "Dove", "Dragonfly", "Dragon", "Duck", "DungBeetle", "Eagle", "Earthworm", "Earwig", "Echidna", "Eel", "Egret", "Elephant", "ElephantSeal", "RedDeer", "Emu", "Ermine", "Falcon", "Ferret", "Finch", "Firefly", "Fish", "Flamingo", "Flea", "Fly", "Flyingfish", "Fowl", "Fox", "Frog", "Megabat", "FruitBat", "Gamefowl", "Galliform", "Gazelle", "Gecko", "Gerbil", "GiantPanda", "GiantSquid", "Gibbon", "GilaMonster", "Giraffe", "Goat", "Goldfish", "Goose", "Gopher", "Gorilla", "Grasshopper", "GreatBlueHeron", "GreatWhiteShark", "GrizzlyBear", "GroundShark", "GroundSloth", "Grouse", "Guan", "Guanaco", "Guineafowl", "GuineaPig", "Gull", "Guppy", "Haddock", "Halibut", "HammerheadShark", "Hamster", "Hare", "Harrier", "Hawk", "Hedgehog", "HermitCrab", "Heron", "Herring", "Hippopotamus", "Hookworm", "Hornet", "Horse", "Hoverfly", "Hummingbird", "HumpbackWhale", "Hyena", "Iguana", "Impala", "IrukandjiJellyfish", "Insect", "Jackal", "Jaguar", "Jay", "Jellyfish", "Junglefowl", "Jacana", "Kangaroo", "KangarooMouse", "KangarooRat", "Kingfisher", "Kite", "Kiwi", "Koala", "Koi", "KomodoDragon", "Krill", "Ladybug", "Lamprey", "Landfowl", "LandSnail", "Lark", "Leech", "Lemming", "Lemur", "Leopard", "Leopon", "Limpet", "Lion", "Lizard", "Llama", "Lobster", "Locust", "Loon", "Louse", "Lungfish", "Lynx", "Macaw", "Mackerel", "Magpie", "Mammal", "Manatee", "Mandrill", "MantaRay", "Marlin", "Marmoset", "Marmot", "Marsupial", "Marten", "Mastodon", "Meadowlark", "Meerkat", "Mink", "Minnow", "Mite", "Mockingbird", "Mole", "Mollusk", "Mongoose", "MonitorLizard", "Monkey", "Moose", "Mosquito", "Moth", "MountainGoat", "Mouse", "Mule", "Muskox", "Narwhal", "Needlefish", "Newt", "NewWorldQuail", "Nighthawk", "Nightingale", "Numbat", "Ocelot", "Octopus", "Okapi", "OldWorldQuail", "Olingo", "Opossum", "Orangutan", "Orca", "Oribi", "Ostrich", "Otter", "Owl", "Owl-facedMonkey", "Ox", "Panda", "Panthera", "Panther", "PantheraHybrid", "Parakeet", "Parrot", "Parrotfish", "Partridge", "Peacock", "Peafowl", "Pelican", "Penguin", "Perch", "PeregrineFalcon", "Pheasant", "Pigeon", "Pike", "PilotWhale", "Pinniped", "Piranha", "Planarian", "Platypus", "Polar bear", "Pony", "Porcupine", "Porpoise", "Possum", "Prawn", "Mantis", "PrayingMantis", "Primate", "Ptarmigan", "Puffin", "Cougar", "Puma", "Pythonidae", "Python", "Quail", "Quelea", "Quokka", "Rabbit", "Raccoon", "RainbowTrout", "Rat", "Rattlesnake", "Raven", "Batoidea", "Ray", "Rajiformes", "RedPanda", "Reindeer", "Reptile", "Rhinoceros", "RightWhale", "Geococcyx", "Roadrunner", "Rodent", "Rook", "Rooster", "Roundworm", "Saber-toothedCat", "Sailfish", "Salamander", "Salmon", "Sawfish", "ScaleInsect", "Scallop", "Scorpion", "Hippocampus", "Seahorse", "SeaLion", "SeaSlug", "SeaSnail", "Shark", "Sheep", "Shrew", "Shrimp", "Silkworm", "Silverfish", "Skink", "Skunk", "Sloth", "Slug", "Smelt", "Snail", "Snake", "Snipe", "Snow leopard", "SockeyeSalmon", "Sole", "Sparrow", "Sperm whale", "Spider", "SpiderMonkey", "Spoonbill", "Squid", "Squirrel", "Starfish", "Star-nosedMole", "SteelheadTrout", "Stingray", "Stoat", "Stork", "Sturgeon", "SugarGlider", "Swallow", "Swan", "Swift", "Swordfish", "Swordtail", "Tahr", "Takin", "Tapir", "Tarantula", "Tarsier", "TasmanianDevil", "Termite", "Tern", "Thrush", "Tick", "Tiger", "TigerShark", "Tiglon", "Titi", "Toad", "Tortoise", "Toucan", "List", "TreeFrog", "Trout", "Tuna", "Turkey", "Turtle", "Urial", "VampireBat", "VampireSquid", "Vaquita", "Vicuña", "Viperidae", "Viper", "Voalavoanala", "Vole", "Vulture", "Wallaby", "Walrus", "Wasp", "Warbler", "Waterbuck", "WaterBuffalo", "WaterChevrotain", "Weasel", "Whale", "Whippet", "Coregonus", "Whitefish", "WhiteRhinoceros", "WhoopingCrane", "WildBoar", "Wildcat", "Wildebeest", "Wildfowl", "Wolf", "Wolverine", "Wombat", "WoodLemming", "Woodchuck", "Woodpecker", "WoollyDormouse", "WoollyHare", "Worm", "Wren", "Xerinae", "X-rayFish", "Yak", "YellowPerch", "Zebra", "ZebraFinch", "ZebraShark", "Zebu"};

        Random random = new Random();
        int r1 = random.nextInt(adjectives.length);

        int r2 = random.nextInt(adjectives.length);
        int r3 = random.nextInt(animalNames.length);

        String s1 = adjectives[r1].trim();
        String e1 = s1.substring(0, 1).toUpperCase() + s1.substring(1);
        String s2 = adjectives[r2].trim();
        String e2 = s2.substring(0, 1).toUpperCase() + s2.substring(1);
        String s3 = animalNames[r3].trim();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e1);
        stringBuilder.append(e2);
        stringBuilder.append(s3);

        return stringBuilder.toString();
    }
}