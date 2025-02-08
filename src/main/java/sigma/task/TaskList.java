package sigma.task;

import java.io.IOException;
import java.util.ArrayList;

import sigma.command.Ui;
import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.storage.Storage;

public class TaskList {
    //List
    private ArrayList<Task> list;
    private Storage store;
    private String replyPrefix;

    public TaskList() throws IOException {
        this.store = new Storage();
        this.list = store.readTasks();
        this.replyPrefix = Ui.getReplyPrefix();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void showList() throws SigmaException, IOException {
        Ui.line();
        if (list.size() == 0) {
            System.out.println(replyPrefix + "The list is empty right now.");
        } else {
            System.out.println(replyPrefix + "Stop slacking and lock in.");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". "+ task.toString());
            }
        }
        Ui.line();
    }
    
    public void addToDo(String[] tokens) throws SigmaException, IOException {
        Ui.line();
        
        String taskName = "";
        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens[i] + " ";
        }
        
        try {
            ToDo todo = new ToDo(taskName);
            list.add(todo);
            
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + todo.toString());
            System.out.println("Now you have " + list.size() + " task(s) in the list!");
            
            Ui.line();
        } catch (NoTaskNameException e) {
            System.out.println(replyPrefix + e.getMessage());
            Ui.line();
        }

        store.writeTasks(list);
    }
    
    public void addDeadline(String[] tokens) throws SigmaException, IOException {
        Ui.line();
        
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
            
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + deadline.toString());
            System.out.println("Now you have " + list.size() + " task(s) in the list!");
            
            Ui.line();
        } catch (SigmaException e) {
            System.out.println(replyPrefix + e.getMessage());
            Ui.line();
        }

        store.writeTasks(list);
    }
    
    public void addEvent(String[] tokens) throws SigmaException, IOException {
        Ui.line();
        
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
            
            System.out.println(replyPrefix + "Aight, I will remember that for you.");
            System.out.println("added: " + event.toString());
            System.out.println("Now you have " + list.size() + " task(s) in the list!");
            
            Ui.line();
        } catch (SigmaException e) {
            System.out.println(replyPrefix + e.getMessage());
            Ui.line();
        }
        
        store.writeTasks(list);
    }
    
    public void markDone(int i) throws SigmaException, IOException {
        try {
            Task todo = list.get(i - 1);
            todo.setDone(true);
            Ui.line();
            System.out.println("Good job bro, keep the grind going!");
            System.out.println(todo);
            Ui.line();
        } catch (IndexOutOfBoundsException e) {
            Ui.line();
            System.out.println(replyPrefix + "Enter a valid task number. There probably ain't even any tasks to mark you bum.");
            Ui.line();
        }

        store.writeTasks(list);
    }
    
    public void markUndone(int i) throws SigmaException, IOException {
        try {
            Task todo = list.get(i - 1);
            todo.setDone(false);
            Ui.line();
            System.out.println("Come on bruh, focus!");
            System.out.println(todo);
            Ui.line();
        } catch (IndexOutOfBoundsException e) {
            Ui.line();
            System.out.println(replyPrefix + "Enter a valid task number. There probably ain't even any tasks to unmark you bum.");
            Ui.line();
        }

        store.writeTasks(list);
    }
    
    public void deleteTask(int i) throws SigmaException, IOException {
        try {
            Task task = list.get(i - 1);
            list.remove(i - 1);
            Ui.line();
            System.out.println("I've removed this for you bud.\n" + i + ". " + task.toString());
            System.out.println("You've got " + list.size() + " task(s) left.");
            Ui.line();
        } catch (IndexOutOfBoundsException e) {
            Ui.line();
            System.out.println(replyPrefix + "Enter a valid task number. There probably ain't even any tasks to delete you bum.");
            Ui.line();
        }

        store.writeTasks(list);
    }

    /**
     * 
     * @param tokens
     */
    public void find(String[] tokens) {
        try {
            ArrayList<Task> matchingTasks = (ArrayList<Task>) list.clone();
            String keyword = "";
            for (int i = 1; i < tokens.length; i++) {
                String word = tokens[i];
                keyword += " " + word;
            }
    
            String finalKeyword = keyword.substring(1);
            matchingTasks.removeIf((task) -> !task.getTaskName().contains(finalKeyword));
    
            Ui.line();
            if (matchingTasks.size() == 0) {
                System.out.println(replyPrefix + "Unable to find any tasks matching the description.");
            } else {
                System.out.println(replyPrefix + "These are probably what you're looking for.");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    Task task = matchingTasks.get(i);
                    System.out.println((i + 1) + ". "+ task.toString());
                }
            }
            Ui.line();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.line();
            System.out.println(replyPrefix + "What are you even finding yo?");
            Ui.line();
        }

    }
}
