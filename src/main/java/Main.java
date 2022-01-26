import Homework3.AdditrionalTasks.CatchTeCycle;
import Homework3.AdditrionalTasks.MyOneSideLinkedList;
import Homework3.AdditrionalTasks.TurnTheList;
import Homework3.Counter.Counter;
import Homework3.Counter.CounterThread;
import Homework3.PingPong;

public class Main {

    public static void main(String[] args) {

//        Task1
        PingPong pingPong = new PingPong();
        new Thread(pingPong::pong).start();
        new Thread(pingPong::ping).start();


////    Task2
//
    Counter counter = new Counter();
        for(int i=0; i<200; i++) {
        CounterThread ct = new CounterThread(counter);
        ct.start();
    }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter:" + counter.getCounter());



//        Task3

        MyOneSideLinkedList<Integer> list = new MyOneSideLinkedList<>();
        TurnTheList<Integer> turnTheList = new TurnTheList<>();
        CatchTeCycle<Integer> catchTeCycle = new CatchTeCycle<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.display();
//        turnTheList.turn(list);
//        list.display();
        catchTeCycle.putTheCycle(4, list);
        System.out.println(catchTeCycle.findCycle(list));



    }
}
