package sigma.task;

//CHECKSTYLE.OFF: CustomImportOrder
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import sigma.exception.NoTaskNameException;

//CHECKSTYLE.OFF: Regexp
//CHECKSTYLE.OFF: MethodName
public class ToDoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest() {
        assertEquals(4, 4);
    }

    @Test
    public void constructor_EmptyString_throwNoTaskNameException() {
        try {
            //Try to create to do task object with no input
            ToDo todo = new ToDo("");
        } catch (NoTaskNameException e) {
            assertEquals("What's the task name yo?", e.getMessage());
        }
    }

    @Test
    public void toString_DoHomework_correctToStringOutput() {
        try {
            ToDo todo = new ToDo("Do homework");
            assertEquals("[T][ ] Do homework", todo.toString());
        } catch (NoTaskNameException e) {
            //Should not reach here
            assertEquals(0, 1);
        }
    }
}
