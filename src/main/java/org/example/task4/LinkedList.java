package org.example.task4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class LinkedList<V> {
    private final AtomicMarkableReference<Node<V>> head = new AtomicMarkableReference<>(null, false);
    private final AtomicMarkableReference<Node<V>> tail = new AtomicMarkableReference<>(null, false);
    private final AtomicInteger size = new AtomicInteger(0);

    public int size() {
        return size.get();
    }

    public void insert(V value) {
        Node<V> newNode = new Node<>(value, new AtomicMarkableReference<>(null, false));
        while (true) {
            if (head.compareAndSet(null, newNode, false, false)) {
                size.incrementAndGet();
                break;
            } else {
                AtomicMarkableReference<Node<V>> last = search(size.get() - 1);
                AtomicMarkableReference<Node<V>> next = last.getReference().getNext();
                if (next.compareAndSet(null, newNode, false, false)) {
                    size.incrementAndGet();
                    break;
                }
            }
        }
    }

    public boolean remove(int index) {
        if (index > size.get() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        AtomicMarkableReference<Node<V>> toRemove;
        while (true) {
            toRemove = search(index);
            Node<V> toRemoveReference = toRemove.getReference();
            toRemove.attemptMark(toRemoveReference, true);
            Node<V> nextRef = toRemoveReference.getNext().getReference();

            if (index != 0) {
                AtomicMarkableReference<Node<V>> prev = search(index - 1);
                if (prev.getReference().getNext().compareAndSet(toRemoveReference, nextRef, true, false)) {
                    size.decrementAndGet();
                    break;
                }
            } else {
                if (head.compareAndSet(toRemoveReference, nextRef, false, false)) {
                    size.decrementAndGet();
                    break;
                }
            }
            toRemove.attemptMark(toRemoveReference, false);
        }
        return true;
    }

    private AtomicMarkableReference<Node<V>> search(int index) {
        AtomicMarkableReference<Node<V>> currentHead = head;
        int currentIndex = 0;
        while (currentIndex < index && currentHead.getReference() != null) {
            currentHead = currentHead.getReference().getNext();
            currentIndex++;
        }
        if (currentIndex == index) {
            return currentHead;
        } else return null;
    }
}
