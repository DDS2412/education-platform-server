package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "answers")
public class Answer implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_generator")
    private Integer id;

    @Column(name = "text_answer")
    private String textAnswer;

    @Column(name = "number_of_answer")
    private Integer numberOfAnswer;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
}
