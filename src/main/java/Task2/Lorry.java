package Task2;

public class Lorry extends Car implements Movable, Stoppable {

    @Override
    public void move(){
        System.out.println("Car is moving");
    }

    @Override
    public void stop(){
        System.out.println("Car is stop");
    }

    @Override
    void open() {


    }
}
