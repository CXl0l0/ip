package sigma.command;

//CHECKSTYLE.OFF: Regexp
/**
 * A parser class that handles interpreting user inputs,
 * then outputting appropriate information back.
 */
public class Parser {
    /**
     * Parses the tokens for commands that requires the index of the task.
     * Returns the index of the target tasks to be acted on.
     * @param tokens The tokens of string to be interpreted.
     * @return The index of the target task.
     * @throws ArrayIndexOutOfBoundsException If there is missing input of the target to be marked.
     */
    public static int parseIndex(String[] tokens) throws ArrayIndexOutOfBoundsException {
        try {
            return Integer.parseInt(tokens[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Parses the tokens for the command to add ToDo tasks.
     * @param tokens The tokens of string to be interpreted.
     * @return The string of the task name.
     */
    public static String parseToDo(String[] tokens) {
        String taskName = "";
        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens[i] + " ";
        }

        return taskName;
    }

    /**
     * Parses the tokens for the command to add Deadline tasks.
     * @param tokens The tokens of string to be interpreted.
     * @return An array of strings containing the task name and deadline.
     */
    public static String[] parseDeadline(String[] tokens) {
        String taskName = "";
        String date = "";
        boolean isReadingDate = false;
        for (int i = 1; i < tokens.length; i++) {
            String str = tokens[i];
            if (isReadingDate) {
                date += " " + str;
            } else if (str.equals("/by")) {
                isReadingDate = true;
                continue;
            } else if (!str.equals("")) {
                taskName += " " + str;
            }
        }

        return new String[]{taskName, date};
    }

    /**
     * Parses the tokens for the command to add Event tasks.
     * @param tokens The tokens of string to be interpreted.
     * @return An array of strings containing the task name, event start date and end date.
     */
    public static String[] parseEvent(String[] tokens) {
        String taskName = "";
        String from = "";
        String to = "";
        boolean isReadingFrom = false;
        boolean isReadingTo = false;
        for (int i = 1; i < tokens.length; i++) {
            String str = tokens[i];
            if (str.equals("/from")) {
                isReadingFrom = true;
                isReadingTo = false;
                continue;
            } else if (str.equals("/to")) {
                isReadingFrom = false;
                isReadingTo = true;
                continue;
            } else if (isReadingFrom) {
                from += " " + str;
            } else if (isReadingTo) {
                to += " " + str;
            } else if (!str.equals("")) {
                taskName += " " + str;
            }
        }

        return new String[]{taskName, from, to};
    }

    /**
     * Parses the tokens for the command to find tasks.
     * @param tokens The tokens of string to be interpreted.
     * @return The keyword string.
     */
    public static String parseFind(String[] tokens) {
        String keyword = "";
        for (int i = 1; i < tokens.length; i++) {
            String word = tokens[i];
            keyword += " " + word;
        }

        return keyword.substring(1);
    }
}
