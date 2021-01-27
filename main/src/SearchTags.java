public class SearchTags {

    String[] theme = {
            "Not Specified Theme","Aliens","Animals","Cooking","Crossdressing","Delinquents","Demons","Genderswap","Ghosts","Gyaru",
            "Harem","Incest","Loli","Mafia","Magic","Martial Arts","Military","Monster Girls","Monsters","Music","Ninja",
            "Office Workers","Police","Post-Apocalyptic","Reincarnation","Reverse Harem","Samurai","School Life","Shota",
            "Supernatural","Survival","Time Travel","Traditional Games","Vampires","Video Games","Virtual Reality","Zombies"};
    String[] genre = {
            "Not Specified genre","Action","Adventure","Comedy","Crime","Drama","Fantasy","Historical","Horror","Isekai","Magical Girls",
            "Mecha","Medical","Mystery","Philosophical","Psychological","Romance","Sci-Fi","Shoujo Ai","Shounen Ai","Slice of Life",
            "Sports","Superhero","Thriller","Tragedy","Wuxia","Yaoi","Yuri"};
    String[] format = {"Not Specified Format","4-Koma","Adaptation","Anthology","Award Winning","Doujinshi","Fan Colored","Full Color",
            "Long Strip","Official Colored","Oneshot","User Created","Web Comic"};
    String[] content = {"Not Specified Content","Ecchi","Gore","Sexual Violence","Smut"};

    public SearchTags() {}

    public String search(String tagQuery) {
        String toReturn=tagQuery;

        for (String s : theme) {
            if (toReturn.contains("!"+s)) {
                toReturn=toReturn.replaceAll("!"+s," Themes NOT LIKE '%"+s+"%' ");
                continue;
            }
            toReturn=toReturn.replaceAll(s," Themes LIKE '%"+s+"%' ");
        }
        for (String s : genre) {
            if (toReturn.contains("!"+s)) {
                toReturn=toReturn.replaceAll("!"+s," Genres NOT LIKE '%"+s+"%' ");
                continue;
            }
            toReturn=toReturn.replaceAll(s," Genres LIKE '%"+s+"%' ");
        }
        for (String s : format) {
            if (toReturn.contains("!"+s)) {
                toReturn=toReturn.replaceAll("!"+s," Format NOT LIKE '%"+s+"%' ");
                continue;
            }
            toReturn=toReturn.replaceAll(s," Format LIKE '%"+s+"%' ");
        }
        for (String s : content) {
            if (toReturn.contains("!"+s)) {
                toReturn=toReturn.replaceAll("!"+s," Content NOT LIKE '%"+s+"%' ");
                continue;
            }
            toReturn=toReturn.replaceAll(s," Content LIKE '%"+s+"%' ");
        }
        toReturn=toReturn.replaceAll("\\|\\|"," OR ");
        toReturn=toReturn.replaceAll("&&"," AND ");
        toReturn=toReturn.replaceAll("\\("," \\( ");
        toReturn=toReturn.replaceAll("\\)"," \\) ");

        return toReturn;
    }

}
