
/**
 * Provides Command words to be matched from the command line.
 * 
 * @author Original from Study Direct, Modified by 164870
 * @Date   (18/03/2017)
 */

public enum CommandWord {
    QUIT("quit"),
    MARK("mark"),
    STEP("step"),
    NEW("new"),
    UNKNOWN("unknown");

    private String word;

    CommandWord(String word) {
        this.word = word;
    }

    /**
     * Given the current word that the user has typed, convert it to lower case and see
     * if it matches the beginning of any of our command words.  If so, return the word
     * @param s The current user input word
     * @return The corresponding command word
     */
    public static CommandWord getCommandWord(String s) {
        for(CommandWord c : CommandWord.values()) {
            if(c.getWord().startsWith(s.toLowerCase())) {
                return c;
            }
        }
        return UNKNOWN;
    }

    /**
     * @return the string representing the command
     */
    public String getWord() {
        return this.word;
    }

}

