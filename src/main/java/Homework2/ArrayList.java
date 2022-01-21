package Homework2;

import java.util.List;

public interface ArrayList<E> extends Iterable<E> {

    void add(E value);

    void add(E value, int index);

    E remove(int index);

    boolean removeObject(E value);

    void trimToSize();


}
