
import java.util.ArrayList;
import java.util.Scanner;

public class Sigma {
    //Names
    private String name = "Sigma";
    private String reply_prefix = name + ": ";
    
    private void set_Name(String name) {
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
    private void show_list() {
        line();
        System.out.println(reply_prefix + "Stop slacking and lock in.");
        for (int i = 0; i < list.size(); i++) {
            Task todo = list.get(i);
            System.out.println((i + 1) + ". "+ todo.toString());
        }
        line();
        await_reply();
    }

    private void add_list(String todo) {
        line();
        Task task = new Task(todo);
        list.add(task);
        System.out.println(reply_prefix + "Aight, I will remember that for you.");
        System.out.println("added: " + task.toString());
        line();
        await_reply();
    }

    private void add_todo(String[] tokens) {
        line();

        String taskName = "";
        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens[i] + " ";
        }

        ToDo todo = new ToDo(taskName);
        list.add(todo);

        System.out.println(reply_prefix + "Aight, I will remember that for you.");
        System.out.println("added: " + todo.toString());
        System.out.println("Now you have " + list.size() + " task(s) in the list!");

        line();
        await_reply();
    }

    private void add_deadline(String[] tokens) {
        line();

        String taskName = "";
        String date = "";
        boolean reading_date = false;
        for (int i = 1; i < tokens.length; i++) {
            String str = tokens[i];
            if (reading_date) {
                date += " " + str;
            } else if (str.equals("/by")) {
                reading_date = true;
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
        await_reply();
    }

    private void add_event(String[] tokens) {
        line();

        String taskName = "";
        String from = "";
        String to = "";
        boolean reading_from = false;
        boolean reading_to = false;
        for (int i = 1; i < tokens.length; i ++) {
            String str = tokens[i];
            if (str.equals("/from")) {
                reading_from = true;
                reading_to = false;
                continue;
            } else if (str.equals("/to")) {
                reading_from = false;
                reading_to = true;
                continue;
            } else if (reading_from) {
                from += " " + str;
            } else if (reading_to) {
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
        await_reply();
    }

    private void mark_done(int i) {
        Task todo = list.get(i - 1);
        todo.setDone(true);
        line();
        System.out.println("Good job bro, keep the grind going!");
        System.out.println(todo);
        line();
        await_reply();
    }

    private void mark_undone(int i) {
        Task todo = list.get(i - 1);
        todo.setDone(false);
        line();
        System.out.println("Come on bruh, focus!");
        System.out.println(todo);
        line();
        await_reply();
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

    private void await_reply() {
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
                show_list();
                break;
            case "mark": {
                int index = Integer.parseInt(tokens[1]);
                mark_done(index);
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(tokens[1]);
                mark_undone(index);
                break;
            }
            case "todo": {
                add_todo(tokens);
                break;
            }
            case "deadline": {
                add_deadline(tokens);
                break;
            }
            case "event": {
                add_event(tokens);
                break;
            }
            default:
                line();
                System.out.println(reply_prefix+ "I don't know what you're talking about, tell me what you want.");
                line();
                await_reply();
        }
    }

    private void start() {
        greet();
        await_reply();
    }

    //Misc
    private void line() {
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma();
        sigma.start();
    }
}
