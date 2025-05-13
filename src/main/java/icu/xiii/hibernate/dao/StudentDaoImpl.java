package icu.xiii.hibernate.dao;

import icu.xiii.hibernate.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class StudentDaoImpl implements GenericDao<Student, Long> {

    @PersistenceContext
    private final EntityManager em;

    public StudentDaoImpl(EntityManager em) {
        this.em = em;
    }

    public void save(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.flush();
        em.getTransaction().commit();
    }

    public void save(List<Student> students) {
        em.getTransaction().begin();
        students.forEach(em::persist);
        em.flush();
        em.getTransaction().commit();
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public Student findByEmail(String email) {
        return em.createQuery("FROM Student WHERE email = :email ", Student.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public List<Student> findAll() {
        return em.createQuery("FROM Student", Student.class)
                .getResultList();
    }

    public Student update(Student student) {
        save(student);
        return student;
    }

    public boolean deleteById(Long id) {
        Student student = findById(id);
        if (student != null) {
            em.getTransaction().begin();
            em.remove(student);
            em.flush();
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
