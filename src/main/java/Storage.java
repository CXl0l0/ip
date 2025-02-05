import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static File myFile = new File(".\\data\\Sigma.txt");
    private static final String SPLIT = "//split//";

    public Storage() throws IOException {
        myFile.getParentFile().mkdirs();
    }

    public void writeTasks(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(myFile);
        
        //Write each tasks to file
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String taskName = task.getTaskName();
            String type = task.getType();
            boolean isDone = task.getIsDone();

            switch (type) {
            case "T":
                fw.write(type + SPLIT + isDone + SPLIT + taskName);
                break;
            
            case "D":
                String by = ((Deadline) task).getBy();
                fw.write(type + SPLIT + isDone + SPLIT + taskName + SPLIT + by);
                break;

            case "E":
                String from = ((Event) task).getFrom();
                String to = ((Event) task).getTo();
                fw.write(type + SPLIT + isDone + SPLIT + taskName + SPLIT + from + SPLIT + to);
                break;
            }

            if (i < list.size() - 1) { 
                //Don't write a new line for the last list
                fw.write("\n");
            }
        }

        //Close file writer
        fw.close();
    }

    public ArrayList<Task> readTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        //Read all the data from data file
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(SPLIT);
                String type = data[0];
                boolean isDone = data[1].equals("true");
                String taskName = data[2];

                switch (type) {
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
                    }
            }

        } catch (FileNotFoundException e) {
            //Just return empty list then since no data file is found
            return tasks;
        } 

        return tasks;
    }
}
