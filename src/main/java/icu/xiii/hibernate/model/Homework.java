package icu.xiii.hibernate.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "mark", nullable = false)
    private int mark;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public Homework() {

    }

    public Homework(String description, LocalDate deadline, int mark) {
        this();
        this.description = description;
        this.deadline = deadline;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", mark=" + mark +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return id != null && Objects.equals(getId(), homework.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
