package Homework3.AdditrionalTasks;

public interface OneSideLinkedList <E> extends Iterable<E> {

    void add(E value);
    E remove(int position);

    class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

    }

}
