package org.example.task3;

import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@NoArgsConstructor
public class Queue<V> {
    private Node<V> dummy = new Node<>(null, new AtomicReference<>(null));
    private AtomicReference<Node<V>> head = new AtomicReference<>(dummy);
    private AtomicReference<Node<V>> tail = new AtomicReference<>(dummy);

    public V pop() {
        while (true) {
            AtomicReference<Node<V>> oldHead = head;
            AtomicReference<Node<V>> oldTail = this.tail;
            AtomicReference<Node<V>> oldNext = oldHead.get().getNext();
            if (oldHead == oldTail) {
                if (oldNext.get() == null) {
                    throw new RuntimeException();
                } else {
                    this.tail.compareAndSet(oldTail.get(), oldNext.get());
                }
            } else {
                Node<V> result = oldNext.get();
                if (head.compareAndSet(oldHead.get(), oldNext.get())) {
                    return result.getValue();
                }
            }
        }
    }

    public void push(V value) {
        Node<V> newTail = new Node<>(value, new AtomicReference<>(null));
        while (true) {
            AtomicReference<Node<V>> tail = this.tail;
            if (tail.get().getNext().compareAndSet(null, newTail)) {
                this.tail.compareAndSet(tail.get(), newTail);
                return;
            } else {
                if (this.tail.compareAndSet(tail.get(), tail.get().getNext().get())) {
                    break;
                }
            }
        }
    }
}
