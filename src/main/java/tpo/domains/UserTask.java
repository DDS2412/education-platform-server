package tpo.domains;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_tasks")
public class UserTask {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_task_generator")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private UserTest test;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;

    @Column(name = "user_answer")
    private Integer userAnswer;

    @Column(name = "score")
    private Double score;
}
