package Homework5;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {

    public static SessionFactory buildSF() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

}
