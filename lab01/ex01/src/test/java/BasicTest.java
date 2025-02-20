import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

class BasicTest {

    @Test
    void testStackIsEmptyOnConstruction() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        assertTrue(stack.isEmpty(), "Stack should be empty on construction");
        assertEquals(0, stack.size(), "Stack size should be 0 on construction");
    }

    @Test
    void testPushIncreasesSize() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size(), "Stack size should be 2 after two pushes");
    }

    @Test
    void testPushThenPopReturnsSameValue() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        stack.push(100);
        assertEquals(100, stack.pop(), "Popped value should be the same as pushed value");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping last element");
    }

    @Test
    void testPushThenPeekDoesNotChangeSize() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        stack.push(99);
        assertEquals(99, stack.peek(), "Peeked value should match last pushed value");
        assertEquals(1, stack.size(), "Peek should not change stack size");
    }

    @Test
    void testPoppingUntilEmpty() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
        assertEquals(0, stack.size(), "Stack size should be 0 after popping all elements");
    }

    @Test
    void testPoppingFromEmptyStackThrowsException() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        assertThrows(NoSuchElementException.class, stack::pop, "Popping an empty stack should throw NoSuchElementException");
    }

    @Test
    void testPeekingIntoEmptyStackThrowsException() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        assertThrows(NoSuchElementException.class, stack::peek, "Peeking into an empty stack should throw NoSuchElementException");
    }

    @Test
    void testPopTopN() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(1, stack.popTopN(3), "popTopN should return correct element after removing n-1 elements");
        assertTrue(stack.isEmpty(), "Stack should be empty after popTopN");
    }

    @Test
    void testPopTopNThrowsExceptionIfTooManyRequested() {
        TQS_Stack<Integer> stack = new TQS_Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThrows(NoSuchElementException.class, () -> stack.popTopN(4), "popTopN should throw exception if n > size");
    }
}