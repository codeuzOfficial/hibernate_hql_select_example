package dasturlash.uz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
//        saveStudents();
//        simpleSelect();
//        partialKeyword();
//        selectUsingEntityConstructor();
        selectUsingNonEntityConstructor();
    }

    public static void simpleSelect() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentEntity> query = session.createQuery("from StudentEntity");
        List<StudentEntity> list = query.list();

        for (StudentEntity entity : list) {
            System.out.println(entity);
        }

        factory.close();
        session.close();
    }

    public static void usingSelectKeyword() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentEntity> query = session.createQuery("SELECT s from StudentEntity as s");
        List<StudentEntity> list = query.list();

        for (StudentEntity entity : list) {
            System.out.println(entity);
        }

        factory.close();
        session.close();
    }

    public static void partialKeyword() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<Object[]> query = session.createQuery("SELECT s.id, s.name, s.surname from StudentEntity as s");
        List<Object[]> list = query.list();

        for (Object[] obj : list) {
            System.out.println(obj[0]); // get id
            System.out.println(obj[1]); // get name
            System.out.println(obj[2]); // get surname
        }

        factory.close();
        session.close();
    }

    public static void selectUsingEntityConstructor() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentEntity> query = session.createQuery("SELECT new StudentEntity (s.id, s.name, s.surname) from StudentEntity as s");
        List<StudentEntity> list = query.list();

        for (StudentEntity student : list) {
            System.out.println(student); // get id
        }

        factory.close();
        session.close();
    }

    public static void selectUsingNonEntityConstructor() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query<StudentShortInfoDTO> query = session.createQuery("SELECT new dasturlash.uz.StudentShortInfoDTO(s.id, s.name, s.surname) from StudentEntity as s");
        List<StudentShortInfoDTO> list = query.list();

        for (StudentShortInfoDTO student : list) {
            System.out.println(student);
        }

        factory.close();
        session.close();
    }

    public static void saveStudents() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        // students
        StudentEntity s1 = new StudentEntity();
        s1.setName("Ali");
        s1.setSurname("Aliyev");
        s1.setPhone("915721213");
        s1.setBirthDate(LocalDate.of(2024, 1, 1));
        session.save(s1);

        StudentEntity s2 = new StudentEntity();
        s2.setName("Valish");
        s2.setSurname("Valiyev");
        s2.setPhone("123456789");
        s2.setBirthDate(LocalDate.of(2024, 1, 1));
        session.save(s2);

        StudentEntity s3 = new StudentEntity();
        s3.setName("Toshmat");
        s3.setSurname("Toshmatov");
        s3.setPhone("123456789");
        s3.setBirthDate(LocalDate.of(2024, 1, 1));
        session.save(s3);

        StudentEntity s4 = new StudentEntity();
        s4.setName("Eshmat");
        s4.setSurname("Eshmatov");
        s4.setPhone("123456789");
        s4.setBirthDate(LocalDate.of(2024, 1, 1));
        session.save(s4);

        t.commit();

        factory.close();
        session.close();
    }
}