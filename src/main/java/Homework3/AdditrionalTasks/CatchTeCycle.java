package Homework3.AdditrionalTasks;

public class CatchTeCycle<E> {

    public boolean findCycle(MyOneSideLinkedList<E> list) {
        OneSideLinkedList.Node<E> slow = list.firstElement;
        OneSideLinkedList.Node<E> fast = list.firstElement;
        boolean flag = false;
        while (true) {
            if (slow.next == null || fast.next == null) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    public void putTheCycle(int position, MyOneSideLinkedList<E> list) {
        OneSideLinkedList.Node<E> current = list.firstElement;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        list.lastElement.next = current;

    }

}
