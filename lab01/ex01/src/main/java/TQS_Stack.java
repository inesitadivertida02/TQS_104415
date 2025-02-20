import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TQS_Stack<T> {

    private LinkedList<T> collection;

    // Constructor
    public TQS_Stack() {
        collection = new LinkedList<>();
    }

    // Push an item onto the stack
    public void push(T item) {
        collection.addFirst(item);
    }

    // Pop the top item and remove it from the stack
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return collection.removeFirst();
    }

    // Peek at the top item without removing it
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return collection.getFirst();
    }

    // Get the size of the stack
    public int size() {
        return collection.size();
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    // Pop the nth item from the top, discarding the (n-1) above it
    public T popTopN(int n) {
        if (n > size()) {
            throw new NoSuchElementException("Not enough elements in stack");
        }
        for (int i = 1; i < n; i++) {
            pop();
        }
        return pop();
    }
}