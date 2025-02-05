import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;

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
            DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HHmm");

            LocalDateTime.parse(date.substring(1), formatter);
        } catch (DateTimeException e) {
            throw new WrongDateTimeFormatException();
        }

        this.by = date;
    }

    public Deadline(String taskName, boolean done, String date) {
        super(taskName, done, "D");
        this.by = date;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HHmm");

        LocalDateTime dateTime = LocalDateTime.parse(this.by.substring(1), formatter);
        String dateTimeString = dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[D]" + "[" + (this.getIsDone() ? "X" : " ") + "]" + this.getTaskName() + " (By: " + dateTimeString + ")";
    }
}
