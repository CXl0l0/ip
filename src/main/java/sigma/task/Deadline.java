package sigma.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sigma.exception.NoDeadlineException;
import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.exception.WrongDateTimeFormatException;

//CHECKSTYLE.OFF: Regexp
/**
 * A subset of the class "Task" which represents tasks with deadline. An additional 'by' field
 * is added to indicate the deadline of the task indicated.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String by;

    /**
     * Returns a Deadline object.
     *
     * @param taskName The name of the task.
     * @param date The deadline of the task.
     * @return Deadline object.
     * @throws SigmaException If there are missing information or wrong date format.
     */
    public Deadline(String taskName, String date) throws SigmaException {
        super(taskName, "D");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }

        if (date.equals("")) {
            throw new NoDeadlineException();
        }
        
        //To check if entered date and time is in the correct format
        try {
            LocalDateTime.parse(date.substring(1), FORMATTER);
        } catch (DateTimeException e) {
            throw new WrongDateTimeFormatException();
        }

        this.by = date;
    }

    /**
     * Returns a Deadline object by taking in an extra argument 
     * which indicates the marked/unmarked state of the task. 
     * For internal use only (eg: Creating deadline objects
     * by reading data files).
     *
     * @param taskName The name of the task.
     * @param isDone The state of completion of the task.
     * @param date The deadline of the task.
     */
    public Deadline(String taskName, boolean isDone, String date) {
        super(taskName, isDone, "D");
        this.by = date;
    }

    /**
     * Returns the deadline date of this deadline object.
     *
     * @return The deadline date.
     */
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        LocalDateTime dateTime = LocalDateTime.parse(this.by.substring(1), FORMATTER);
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[D]" + "[" + (this.getIsDone() ? "X" : " ") + "]" 
                + this.getTaskName() + " (By: " + dateTimeString + ")";
    }
}
