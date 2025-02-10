package sigma.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.Task;
import sigma.task.ToDo;

//CHECKSTYLE.OFF: Regexp
/**
 * Represents an object that is responsible for reading the data files from the hard disk and sending
 * the data stored to other classes that benefits from it.
 */
public class Storage {
    private static File myFile = new File(".\\data\\Sigma.txt");
    private static final String SPLIT = "//split//";

    public Storage() {
        myFile.getParentFile().mkdirs();
    }

    /**
     * Saves the tasks list's information into the local hard disk.
     *
     * @param list The task list that is being saved.
     */
    public void writeTasks(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(myFile);
            
            //Write each tasks to file
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskName = task.getTaskName();
                String taskType = task.getTaskType();
                boolean isDone = task.getIsDone();
    
                switch (taskType) {
                case "T":
                    fw.write(taskType + SPLIT + isDone + SPLIT + taskName);
                    break;
                
                case "D":
                    String by = ((Deadline) task).getBy();
                    fw.write(taskType + SPLIT + isDone + SPLIT + taskName + SPLIT + by);
                    break;
    
                case "E":
                    String from = ((Event) task).getStartDate();
                    String to = ((Event) task).getEndDate();
                    fw.write(taskType + SPLIT + isDone + SPLIT + taskName + SPLIT + from + SPLIT + to);
                    break;

                default:
                    break;
                }


    
                if (i < list.size() - 1) { 
                    //Don't write a new line for the last list
                    fw.write("\n");
                }
            }
    
            //Close file writer
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads and interprets the saved tasks list's data from the local hard disk
     * and returns the compiled information as an ArrayList Task object.
     * 
     * @return An array list of Task objects.
     */
    public ArrayList<Task> readTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        //Read all the data from data file
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(SPLIT);
                String taskType = data[0];
                boolean isDone = data[1].equals("true");
                String taskName = data[2];

                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(taskName, isDone);
                    tasks.add(todo);
                    break;
                    
                case "D":
                    String by = data[3];
                    Deadline deadline = new Deadline(taskName, isDone, by);
                    tasks.add(deadline);
                    break;
        
                case "E":
                    String from = data[3];
                    String to = data[4];
                    Event event = new Event(taskName, isDone, from, to);
                    tasks.add(event);
                    break;

                default:
                    System.out.println("File corrupted.");
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            //Just return empty list then since no data file is found
            return tasks;
        } 

        return tasks;
    }
}
