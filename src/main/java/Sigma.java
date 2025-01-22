
import java.util.ArrayList;
import java.util.Scanner;

public class Sigma {
    //Names
    private String name = "Sigma";
    private String reply_prefix = name + ": ";
    
    private void set_Name(String name) {
        this.name = name;
    }

    //Scanner
    private Scanner sc;

    //List
    ArrayList<String> list;
    private void show_list() {
        line();
        System.out.println(reply_prefix + "Stop slacking and lock in.");
        for (int i = 0; i < list.size(); i++) {
            String todo = list.get(i);
            System.err.println((i + 1) + ". " + todo);
        }
        line();
        await_reply();
    }

    private void add_list(String todo) {
        line();
        list.add(todo);
        System.out.println(reply_prefix + "I will remember that for you.");
        System.out.println("added: " + todo);
        line();
        await_reply();
    }

    public Sigma() {
        sc = new Scanner(System.in);
        list = new ArrayList<>();
    }
    

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
        switch (reply) {
            case "bye":
                exit();
                break;
            case "list":
                show_list();
                break;
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
