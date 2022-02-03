import Homework5.Student;
import Homework5.StudentDAO;
import Task1.Person;
import Task2.Lorry;
import Task3.AreaCalculator;
import Task3.Circle;
import Task3.Square;
import Task3.Triangle;

public class Main {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student("New Student", 5);
        Student changeStudent = new Student(4L,"Change student", 2);
        Student wrongStudent = new Student(1001L,"Change student", 2);

        studentDAO.fillTheTable(1000);
        System.out.println(studentDAO.findById(5));
        System.out.println(studentDAO.saveOrUpdate(student));
        System.out.println(studentDAO.saveOrUpdate(changeStudent));
        System.out.println(studentDAO.saveOrUpdate(wrongStudent));
        System.out.println(studentDAO.findAll().toString());
        studentDAO.deleteById(2);
        studentDAO.deleteById(1001);
        System.out.println(studentDAO.findAll().toString());
    }

}
