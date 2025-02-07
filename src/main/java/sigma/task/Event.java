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
    private String from;
    private String to;

    public Event(String taskName, boolean done, String from, String to) {
        super(taskName, done, "E");
        this.from = from;
        this.to = to;
    }

    public Event(String taskName, String from, String to) throws SigmaException {
        super(taskName, "E");
        if (taskName.equals("")) {
            throw new NoTaskNameException();
        }

        if (from.equals("") || to.equals("")) {
            throw new NoEventTimeException();
        }

        //To check if entered date and time is in the correct format
        try {
            LocalDateTime.parse(from.substring(1), FORMATTER);
            LocalDateTime.parse(to.substring(1), FORMATTER);
        } catch (DateTimeException e) {
            throw new WrongDateTimeFormatException();
        }

        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        LocalDateTime dateTimeFrom = LocalDateTime.parse(this.from.substring(1), FORMATTER);
        LocalDateTime dateTimeTo = LocalDateTime.parse(this.to.substring(1), FORMATTER);
        String dateTimeFromString = dateTimeFrom.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String dateTimeToString = dateTimeTo.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName() + " (From: " + dateTimeFromString + " -- To: " + dateTimeToString + ")";
    }
}
