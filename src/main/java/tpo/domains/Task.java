package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "tasks")
public class Task implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_generator")
    private Integer id;

    @Column(name = "question")
    private String question;

    @OneToMany(mappedBy = "task", targetEntity = Answer.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    @Column(name = "correct_answer")
    private Integer correctAnswer;

    @Column(name = "score")
    private Double score;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;
}
