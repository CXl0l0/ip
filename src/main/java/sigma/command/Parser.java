package sigma.command;

//CHECKSTYLE.OFF: Regexp

import sigma.exception.NoNewDeadlineInfoException;
import sigma.exception.NoNewNameException;
import sigma.exception.SigmaException;

/**
 * A parser class that handles interpreting user inputs,
 * then outputting appropriate information back.
 */
public class Parser {
    /**
     * Parses the tokens for commands that requires the index of the task.
     * Returns the index of the target tasks to be acted on.
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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

    /**
     * Parses the tokens for the command to edit tasks.
     * 
     * @param tokens The tokens of string to be interpreted.
     * @param taskType The task type of the target task.
     * @return The parsed information as an array of String.
     */
    public static String[] parseEdit(String[] tokens, String taskType) throws SigmaException {
        assert taskType != null;
        assert taskType != "";

        switch (taskType) {
        case "T":
            return parseEditToDo(tokens);
        case "D":
            return parseEditDeadline(tokens);
        case "E":
            return parseEditEvent(tokens);
        default:
            assert taskType == "T" 
                    || taskType == "D"
                    || taskType == "E";
        }

        return null;
    }

    private static String[] parseEditToDo(String[] tokens) throws NoNewNameException {
        String[] newInfos = new String[1];
        String newTaskName = "";
        if (tokens.length < 4 || !tokens[2].equals("/name")) {
            throw new NoNewNameException();
        }

        for (int i = 3; i < tokens.length; i++) {
            newTaskName += " " + tokens[i];
        }

        newInfos[0] = newTaskName;
        return newInfos;
    }

    private static String[] parseEditDeadline(String[] tokens) throws NoNewDeadlineInfoException {
        String[] newInfos = new String[2];
        String newTaskName = "";
        String newDeadline = "";

        if (tokens.length < 4 || !tokens[2].equals("/name") && !tokens[2].equals("/by")) {
            throw new NoNewDeadlineInfoException();
        }

        boolean isReadingNewTaskName = false;
        boolean isReadingNewDeadline = false;
        for (int i = 2; i < tokens.length; i++) {
            String str = tokens[i];
            if (str.equals("/name")) {
                isReadingNewTaskName = true;
                isReadingNewDeadline = false;
                continue;
            } else if (str.equals("/by")) {
                isReadingNewTaskName = false;
                isReadingNewDeadline = true;
                continue;
            } else if (isReadingNewTaskName) {
                newTaskName += " " + str;
            } else if (isReadingNewDeadline) {
                newDeadline += " " + str;
            }
        }

        newInfos[0] = newTaskName;
        newInfos[1] = newDeadline;

        return newInfos;
    }

    private static String[] parseEditEvent(String[] tokens) {
        return null;   
    }
}
