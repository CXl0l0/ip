import java.io.IOException;
import java.util.Scanner;

public class Ui {
    protected static String name = "Sigma";
    protected static String replyPrefix = name + ": ";

    private Scanner sc;
    private TaskList list;

    public Ui() throws IOException {
        this.list = new TaskList();
        this.sc = new Scanner(System.in);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void greet() {
        line();
        System.out.println(replyPrefix + "What's up, I'm " + name);
        System.out.println(replyPrefix + "What do you want?");
        line();
    }
    
    private void exit() {
        line();
        System.out.println(replyPrefix + "Bye.");
        line();
        System.exit(0);
    }
    
    public void awaitReply() throws SigmaException, IOException {
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
            list.showList();
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
                    list.markDone(index);
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
                    list.markUndone(index);
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
                list.addToDo(tokens);
                awaitReply();
                break;
            }
            
            case "deadline": {
                list.addDeadline(tokens);
                awaitReply();
                break;
            }
            
            case "event": {
                list.addEvent(tokens);
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
                    list.deleteTask(index);
                    awaitReply();
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    line();
                    System.out.println(replyPrefix + "Which one do you want to delete exactly?");
                    line();
                    awaitReply();
                }
            }
            
            default:
            //Invalid/Unknown command
            line();
            System.out.println(replyPrefix + "I don't know what you're talking about.");
            line();
            awaitReply();
        }
    }
    
    public void start() throws SigmaException, IOException {
        greet();
        awaitReply();
    }
    
    //Misc
    public static void line() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }
}
