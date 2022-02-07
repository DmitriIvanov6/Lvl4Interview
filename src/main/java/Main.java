import Task1.Person;
import Task2.Lorry;
import Task3.AreaCalculator;
import Task3.Circle;
import Task3.Square;
import Task3.Triangle;

public class Main {

    public static void main(String[] args) {

//        Task1

        Person person = new Person.PersonBuilder()
                .addFirstName("Ivan")
                .addLastName("Ivanov")
                .addMiddleName("Ivanovich")
                .addAge(10)
                .addCountry("Russia")
                .addAddress("address")
                .addGender("male")
                .addPhone("123")
                .build();
        System.out.println(person);

//        Task2
        Lorry lorry = new Lorry();
        lorry.move();

//        Task3

        Circle circle = new Circle(5);
        Square square = new Square(3);
        Triangle triangle = new Triangle(2, 3);
        System.out.println(AreaCalculator.calcArea(circle));
        System.out.println(AreaCalculator.calcArea(square));
        System.out.println(AreaCalculator.calcArea(triangle));
    }

}
