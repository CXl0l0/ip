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
     * UI method of handling the user interface of showing the
     * current list.
     */
    private void handleShowList() {
        line();
        if (taskList.getSize() == 0) {
            System.out.println(replyPrefix + "The list is empty right now.");
        } else {
            System.out.println(replyPrefix + "Stop slacking and lock in.");
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                System.out.println((i + 1) + ". " + task.toString());
            }
        }
        line();
    }

    /**
     * UI method of handling marking tasks.
     * @param tokens The tokens of user input.
     */
    private void handleMark(String[] tokens) {
        if (tokens.length > 2) { //unknown elements after command
            line();
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
        } else {
            try {
                int index = Parser.parseIndex(tokens);
                taskList.markDone(index);

                line();
                System.out.println("Good job bro, keep the grind going!");
                System.out.println(taskList.getTask(index - 1));
                line();
            } catch (ArrayIndexOutOfBoundsException e) {
                line();
                System.out.println(replyPrefix + "Which one do you want to mark exactly?");
                line();
            } catch (IndexOutOfBoundsException e) {
                line();
                System.out.println(replyPrefix + "Enter a valid task number. "
                                    + "There probably ain't even any tasks to mark you bum.");
                line();
            }
        }
        
    }

    /**
     * UI method of handling unmarking tasks.
     * @param tokens The tokens of user input.
     */
    private void handleUnmark(String[] tokens) {
        if (tokens.length > 2) { //unknown elements after command
            line();
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
        } else {
            try {
                int index = Parser.parseIndex(tokens);
                taskList.markUndone(index);
                
                line();
                System.out.println("Come on bruh, focus!");
                System.out.println(taskList.getTask(index - 1));
                line();
            } catch (ArrayIndexOutOfBoundsException e) {
                line();
                System.out.println(replyPrefix + "Which one do you want to unmark exactly?");
                line();
            } catch (IndexOutOfBoundsException e) {
                line();
                System.out.println(replyPrefix + "Enter a valid task number. " 
                                    + "There probably ain't even any tasks to unmark you bum.");
                line();
            }
        }
    }

    /**
     * UI method of handling adding ToDo tasks.
     * @param tokens The tokens of user input.
     */
    private void handleAddToDo(String[] tokens) {
        line();
        
        String taskName = Parser.parseToDo(tokens);
        
        try {
            ToDo todo = taskList.addToDo(taskName);
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + todo.toString());
            System.out.println("Now you have " + taskList.getSize() + " task(s) in the list!");
        } catch (NoTaskNameException e) {
            System.out.println(replyPrefix + e.getMessage());
        }

        line();
    }

    /**
     * UI method of handling adding Deadline tasks.
     * @param tokens The tokens of user input.
     */
    private void handleAddDeadline(String[] tokens) {
        line();
        
        String[] parsedInfos = Parser.parseDeadline(tokens);
        String taskName = parsedInfos[0];
        String date = parsedInfos[1];

        try {
            Deadline deadline = taskList.addDeadline(taskName, date);
            
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + deadline.toString());
            System.out.println("Now you have " + taskList.getSize() + " task(s) in the list!");
        } catch (SigmaException e) {
            System.out.println(replyPrefix + e.getMessage());
        }

        line();
    }

    /**
     * UI method of handling adding Event tasks.
     * @param tokens The tokens of user input.
     */
    private void handleAddEvent(String[] tokens) {
        line();
        
        try {
            String[] parsedInfos = Parser.parseEvent(tokens);
            String taskName = parsedInfos[0];
            String startDate = parsedInfos[1];
            String endDate = parsedInfos[2];

            Event event = taskList.addEvent(taskName, startDate, endDate);
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + event.toString());
            System.out.println("Now you have " + taskList.getSize() + " task(s) in the list!");
        } catch (SigmaException e) {
            System.out.println(replyPrefix + e.getMessage());
        }

        line();
    }

    /**
     * UI method of handling deleting tasks.
     * @param tokens The tokens of user input.
     */
    private void handleDelete(String[] tokens) {
        line();

        if (tokens.length > 2) { //unknown elements after command
            System.out.println(replyPrefix + "I don't know what you're talking about.");
        } else {
            try {
                int index = Parser.parseIndex(tokens);
                Task task = taskList.deleteTask(index);
                
                System.out.println("I've removed this for you bud.\n" + index + ". " + task.toString());
                System.out.println("You've got " + taskList.getSize() + " task(s) left.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(replyPrefix + "Which one do you want to delete exactly?");
            } catch (IndexOutOfBoundsException e) {
                System.out.println(replyPrefix + "Enter a valid task number. " 
                                    + "There probably ain't even any tasks to delete you bum.");
            }
        }

        line();
    }

    /**
     * UI method of finding tasks.
     * @param tokens The tokens of user input.
     */
    private void handleFind(String[] tokens) {
        line();

        try {
            String keyword = Parser.parseFind(tokens);
    
            ArrayList<Task> matchingTasks = taskList.find(keyword);

            if (matchingTasks.size() == 0) {
                System.out.println(replyPrefix + "Unable to find any tasks matching the description.");
            } else {
                System.out.println(replyPrefix + "These are probably what you're looking for.");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    Task task = matchingTasks.get(i);
                    System.out.println((i + 1) + ". " + task.toString());
                }
            }
            
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(replyPrefix + "What are you even finding yo?");
        }

        line();
    }

    /**
     * Main function that handles the interpretation of user's input.
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
