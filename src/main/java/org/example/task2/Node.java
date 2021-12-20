package org.example.task2;

import lombok.Data;

import java.util.Optional;

@Data
public class Node<T, K> implements Comparable<Node<T, K>> {
    public Node(boolean isSentinel) {
        this.isSentinel = isSentinel;
    }

    private Node above;
    private Node below;
    private boolean isSentinel;
    private K key;
    private T value;

    public T get() {
        return value;
    }

    @Override
    public int compareTo(Node<T, K> o) {
        return Integer.valueOf(hashCode()).compareTo(Optional.ofNullable(o)
                .map(Object::hashCode)
                .orElse(0));
    }
}
