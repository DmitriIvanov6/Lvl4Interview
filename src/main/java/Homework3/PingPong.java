package Homework3;

public class PingPong {

    private String ball = "pong";

    public synchronized void ping()  {
        int count = 0;
        while (count < 5) {
            while (this.ball.equals("ping") ) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println("Ping!");
            ball = "ping";
            this.notifyAll();
        }

    }

    public synchronized void pong() {
        int count = 0;
        while (count < 5) {
            while(this.ball.equals("pong")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            System.out.println("Pong!");
            ball = "pong";
            this.notifyAll();
        }

    }
}
