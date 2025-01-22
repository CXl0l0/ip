
import java.util.Scanner;

public class Sigma {
    private String name = "Sigma";
    private String reply_prefix = name + ": ";
    private Scanner sc;

    public Sigma() {
        sc = new Scanner(System.in);
    }
    
    private void set_Name(String name) {
        this.name = name;
    }

    private void greet() {
        System.out.println(reply_prefix + "What's up, I'm " + name);
        System.out.println(reply_prefix + "What do you want?");
    }

    private void exit() {
        System.out.println(reply_prefix + "Bye.");
    }

    private void await_reply() {
        String reply = sc.nextLine();
        if (reply.equals("bye")) {
            exit();
        } else {
            System.out.println(reply_prefix + reply);
            await_reply();
        }
    }

    private void start() {
        greet();
        await_reply();
    }

    public static void main(String[] args) {
        Sigma sigma = new Sigma();
        sigma.start();
    }
}
