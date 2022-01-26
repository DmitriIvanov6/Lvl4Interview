package Homework3.AdditrionalTasks;

public class TurnTheList<E> {

    public MyOneSideLinkedList<E> turn(MyOneSideLinkedList<E> list) {
        OneSideLinkedList.Node<E> current = list.firstElement;
        OneSideLinkedList.Node<E> prev = list.firstElement;
        OneSideLinkedList.Node<E> next = list.firstElement.next;
        list.lastElement = current;
        list.lastElement.next = null;
        for (int i = 0; i < list.size - 2; i++) {
            current = next;
            next = current.next;
            current.next = prev;
            prev = current;
        }
        list.firstElement = next;
        list.firstElement.next = current;
        return list;
    }


}
