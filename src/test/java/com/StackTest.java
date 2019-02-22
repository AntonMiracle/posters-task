package com;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StackTest {

    @Test
    public void dequeStack() {
        Stack<Integer> stack = new DequeStack<>();
        assertThat(stack.isEmpty()).isTrue();
        stack.push(1);
        stack.push(3);

        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.size() == 2).isTrue();
        assertThat(stack.peek()).isEqualTo(Integer.valueOf(3));
        assertThat(stack.pop()).isEqualTo(Integer.valueOf(3));
        assertThat(stack.pop()).isEqualTo(Integer.valueOf(1));
        assertThat(stack.isEmpty()).isTrue();
    }
}