package icu.xiii.hibernate;

import icu.xiii.hibernate.dao.StudentDaoImpl;
import icu.xiii.hibernate.model.Homework;
import icu.xiii.hibernate.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("xiii")) {
            try (EntityManager em = emf.createEntityManager()) {
                StudentDaoImpl studentDao = new StudentDaoImpl(em);

                Homework homework = new Homework("Test ABC 123", LocalDate.of(2025, 10, 7), 1);
                Student bob = new Student("Bob", "Smith", "bob@mail.com");
                bob.addHomework(new Homework("Test 123", LocalDate.of(2025, 10, 1), 1));
                bob.addHomework(homework);
                bob.addHomework(new Homework("Test DEF 456", LocalDate.of(2025, 10, 14), 1));

                Student john = new Student("John", "Doe", "john@mail.com");
                john.addHomework(new Homework("Test ABC 123", LocalDate.of(2025, 10, 7), 1));

                Student jane = new Student("Jane", "Jane", "jane@mail.com");

                studentDao.save(List.of(bob, john, jane));

                bob.removeHomework(homework);
                studentDao.update(bob);

                jane.addHomework(new Homework("Test 123", LocalDate.of(2025, 10, 1), 1));
                jane.addHomework(new Homework("Test ABC 123", LocalDate.of(2025, 10, 7), 1));
                studentDao.update(jane);

                System.out.println("findById(3L): " + studentDao.findById(3L));
                System.out.println("findByEmail(\"bob@mail.com\"): " + studentDao.findByEmail("bob@mail.com"));

                if (studentDao.deleteById(2L)) {
                    System.out.println("deleteById(2L): done");
                } else {
                    System.out.println("deleteById(2L): fail");
                }

                System.out.println("Students: ");
                studentDao.findAll().forEach(s -> {
                    System.out.println(s.toString());
                });
            }
        }

    }
}
