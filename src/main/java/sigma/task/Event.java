package sigma.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sigma.exception.NoEventTimeException;
import sigma.exception.NoTaskNameException;
import sigma.exception.SigmaException;
import sigma.exception.WrongDateTimeFormatException;

public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String startDate;
    private String endDate;

    public Event(String taskName, boolean isDone, String startDate, String endDate) {
        super(taskName, isDone, "E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        LocalDateTime dateTimeStartDate = LocalDateTime.parse(this.startDate.substring(1), FORMATTER);
        LocalDateTime dateTimeEndDate = LocalDateTime.parse(this.endDate.substring(1), FORMATTER);
        String dateTimeStartDateString = dateTimeStartDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String dateTimeEndDateString = dateTimeEndDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName() + " (From: " + dateTimeStartDateString + " -- To: " + dateTimeEndDateString + ")";
    }
}
