package sigma.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sigma.exception.NoEventTimeException;
import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.exception.WrongDateTimeFormatException;

//CHECKSTYLE.OFF: Regexp
/**
 * A subset of the class "Task" which represents tasks with a period of effect.
 * 2 additional field which stores the start date of the event and end date of the
 * event.
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String startDate;
    private String endDate;

    /**
     * Returns the Event object.
     *
     * @param taskName The name of the task.
     * @param startDate The starting date of the task.
     * @param endDate The ending date of the task.
     * @throws SigmaException If there are missing information or wrong date format.
     */
    public Event(String taskName, String startDate, String endDate) throws SigmaException {
        super(taskName, "E");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }
        
        if (startDate.equals("") || endDate.equals("")) {
            throw new NoEventTimeException();
        }
        
        //To check if entered date and time is in the correct format
        try {
            LocalDateTime.parse(startDate.substring(1), FORMATTER);
            LocalDateTime.parse(endDate.substring(1), FORMATTER);
        } catch (DateTimeException e) {
            throw new WrongDateTimeFormatException();
        }
        
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    /**
     * Returns an Event object by taking in 1 extra argument 
     * which indicates the marked/unmarked state of the task. 
     * For internal use only (eg: Creating event objects
     * by reading data files).
     *
     * @param taskName The name of the task.
     * @param isDone The state of completion of the task.
     * @param startDate The starting date of the task.
     * @param endDate The ending date of the task.
     */
    public Event(String taskName, boolean isDone, String startDate, String endDate) {
        super(taskName, isDone, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the starting date of the event object.
     *
     * @return The starting date of the event.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the ending date of the event object.
     *
     * @return The ending date of the event.
     */
    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        assert startDate != null;
        assert endDate != null;
        LocalDateTime dateTimeStartDate = LocalDateTime.parse(this.startDate.substring(1), FORMATTER);
        LocalDateTime dateTimeEndDate = LocalDateTime.parse(this.endDate.substring(1), FORMATTER);
        String dateTimeStartDateString = dateTimeStartDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String dateTimeEndDateString = dateTimeEndDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName() 
                + " (From: " + dateTimeStartDateString + " -- To: " + dateTimeEndDateString + ")";
    }
}
