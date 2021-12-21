package org.example.task4;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicMarkableReference;

@Data
@NoArgsConstructor
public class Node<V> {
    private V value;
    private AtomicMarkableReference<Node<V>> next;

    public Node(V value, AtomicMarkableReference<Node<V>> next) {
        this.value = value;
        this.next = next;
    }

    public Node(V value) {
        this.value = value;
    }
}
