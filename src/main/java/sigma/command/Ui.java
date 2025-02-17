package sigma.command;

import java.util.ArrayList;
import java.util.Scanner;

import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.Task;
import sigma.task.TaskList;
import sigma.task.ToDo;

//CHECKSTYLE.OFF: Regexp
/**
 * Handles operations that are responsible for user interactions.
 * An Ui object provide support to other classes that require
 * user's input or wants to provide feedbacks back to the user.
 */
public class Ui {
    protected static String name = "Sigma";
    protected static String replyPrefix = name + ": ";

    private Scanner sc;
    private TaskList taskList;

    /**
     * Constructor of the Ui class.
     */
    public Ui() {
        this.taskList = new TaskList();
        this.sc = new Scanner(System.in);
    }

    private void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the reply prefix for dialogues spoken by the chatbot.
     * @return The reply prefix 
     */
    public static String getReplyPrefix() {
        return replyPrefix;
    }

    /**
     * Greets the user upon launching the chatbot.
    */
    protected void greet() {
        line();
        System.out.println(replyPrefix + "What's up, I'm " + name);
        System.out.println(replyPrefix + "What do you want?");
        line();
    }
    
    /**
     * Close up the dialogue and exits the system.
     */
    protected void exit() {
        line();
        System.out.println(replyPrefix + "Bye.");
        line();
        System.exit(0);
    }
    
    /**
     * Returns the response for handling the user 
     * interface of showing the current list.
     * 
     * @return The string of the current list.
     */
    private String handleShowList() {
        String response = "";

        line();
        if (taskList.getSize() == 0) {
            System.out.println(replyPrefix + "The list is empty right now.");
            response = "The list is empty right now.";
        } else {
            System.out.println(replyPrefix + "Stop slacking and lock in.");
            response += "Stop slacking and lock in.\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                System.out.println((i + 1) + ". " + task.toString());
                response += (i + 1) + ". " + task.toString() + "\n";
            }
        }
        line();

