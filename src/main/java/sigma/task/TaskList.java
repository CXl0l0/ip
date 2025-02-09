package sigma.task;

import java.util.ArrayList;

import sigma.command.Ui;
import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.storage.Storage;

/**
 * Represents the list which stores the current recorded tasks whether by user input or
 * from data files. Tasks included in this object are: To Do tasks, Deadline tasks and
 * Event tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage store;
    private String replyPrefix;

    /**
     * Constructor of TaskList object. Intialized
     * by calling the constructors of storage for reading data files.
     */
    public TaskList() {
        this.store = new Storage();
        this.list = store.readTasks();
        this.replyPrefix = Ui.getReplyPrefix();
    }

    /**
     * A getter method for getting the list of tasks recorded by this object.
     * 
     * @return An array list of Tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * A getter method for getting the task in the list based on the index.
     * 
     * @param i The index of the task requested.
     * @return Task object requested.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * A getter method for getting the list's current size.
     * 
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }
    
    /**
     * Add the indicated To Do task into TaskList. 
     * 
     * @param taskName The name of the task.
     * @throws NoTaskNameException If taskName is empty.
     * @return The ToDo task created.
     */
    public ToDo addToDo(String taskName) throws NoTaskNameException {
        try {
            ToDo todo = new ToDo(taskName);
            list.add(todo);
            store.writeTasks(list);
            return todo;
        } catch (NoTaskNameException e) {
            throw e;
        }
    }
    
    /**
     * Add the indicated Deadline task into TaskList. 
     * 
     * @param taskName The name of the task.
     * @param date The deadline date of the task.
     * @throws SigmaException If the inputs are erroneous.
     * @return The deadline task created.
     */
    public Deadline addDeadline(String taskName, String date) throws SigmaException {
        try {
            Deadline deadline = new Deadline(taskName, date);
            list.add(deadline);
            store.writeTasks(list);
            return deadline;
        } catch (SigmaException e) {
            throw e;
        }
    }
    
    /**
     * Add the indicated Event task into TaskList. 
     * 
     * @param TaskName The name of the task.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     * @throws SigmaException If the inputs are erroneous.
     * @return The event task created.
     */
    public Event addEvent(String taskName, String from, String to) throws SigmaException {
        try {
            Event event = new Event(taskName, from, to);
            list.add(event);
            store.writeTasks(list);
            return event;
        } catch (SigmaException e) {
            throw e;
        }
    }
    
    /**
     * Mark the indicated task according 
     * to the index in TaskList as done.
     * 
     * @param i The index of the task to be marked.
     * @throws IndexOutOfBoundsException If entered an invalid index to mark.
     */
    public void markDone(int i) throws IndexOutOfBoundsException {
        try {
            Task todo = list.get(i - 1);
            todo.setDone(true);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }

        store.writeTasks(list);
    }
    
    /**
     * Mark the indicated task according 
     * to the index in TaskList as not done.
     * 
     * @param i The index of the task to be unmarked.
     * @throws IndexOutOfBoundsException If entered an invalid index to unmark.
     */
    public void markUndone(int i) throws IndexOutOfBoundsException {
        try {
            Task todo = list.get(i - 1);
            todo.setDone(false);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }

        store.writeTasks(list);
    }
    
    /**
     * Delete the indicated task according 
     * to the index in TaskList.
     * 
     * @param i The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If entered an invalid index to delete.
     */
    public Task deleteTask(int i) throws IndexOutOfBoundsException {
        try {
            Task task = list.get(i - 1);
            list.remove(i - 1);
            store.writeTasks(list);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }

    }

    /**
     * Find tasks in TaskList that fits the keyword
     * inputted by the user.
     * 
     * @param finalKeyword The keyword to search in the task list.
     * @return The array list of matching tasks.
     */
    public ArrayList<Task> find(String finalKeyword) {
        ArrayList<Task> matchingTasks = (ArrayList<Task>) list.clone();
        matchingTasks.removeIf((task) -> !task.getTaskName().contains(finalKeyword));
        return matchingTasks;
    }
}
