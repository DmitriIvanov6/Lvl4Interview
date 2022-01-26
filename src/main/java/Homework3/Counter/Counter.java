package Homework3.Counter;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {


        private long counter = 0L;

        ReentrantLock locker = new ReentrantLock();

        public void increaseCounter() {
            locker.lock();
            try {
                counter++;
            } finally {
                locker.unlock();
            }

        }

        public long getCounter() {
            return counter;
    }



}