        return response;
    }

    /**
     * Returns the response for marking tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string containing the response of marking a task.
     */
    private String handleMark(String[] tokens) {
        String response = "";

        if (tokens.length > 2) { //unknown elements after command
            response = "I don't know what you're talking about.";

            line();
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
        } else {
            try {
                int index = Parser.parseIndex(tokens);
                taskList.markDone(index);

                response += "Good job bro, keep the grind going!\n";
                response += taskList.getTask(index - 1).toString();

                line();
                System.out.println("Good job bro, keep the grind going!");
                System.out.println(taskList.getTask(index - 1));
                line();
            } catch (ArrayIndexOutOfBoundsException e) {
                response = "Which one do you want to mark exactly?";

                line();
                System.out.println(replyPrefix + "Which one do you want to mark exactly?");
                line();
            } catch (IndexOutOfBoundsException e) {
                response = "Enter a valid task number. "
                            + "There probably ain't even any tasks to mark you bum.";

                line();
                System.out.println(replyPrefix + "Enter a valid task number. "
                                    + "There probably ain't even any tasks to mark you bum.");
                line();
            } catch (NumberFormatException e) {
                response = "Huh?";

                line();
                System.out.println("Huh?");
                line();
            }
        }
        
        return response;
    }

    /**
     * Returns the response for unmarking tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string containing the response of unmarking a task.
     */
    private String handleUnmark(String[] tokens) {
        if (tokens.length > 2) { //unknown elements after command
            line();
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
            
            return "I don't know what you're talking about.";
        }
        
        String response = "";
        try {
            int index = Parser.parseIndex(tokens);
            taskList.markUndone(index);
            
            response += "Come on bruh, focus!\n";
            response += taskList.getTask(index - 1).toString();

            line();
            System.out.println("Come on bruh, focus!");
            System.out.println(taskList.getTask(index - 1));
            line();
        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Which one do you want to unmark exactly?";

            line();
            System.out.println(replyPrefix + "Which one do you want to unmark exactly?");
            line();
        } catch (IndexOutOfBoundsException e) {
            response = "Enter a valid task number. "
                        + "There probably ain't even any tasks to unmark you bum.";

            line();
            System.out.println(replyPrefix + "Enter a valid task number. " 
                                + "There probably ain't even any tasks to unmark you bum.");
            line();
        } catch (NumberFormatException e) {
            response = "Huh?";

            line();
            System.out.println("Huh?");
            line();
        }

        return response;
    }

    /**
     * Returns the response for adding ToDo tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string of the response to adding a ToDo task.
     */
    private String handleAddToDo(String[] tokens) {
        String response = "";
        line();
        
        String taskName = Parser.parseToDo(tokens);
        
        try {
            ToDo todo = taskList.addToDo(taskName);

            response += "Aight, I will remember that for you.\n";
            response += "added: " + todo.toString() + "\n";
            response += "Now you have " + taskList.getSize() + " task(s) in the list!";

            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + todo.toString());
            System.out.println("Now you have " + taskList.getSize() + " task(s) in the list!");
        } catch (NoTaskNameException e) {
            System.out.println(replyPrefix + e.getMessage());
            response = e.getMessage();
        }

        line();

        return response;
    }

    /**
     * Returns the response for adding Deadline tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string of the response of adding a Deadline task.
     */
    private String handleAddDeadline(String[] tokens) {
        String response = "";

        line();
        
        String[] parsedInfos = Parser.parseDeadline(tokens);
        String taskName = parsedInfos[0];
        String date = parsedInfos[1];

        try {
            Deadline deadline = taskList.addDeadline(taskName, date);

            response += "Aight, I will remember that for you.\n";
            response += "added: " + deadline.toString() + "\n";
            response += "Now you have " + taskList.getSize() + " task(s) in the list!";
            
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + deadline.toString());
            System.out.println("Now you have " + taskList.getSize() + " task(s) in the list!");
        } catch (SigmaException e) {
            response = e.getMessage();
            System.out.println(replyPrefix + e.getMessage());
        }

        line();

        return response;
    }

    /**
     * Returns the response for adding Event tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string of the response of adding an Event task.
     */
    private String handleAddEvent(String[] tokens) {
        String response = "";
        line();
        
        try {
            String[] parsedInfos = Parser.parseEvent(tokens);
            String taskName = parsedInfos[0];
            String startDate = parsedInfos[1];
            String endDate = parsedInfos[2];

            Event event = taskList.addEvent(taskName, startDate, endDate);

            response += "Aight, I will remember that for you.\n";
            response += "added: " + event.toString() + "\n";
            response += "Now you have " + taskList.getSize() + " task(s) in the list!";

            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + event.toString());
            System.out.println("Now you have " + taskList.getSize() + " task(s) in the list!");
        } catch (SigmaException e) {
            response = e.getMessage();
            System.out.println(replyPrefix + e.getMessage());
        }

        line();

        return response;
    }

    /**
     * Returns the response for deleting tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string of the response of deleting a task.
     */
    private String handleDelete(String[] tokens) {
        line();
        
        if (tokens.length > 2) { //unknown elements after command
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
            return "I don't know what you're talking about.";
        }
        
        String response = "";
        try {
            int index = Parser.parseIndex(tokens);
            Task task = taskList.deleteTask(index);

            response += "I've removed this for you bud.\n" + index + ". " + task.toString() + "\n";
            response += "You've got " + taskList.getSize() + " task(s) left.";
            
            System.out.println("I've removed this for you bud.\n" + index + ". " + task.toString());
            System.out.println("You've got " + taskList.getSize() + " task(s) left.");
        } catch (ArrayIndexOutOfBoundsException e) {
            response = "Which one do you want to delete exactly?";
            System.out.println(replyPrefix + "Which one do you want to delete exactly?");
        } catch (IndexOutOfBoundsException e) {
            response = "Enter a valid task number. "
                        + "There probably ain't even any tasks to delete you bum.";
            System.out.println(replyPrefix + "Enter a valid task number. " 
                                + "There probably ain't even any tasks to delete you bum.");
        } catch (NumberFormatException e) {
            response = "Huh? There's no such task yo.";
            System.out.println("Huh? There's no such task yo.");
        }

        line();

        return response;
    }

    /**
     * Returns the response for finding tasks.
     * 
     * @param tokens The tokens of user input.
     * @return The string of the response of finding tasks.
     */
    private String handleFind(String[] tokens) {
        String response = "";
        line();

        try {
            String keyword = Parser.parseFind(tokens);
    
            ArrayList<Task> matchingTasks = taskList.find(keyword);

            if (matchingTasks.size() == 0) {
                response = "Unable to find any tasks matching the description.";
                System.out.println(replyPrefix + "Unable to find any tasks matching the description.");
            } else {
                System.out.println(replyPrefix + "These are probably what you're looking for.");
                response += "These are probably what you're looking for.\n";
                for (int i = 0; i < matchingTasks.size(); i++) {
                    Task task = matchingTasks.get(i);
                    response += (i + 1) + ". " + task.toString() + "\n";
                    System.out.println((i + 1) + ". " + task.toString());
                }
            }
            
        } catch (StringIndexOutOfBoundsException e) {
            response = "What are you even finding yo?";
            System.out.println(replyPrefix + "What are you even finding yo?");
        }

        line();

        return response;
    }

    /**
     * Handles the interpretation of user's input.
     * Awaits reply from the user to proceed to the next actions requested by the user.
     */
    public void awaitReply() {
        System.out.println("You: ");
        String reply = sc.nextLine();
        //Token reading solution below inspired by https://www.youtube.com/watch?v=lGHlFaF0F44
        String[] tokens = reply.split(" ");
        String command = tokens[0];
        switch (command) {
        case "bye":
            exit();
            break;
            
        case "list":
            handleShowList();
            awaitReply();
            break;
            
        case "mark":
            handleMark(tokens);
            awaitReply();
            break;
            
        case "unmark": 
            handleUnmark(tokens);
            awaitReply();
            break;
            
        case "todo": 
            handleAddToDo(tokens);
            awaitReply();
            break;
            
        case "deadline":
            handleAddDeadline(tokens);
            awaitReply();
            break;
            
        case "event": 
            handleAddEvent(tokens);
            awaitReply();
            break;
            
        case "delete": 
            handleDelete(tokens);
            awaitReply();
            break;

        case "find": 
            handleFind(tokens);
            awaitReply();
            break;
            
        default:
            //Invalid or Unknown command
            line();
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
            awaitReply();
        }
    }

    /**
     * Handles the interpretation of user's input through GUI.
     * Returns the response from Sigma after processing the input.
     * 
     * @param input The input from user.
     * @return The response from user input into the GUI.
     */
    public String generateResponse(String input) {
        String response = "";
        //Token reading solution below inspired by https://www.youtube.com/watch?v=lGHlFaF0F44
        String[] tokens = input.split(" ");
        String command = tokens[0];
        switch (command) {
        case "bye":
            exit();
            break;
            
        case "list":
            response = handleShowList();
            break;
            
        case "mark":
            response = handleMark(tokens);
            break;
            
        case "unmark": 
            response = handleUnmark(tokens);
            break;
            
        case "todo": 
            response = handleAddToDo(tokens);
            break;
            
        case "deadline":
            response = handleAddDeadline(tokens);
            break;
            
        case "event": 
            response = handleAddEvent(tokens);
            break;
            
        case "delete": 
            response = handleDelete(tokens);
            break;

        case "find": 
            response = handleFind(tokens);
            break;
            
        default:
            //Invalid or Unknown command
            response = "I don't know what you're talking about.";
        }

        return response;
    }
    
    /**
     * Returns the command type of the input.
     * 
     * @param input The input from user.
     * @return The command type.
     */
    public String identifyCommandType(String input) {
        String commandType = "";
        //Token reading solution below inspired by https://www.youtube.com/watch?v=lGHlFaF0F44
        String[] tokens = input.split(" ");
        String command = tokens[0];
        switch (command) {
        case "mark":
        case "unmark":
            commandType = "ChangeMarkCommand";
            break;
            
        case "todo": 
        case "deadline":
        case "event": 
            commandType = "AddCommand";
            break;
            
        case "delete": 
            commandType = "DeleteCommand";
            break;

        case "find": 
            commandType = "FindCommand";
            break;
            
        default:
            //Invalid or Other commands
            commandType = "N/A";
        }

        return commandType;
    }

    /**
     * Starts the chatbot by greeting and awaiting the next reply (command) from user.
     */
    public void start() {
        greet();
        awaitReply();
    }
    
    //Misc
    /**
     * UI tool to print a line for graphic purpose.
     */
    public static void line() {
        System.out.println("-------------------------------------------------" 
                            + "-------------------------------------------------------------------");
    }
}
