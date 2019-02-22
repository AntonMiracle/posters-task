package com;

public interface Stack<T> {
    void push(T object);

    T pop();

    boolean isEmpty();

    T peek();

    int size();
}
