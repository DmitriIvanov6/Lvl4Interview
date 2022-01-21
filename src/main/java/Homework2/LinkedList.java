package Homework2;

public interface LinkedList<E> extends Iterable<E> {

    void addFirst(E value); //убрать

    void add(E value);

    void add(E value, int position);


    E removeFirst();

    boolean remove(E value);

    E removeByIndex(int index);

    boolean contains(E value);

    int size();

    boolean isEmpty();

    void display();

    E getFirst();

    E get(int index);

    class Node<E> {
        E item;
        Node<E> next;
        Node<E> previous;

        public Node(E item, Node<E> next) {
            this(item, next, null);
        }

        public Node(E item, Node<E> next, Node<E> previous) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }

    }
}

