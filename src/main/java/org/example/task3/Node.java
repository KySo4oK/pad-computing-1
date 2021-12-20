package org.example.task3;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@Data
@NoArgsConstructor
public class Node <V>{
    private V value;

    public Node(V value, AtomicReference<Node<V>> next) {
        this.value = value;
        this.next = next;
    }

    private AtomicReference<Node<V>> next;

    public Node(V value) {
        this.value = value;
    }
}
