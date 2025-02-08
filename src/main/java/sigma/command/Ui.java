package sigma.command;

import java.util.Scanner;

import sigma.task.TaskList;

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

    public Ui() {
        this.taskList = new TaskList();
        this.sc = new Scanner(System.in);
    }

    private void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the reply prefix for dialogues spoken by the chatbot.
     *
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
            taskList.showList();
            awaitReply();
            break;
            
            case "mark": {
                if (tokens.length > 2) { //unknown elements after command
                    line();
                    System.out.println(replyPrefix + "I don't know what you're talking about.");
                    line();
                    awaitReply();
                }
                
                try {
                    int index = Integer.parseInt(tokens[1]);
                    taskList.markDone(index);
                    awaitReply();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    line();
                    System.out.println(replyPrefix + "Which one do you want to mark exactly?");
                    line();
                    awaitReply();
                }
            }
            
            case "unmark": {
                if (tokens.length > 2) { //unknown elements after command
                    line();
                    System.out.println(replyPrefix + "I don't know what you're talking about.");
                    line();
                    awaitReply();
                }
                
                try {
                    int index = Integer.parseInt(tokens[1]);
                    taskList.markUndone(index);
                    awaitReply();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    line();
                    System.out.println(replyPrefix + "Which one do you want to unmark exactly?");
                    line();
                    awaitReply();
                }
            }
            
            case "todo": {
                taskList.addToDo(tokens);
                awaitReply();
                break;
            }
            
            case "deadline": {
                taskList.addDeadline(tokens);
                awaitReply();
                break;
            }
            
            case "event": {
                taskList.addEvent(tokens);
                awaitReply();
                break;
            }
            
            case "delete": {
                if (tokens.length > 2) { //unknown elements after command
                    line();
                    System.out.println(replyPrefix + "I don't know what you're talking about.");
                    line();
                    awaitReply();
                }
                try {
                    int index = Integer.parseInt(tokens[1]);
                    taskList.deleteTask(index);
                    awaitReply();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    line();
                    System.out.println(replyPrefix + "Which one do you want to delete exactly?");
                    line();
                    awaitReply();
                }
            }

            case "find": {
                list.find(tokens);
                awaitReply();
                break;
            }
            
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
    public static void line() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
