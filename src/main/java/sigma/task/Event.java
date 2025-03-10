package sigma.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sigma.exception.InvalidEventPeriodException;
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
        checkValidity(taskName, startDate, endDate);
        checkDateValidity(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Checks the validity of the input of user for
     * taskName, event's start date and end date.
     *  
     * @param taskName The name of the task.
     * @param startDate The starting date of the task.
     * @param endDate The ending date of the task.
     * @throws SigmaException If there are missing information or wrong date format.
     */
    private void checkValidity(String taskName, String startDate, String endDate) throws SigmaException {
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }
        
        if (startDate.equals("") || endDate.equals("")) {
            throw new NoEventTimeException();
        }
    }

    public static void checkDateValidity(String startDate, String endDate) throws SigmaException {
        //To check if entered date and time is in the correct format
        try {
            LocalDateTime ldt1 = LocalDateTime.parse(startDate.substring(1), FORMATTER);
            LocalDateTime ldt2 = LocalDateTime.parse(endDate.substring(1), FORMATTER);
            if (ldt1.compareTo(ldt2) > 0) {
                throw new InvalidEventPeriodException();
            }
        } catch (DateTimeException e) {
            throw new WrongDateTimeFormatException();
        }
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
     * Sets the new event start date.
     * 
     * @param startDate The new event start date.
     * @throws SigmaException If the start date is invalid.
     */
    public void setStartDate(String startDate) throws SigmaException {
        if (!startDate.equals("")) {
            //To check if entered date and time is in the correct format
            try {
                LocalDateTime.parse(startDate.substring(1), FORMATTER);
                checkDateValidity(startDate, this.endDate);
            } catch (DateTimeException e) {
                throw new WrongDateTimeFormatException();
            }
    
            this.startDate = startDate;
        }
    }

    /**
     * Returns the ending date of the event object.
     *
     * @return The ending date of the event.
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the new event end date.
     * 
     * @param endDate The new event end date.
     * @throws SigmaException If the end date is invalid.
     */
    public void setEndDate(String endDate) throws SigmaException {
        if (!endDate.equals("")) {
            //To check if entered date and time is in the correct format
            try {
                LocalDateTime.parse(endDate.substring(1), FORMATTER);
                checkDateValidity(this.startDate, endDate);
            } catch (DateTimeException e) {
                throw new WrongDateTimeFormatException();
            }
    
            this.endDate = endDate;
        }
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
