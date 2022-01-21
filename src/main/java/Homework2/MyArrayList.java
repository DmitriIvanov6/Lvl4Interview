package Homework2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements ArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;
    private int size;

    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E value) {
        elementData = ensureCapacity(size, elementData);
        elementData[size++] = value;
    }

    @Override
    public void add(E value, int index) {
        if (indexCheck(index)) {
            elementData = ensureCapacity(size, elementData);
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }

    }

    @Override
    public E remove(int index) {
        E removedItem;
        if (indexCheck(index)) {
            removedItem = (E) elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        } else {
            throw new ArrayIndexOutOfBoundsException("Неверный индекс");
        }
        elementData[--size] = null;
        return removedItem;
    }

    @Override
    public boolean removeObject(E value) {
        int index;
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                index = i;
                remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public void trimToSize() {
        elementData = Arrays.copyOf(elementData, size);
    }


    private Object[] ensureCapacity(int size, Object[] elementData) {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, ((elementData.length * 3) / 2 + 1));
        }
        return elementData;
    }

    boolean indexCheck(int index) {
        return (index <= size && index >= 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {
        int current;

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            int i = current;
            if (i >= size) throw new NoSuchElementException();
            Object[] elementData = MyArrayList.this.elementData;
            current = i + 1;
            return (E) elementData[i];
        }
    }

    @Override
    public String toString() {
        return "MyArrayList{" + Arrays.toString(elementData) +
                '}';
    }
}
