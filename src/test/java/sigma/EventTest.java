package sigma.task;

//CHECKSTYLE.OFF: CustomImportOrder
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import sigma.exception.SigmaException;

//CHECKSTYLE.OFF: Regexp
//CHECKSTYLE.OFF: MethodName

public class EventTest {
    @Test
    public void constructor_InvalidStartDateEndDate_throwInvalidEventPeriodException() {
        try {
            String taskName = "test";
            String startDate = " 2020-10-10 0000";
            String endDate = " 2019-10-10 0000";
            Event event = new Event("test", startDate, endDate);
        } catch (SigmaException e) {
            assertEquals("The event's start date can't be after the event's end date yo!"
                            , e.getMessage());
        }
    }

    @Test
    public void setStartDate_InvalidStartDate_throwInvalidEventPeriodException() {
        try {
            String taskName = "test";
            String startDate = " 2019-10-10 0000";
            String endDate = " 2019-10-10 0001";
            Event event = new Event("test", startDate, endDate);

            String invalidStartDate = " 2019-10-11 0000";
            event.setStartDate(invalidStartDate);
        } catch (SigmaException e) {
            assertEquals("The event's start date can't be after the event's end date yo!"
                            , e.getMessage());
        }
    }

    @Test
    public void setEndDate_InvalidEndDate_throwInvalidEventPeriodException() {
        try {
            String taskName = "test";
            String startDate = " 2019-10-10 0000";
            String endDate = " 2019-10-10 0001";
            Event event = new Event("test", startDate, endDate);

            String invalidEndDate = " 2019-10-09 0000";
            event.setStartDate(invalidEndDate);
        } catch (SigmaException e) {
            assertEquals("The event's start date can't be after the event's end date yo!"
                            , e.getMessage());
        }
    }
}
