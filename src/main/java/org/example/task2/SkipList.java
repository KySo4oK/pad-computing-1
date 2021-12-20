package org.example.task2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SkipList<T, K> {

    List<LinkedList<Node<T, K>>> levels;

    public SkipList() {
        this.levels = new ArrayList<>();
        levels.add(getLinkedList());
        levels.add(getLinkedList());
    }

    private LinkedList<Node<T, K>> getLinkedList() {
        LinkedList<Node<T, K>> nodes = new LinkedList<>();
        nodes.set(0, new Node<T, K>(true));
        return nodes;
    }

    Optional<T> search(K key, LinkedList<Node<T, K>> highestLevel) {
        return search(key, getHighestLevel());
    }

    Optional<T> auxSearch(K key, LinkedList<Node<T, K>> highestLevel) {

//        Node<T, K> first = highestLevel.get(0);
//        switch (first.getKey().compareTo(key)) {
//            case 0 : return Optional.ofNullable(first.getValue());
//            case 1 : return Optional.empty();
//            case -1 : return search(highestLevel.subList(1, highestLevel.size()-1), getHighestLevel())
//        }
//
        return null;
    }

    private LinkedList<Node<T, K>> getHighestLevel() {
        return levels.get(levels.size() - 1);
    }


}
