import java.util.ArrayList;
import java.util.Scanner;

public class Sigma {
    //Names
    private String name = "Sigma";
    private String reply_prefix = name + ": ";
    
    private void setName(String name) {
        this.name = name;
    }
    
    public Sigma() {
        sc = new Scanner(System.in);
        list = new ArrayList<>();
    }

    //Scanner
    private Scanner sc;

    //List
    ArrayList<Task> list;
    private void showList() throws SigmaException {
        line();
        if (list.size() == 0) {
            System.out.println(reply_prefix + "The list is empty right now.");
        } else {
            System.out.println(reply_prefix + "Stop slacking and lock in.");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". "+ task.toString());
            }
        }
        line();
        awaitReply();
    }

    private void addToDo(String[] tokens) throws SigmaException {
        line();

        String taskName = "";
        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens[i] + " ";
        }

        try {
            ToDo todo = new ToDo(taskName);
            list.add(todo);
    
            System.out.println(reply_prefix + "Aight, I will remember that for you.");
            System.out.println("added: " + todo.toString());
            System.out.println("Now you have " + list.size() + " task(s) in the list!");
    
            line();
            awaitReply();
        } catch (NoTaskNameException e) {
            System.out.println(reply_prefix + e.getMessage());
            line();
            awaitReply();
        }
    }

    private void addDeadline(String[] tokens) throws SigmaException {
        line();

        try {
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
                } else {
                    taskName += " " + str;
                }
            }
    
            Deadline deadline = new Deadline(taskName, date);
            list.add(deadline);
    
            System.out.println(reply_prefix + "Aight, I will remember that for you.");
            System.out.println("added: " + deadline.toString());
            System.out.println("Now you have " + list.size() + " task(s) in the list!");
            
            line();
            awaitReply();
        } catch (SigmaException e) {
            System.out.println(reply_prefix + e.getMessage());
            line();
            awaitReply();
        }
    }

    private void addEvent(String[] tokens) throws SigmaException {
        line();

        try {
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
                } else {
                    taskName += " " + str;
                }
            }
    
            Event event = new Event(taskName, from, to);
            list.add(event);
    
            System.out.println(reply_prefix + "Aight, I will remember that for you.");
            System.out.println("added: " + event.toString());
            System.out.println("Now you have " + list.size() + " task(s) in the list!");
    
            line();
            awaitReply();
        } catch (SigmaException e) {
            System.out.println(reply_prefix + e.getMessage());
            line();
            awaitReply();
        }

    }

    private void markDone(int i) throws SigmaException {
        try {
            Task todo = list.get(i - 1);
            todo.setDone(true);
            line();
            System.out.println("Good job bro, keep the grind going!");
            System.out.println(todo);
            line();
            awaitReply();
        } catch (IndexOutOfBoundsException e) {
            line();
            System.out.println(reply_prefix + "Enter a valid task number. There probably ain't even any tasks to mark you bum.");
            line();
            awaitReply();
        }
    }

    private void markUndone(int i) throws SigmaException {
        try {
            Task todo = list.get(i - 1);
            todo.setDone(false);
            line();
            System.out.println("Come on bruh, focus!");
            System.out.println(todo);
            line();
            awaitReply();
        } catch (IndexOutOfBoundsException e) {
            line();
            System.out.println(reply_prefix + "Enter a valid task number. There probably ain't even any tasks to unmark you bum.");
            line();
            awaitReply();
        }
    }

    private void deleteTask(int i) throws SigmaException {
        try {
            Task task = list.get(i - 1);
            list.remove(i - 1);
            line();
            System.out.println("I've removed this for you bud.\n" + i + ". " + task.toString());
            System.out.println("You've got " + list.size() + " task(s) left.");
            line();
            awaitReply();
        } catch (IndexOutOfBoundsException e) {
            line();
            System.out.println(reply_prefix + "Enter a valid task number. There probably ain't even any tasks to delete you bum.");
            line();
            awaitReply();
        }
    }

    //Dialogue functions
    private void greet() {
        line();
        System.out.println(reply_prefix + "What's up, I'm " + name);
        System.out.println(reply_prefix + "What do you want?");
        line();
    }

    private void exit() {
        line();
        System.out.println(reply_prefix + "Bye.");
        line();
        System.exit(0);
    }

    private void awaitReply() throws SigmaException {
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
            showList();
            break;

        case "mark": {
            if (tokens.length > 2) { //unknown elements after command
                line();
                System.out.println(reply_prefix + "I don't know what you're talking about.");
                line();
                awaitReply();
            }

            try {
                int index = Integer.parseInt(tokens[1]);
                markDone(index);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                line();
                System.out.println(reply_prefix + "Which one do you want to mark exactly?");
                line();
                awaitReply();
            }
        }

        case "unmark": {
            if (tokens.length > 2) { //unknown elements after command
                line();
                System.out.println(reply_prefix + "I don't know what you're talking about.");
                line();
                awaitReply();
            }

            try {
                int index = Integer.parseInt(tokens[1]);
                markUndone(index);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                line();
                System.out.println(reply_prefix + "Which one do you want to unmark exactly?");
                line();
                awaitReply();
            }
        }

        case "todo": {
            addToDo(tokens);
            break;
        }

        case "deadline": {
            addDeadline(tokens);
            break;
        }

        case "event": {
            addEvent(tokens);
            break;
        }

        case "delete": {
            if (tokens.length > 2) { //unknown elements after command
                line();
                System.out.println(reply_prefix + "I don't know what you're talking about.");
                line();
                awaitReply();
            }
            try {
                int index = Integer.parseInt(tokens[1]);
                deleteTask(index);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                line();
                System.out.println(reply_prefix + "Which one do you want to delete exactly?");
                line();
                awaitReply();
            }
        }
        
        default:
        //Invalid/Unknown command
            line();
            System.out.println(reply_prefix + "I don't know what you're talking about.");
            line();
            awaitReply();
        }
    }

    private void start() throws SigmaException {
        greet();
        awaitReply();
    }

    //Misc
    private void line() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) throws SigmaException {
        Sigma sigma = new Sigma();
        sigma.start();
    }
}
