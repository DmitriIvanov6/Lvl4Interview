package Homework5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class StudentDAO {

    SessionFactory sessionFactory = SessionFactoryBuilder.buildSF();
    Session session = sessionFactory.openSession();

    public Student findById(long id) {
        return session.find(Student.class, id);
    }

    public List<Student> findAll() {
        return session.createQuery("SELECT a FROM Student a", Student.class).getResultList();
    }

    public Student saveOrUpdate(Student student) {
        long id = student.getId();

        if (id == 0L) {
            session.getTransaction().begin();
            session.persist(student);
            id = student.getId();
            session.getTransaction().commit();
        } else {
            Student studentToChange = findById(id);
            if (studentToChange != null) {
                session.getTransaction().begin();
                studentToChange.setName(student.getName());
                studentToChange.setMark(student.getMark());
                session.merge(studentToChange);
                session.getTransaction().commit();
            } else {
                System.out.println("Студента с таким ID не существует");
                return student;
            }
        }
        return findById(id);
    }

    public void deleteById(long id) {
        if (findById(id) != null) {
            Student toDelete = session.find(Student.class, id);
            session.getTransaction().begin();
            session.remove(toDelete);
            session.getTransaction().commit();
        } else {
            System.out.println("Нет студента с таким id");
        }
    }

    public void fillTheTable(int quantity) {
        session.getTransaction().begin();
        for (int i = 1; i <= quantity; i++) {
            Student student = new Student();
            student.setName("Student" + i);
            student.setMark((int) (Math.random() * (5 - 1 + 1) + 1));
            session.persist(student);
        }
        session.getTransaction().commit();
    }
}
