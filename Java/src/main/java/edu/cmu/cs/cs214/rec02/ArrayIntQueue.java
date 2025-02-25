package edu.cmu.cs.cs214.rec02;

import java.util.Arrays;

/**
 * A resizable-array implementation of the {@link IntQueue} interface. The head of
 * the queue starts out at the head of the array, allowing the queue to grow and
 * shrink in constant time.
 *
 * TODO: This implementation contains three bugs! Use your tests to determine the
 * source of the bugs and correct them!
 *
 * @autho Alex Lockwood
 * @autho Ye Lu
 */
public class ArrayIntQueue implements IntQueue {

    private Integer[] elementData;
    private int head;
    private int size;
    private static final int INITIAL_SIZE = 10;

    public ArrayIntQueue() {
        elementData = new Integer[INITIAL_SIZE];
        head = 0;
        size = 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
        head = 0;
    }


    @Override
    public Integer dequeue() {
        if (isEmpty()) return null;
        Integer value = elementData[head];
        elementData[head] = null;
        head = (head + 1) % elementData.length;
        size--;
        if (size == 0) {
            clear(); // Queue хоосорсон үед clear() дуудаж дахин тохируулна
        }
        return value;
    }


    @Override
    public boolean enqueue(Integer value) {
        
        ensureCapacity();
        int tail = (head + size) % elementData.length;
        elementData[tail] = value;
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData[head];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Increases the capacity of this <tt>ArrayIntQueue</tt> instance, if
     * necessary, to ensure that it can hold at least size + 1 elements.
     */
    private void ensureCapacity() {
        if (size == elementData.length) {
            int newCapacity = elementData.length * 2 + 1;
            Integer[] newData = new Integer[newCapacity];
    
            for (int i = 0; i < size; i++) {
                newData[i] = elementData[(head + i) % elementData.length]; // Corrected indexing
            }
    
            elementData = newData;
            head = 0; // Reset head to 0 after resizing
        }
    }    
}
