package Homework3.AdditrionalTasks;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyOneSideLinkedList<E> implements OneSideLinkedList<E> {

    protected Node<E> firstElement;
    protected Node<E> lastElement;
    protected int size;

    @Override
    public void add(E value) {
        if (isEmpty()) {
            firstElement = new Node<>(value, null);
            lastElement = firstElement;
        } else {
            Node<E> current = new Node<>(value, null);
            lastElement.next = current;
            lastElement = current;
        }
        size++;

    }

    @Override
    public E remove(int position) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }
        Node<E> current;
        if (position == 0) {
            current = firstElement;
            firstElement = current.next;
            current.next = null;

        } else if (position > 0 && position < size - 1) {
            current = firstElement;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        } else if (position == size - 1) {
            current = firstElement;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            lastElement = current;
            lastElement.next = null;
        } else {
            throw new NoSuchElementException("Данный индекс за переделами размера списка");
        }
        size--;
        return current.item;
    }


    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        System.out.println(this);
    }

    private class LinkedListIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return firstElement != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<E> next = firstElement;
            firstElement = firstElement.next;
            return next.item;
        }

    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = firstElement;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(" -> ");
            }

            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }


}
