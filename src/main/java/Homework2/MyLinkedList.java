package Homework2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements LinkedList<E> {

    protected Node<E> firstElement;
    protected Node<E> lastElement;
    protected int size;

    @Override
    public void addFirst(E value) {
        firstElement = new Node<>(value, firstElement);
        if (isEmpty()) {
            lastElement = firstElement;
        }
        size++;
    }

    @Override
    public void add(E value) {
        if (isEmpty()) {
            firstElement = new Node<>(value, firstElement);
            lastElement = firstElement;
        } else {
            lastElement = new Node<>(value, null, lastElement);
            lastElement.previous.next = lastElement;
        }
        size++;
    }

    @Override
    public void add(E value, int position) {
        if (position == 0 || isEmpty()) {
            addFirst(value);
            size--;
        } else if (position <= size / 2 && position > 0) {
            Node<E> current = firstElement;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
            Node<E> insertion = new Node<>(value, current, current.previous);
            current.previous.next = insertion;
            current.previous = insertion;
        } else if (position > size / 2 && position < size) {
            Node<E> current = lastElement;
            for (int i = size - 1; i > position; i--) {
                current = current.previous;
            }
            Node<E> insertion = new Node<>(value, current, current.previous);
            current.previous.next = insertion;
            current.previous = insertion;
        } else if (position == size) {
            add(value);
            size--;
        } else {
            throw new NoSuchElementException("Данный индекс за переделами размера списка");
        }
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> removedNode = firstElement;
        firstElement = removedNode.next;
        removedNode.next = null;
        firstElement.previous = null;
        size--;
        return removedNode.item;
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;
        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        } else if (current == firstElement) {
            removeFirst();
            return true;
        } else {
            previous.next = current.next;
        }

        current.next = null;
        size--;
        return true;
    }

    @Override
    public E removeByIndex(int position) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }
        Node<E> current;
        if (position == 0) {
            current = firstElement;
            removeFirst();
            size++;
        } else if (position <= size / 2 && position > 0) {
            current = firstElement;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
        } else if (position > size / 2 && position < size - 1) {
            current = lastElement;
            for (int i = size - 1; i > position; i--) {
                current = current.previous;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
        } else if (position == size - 1) {
            current = lastElement;
            current.previous.next = null;
            lastElement = current.previous;
            current.previous = null;
        } else {
            throw new NoSuchElementException("Данный индекс за переделами размера списка");
        }
        size--;
        return current.item;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }


    @Override
    public E getFirst() {
        return getValue(firstElement);
    }

    @Override
    public E get(int position) {
        if (isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }
        Node<E> current;
        if (position == 0) {
            current = firstElement;
        } else if (position <= size / 2 && position > 0) {
            current = firstElement;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
        } else if (position > size / 2 && position <= size - 1) {
            current = lastElement;
            for (int i = size - 1; i > position; i--) {
                current = current.previous;
            }
        } else {
            throw new NoSuchElementException("Данный индекс за переделами размера списка");
        }
        return current.item;
    }

    protected E getValue(Node<E> value) {
        return value != null ? value.item : null;
    }

    @Override
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


    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
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

}
