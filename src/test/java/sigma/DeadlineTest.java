package sigma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import sigma.exception.SigmaException;
import sigma.exception.WrongDateTimeFormatException;

public class DeadlineTest {
    @Test
    public void constructor_DoHomeworkByDate_correctToStringOutput() throws SigmaException {
        Deadline deadline = new Deadline(" Do homework", " 2025-12-31 0000");
        assertEquals("[D][ ] Do homework (By: Dec 31 2025 00:00)", deadline.toString());
    }

    @Test
    public void constructor_invalidDateFormat_throwWrongDateTimeFormatException() throws SigmaException {
        try {
            Deadline deadline = new Deadline(" Do homework", " Dec 31 2025 0000");
        } catch (WrongDateTimeFormatException e) {
            assertEquals("Invalid format yo, follow this format 'yyyy-MM-dd HHmm'", e.getMessage());
        }
    }
}
