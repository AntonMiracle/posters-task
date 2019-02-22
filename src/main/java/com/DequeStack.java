package com;


import java.util.ArrayDeque;
import java.util.Deque;

public class DequeStack<T> implements Stack<T> {
    private final Deque<T> deque = new ArrayDeque<T>();

    @Override
    public void push(T object) {
        deque.addFirst(object);
    }

    @Override
    public T pop() {
        return deque.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    @Override
    public T peek() {
        return deque.getFirst();
    }

    @Override
    public int size() {
        return deque.size();
    }
}
