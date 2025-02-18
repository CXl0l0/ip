package sigma.task;

import java.util.ArrayList;

import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.storage.Storage;

//CHECKSTYLE.OFF: Regexp
/**
 * Represents the list which stores the current recorded tasks whether by user input or
 * from data files. Tasks included in this object are: To Do tasks, Deadline tasks and
 * Event tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage store;

    /**
     * Constructor of TaskList object. Intialized
     * by calling the constructors of storage for reading data files.
     */
    public TaskList() {
        this.store = new Storage();
        this.list = store.readTasks();
    }

    /**
     * Returns the list of tasks recorded by this object.
     * 
     * @return An array list of Tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the task in the list based on the index.
     * 
     * @param i The index of the task requested.
     * @return Task object requested.
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Returns the list's current size.
     * 
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }
    
    /**
     * Adds the indicated To Do task into TaskList. 
     * 
     * @param taskName The name of the task.
     * @return The ToDo task created.
     * @throws NoTaskNameException If taskName is empty.
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
     * Adds the indicated Deadline task into TaskList. 
     * 
     * @param taskName The name of the task.
     * @param date The deadline date of the task.
     * @return The deadline task created.
     * @throws SigmaException If the inputs are erroneous.
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
     * Adds the indicated Event task into TaskList. 
     * 
     * @param taskName The name of the task.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     * @return The event task created.
     * @throws SigmaException If the inputs are erroneous.
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
     * Marks the indicated task according 
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
     * Marks the indicated task according 
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
     * Deletes the indicated task according 
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
     * Finds tasks in TaskList that fits the keyword
     * inputted by the user.
     * 
     * @param finalKeyword The keyword to search in the task list.
     * @return The array list of matching tasks.
     */
    public ArrayList<Task> find(String finalKeyword) {
        assert finalKeyword != null;
        assert finalKeyword != "";
        ArrayList<Task> matchingTasks = (ArrayList<Task>) list.clone();
        matchingTasks.removeIf((task) -> !task.getTaskName().contains(finalKeyword));
        return matchingTasks;
    }

    /**
     * Edits task in TaskList according to the parsed information
     * specified by the user.
     * 
     * @param task The task to be edited.
     * @param parsedInfos The new informations for the edition.
     * @throws SigmaException If there are user input errors.
     */
    public void editTask(Task task, String[] parsedInfos) throws SigmaException {
        String taskType = task.getTaskType();
        switch (taskType) {
            case "T": {
                String newTaskName = parsedInfos[0];
                task.setTaskName(newTaskName);
                break;
            }

            case "D": {
                String newTaskName = parsedInfos[0];
                String newDeadline = parsedInfos[1];
                Deadline deadline = (Deadline) task;
                deadline.setTaskName(newTaskName);
                deadline.setBy(newDeadline);
                break;
            }

            case "E": {
                String newTaskName = parsedInfos[0];
                String newStartDate = parsedInfos[1];
                String newEndDate = parsedInfos[2];
                Event event = (Event) task;

                if (!newStartDate.equals("") && !newEndDate.equals("")) {
                    Event.checkDateValidity(newStartDate, newEndDate);
                }

                event.setTaskName(newTaskName);
                event.setStartDate(newStartDate);
                event.setEndDate(newEndDate);
                break;
            }

            default:
                assert taskType == "T" 
                || taskType == "D"
                || taskType == "E";
            }

        store.writeTasks(list);
    }
}
