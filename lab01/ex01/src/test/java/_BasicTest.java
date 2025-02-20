import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class _BasicTest {

    private TQS_Stack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new TQS_Stack<>();
    }

    @Test
    @DisplayName("The stack is empty when created")
    void testIsEmpty() {
        assertTrue(stack.isEmpty(), "Stack should be empty on creation");
        assertEquals(0, stack.size(), "Stack size should be 0 on creation");
    }

    @Test
    @DisplayName("Stack has size 0 when created")
    void testSize() {
        assertEquals(0, stack.size(), "Stack size should be 0 on creation");
    }

    @Test
    @DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n.")
    void testPush() {
        int n = 4;
        for (int i = 0; i < n; i++) {
            stack.push("item" + i);
        }
        assertFalse(stack.isEmpty(), "Stack should not be empty after pushes");
        assertEquals(n, stack.size(), "Stack size should be equal to the number of pushes");
    }

    @Test
    @DisplayName("If one pushes x then pops, the value popped is x.")
    void testPushPop() {
        String x = "item";
        stack.push(x);
        String result = stack.pop();
        assertEquals(x, result, "Popped value should be the same as last pushed");
        assertTrue(stack.isEmpty(), "Stack should be empty after popping last element");
    }

    @Test
    @DisplayName("If one pushes x then peeks, the value returned is x and the size stays the same.")
    void testPushPeek() {
        String x = "item";
        stack.push(x);
        assertEquals(x, stack.peek(), "Peeked value should be the same as last pushed");
        assertEquals(1, stack.size(), "Stack size should remain the same after peek");
    }

    @Test
    @DisplayName("If the size is n, then after n pops, the stack is empty and has size 0.")
    void testPopAll() {
        int n = 3;
        for (int i = 0; i < n; i++) {
            stack.push("item" + i);
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements");
        assertEquals(0, stack.size(), "Stack size should be 0 after popping all elements");
    }

    @Test
    @DisplayName("Popping from an empty stack throws NoSuchElementException")
    void testPopEmpty() {
        assertThrows(NoSuchElementException.class, stack::pop, "Popping an empty stack should throw NoSuchElementException");
    }

    @Test
    @DisplayName("Peeking from an empty stack throws NoSuchElementException")
    void testPeekEmpty() {
        assertThrows(NoSuchElementException.class, stack::peek, "Peeking an empty stack should throw NoSuchElementException");
    }


}