
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
            System.err.println((i + 1) + ". "+ todo.toString());
        }
        line();
        await_reply();
    }

    private void add_list(String todo) {
        line();
        Task task = new Task(todo);
        list.add(task);
        System.out.println(reply_prefix + "Aight, I will remember that for you.");
        System.out.println("added: " + task.getTaskName());
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
        System.out.print("You: ");
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
            default:
                add_list(reply);
        }
    }

    private void start() {
        greet();
        await_reply();
    }

    //Misc
    private void line() {
        System.out.println("----------------------------------------------------------");
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma();
        sigma.start();
    }
}
