package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "user_tests")
public class UserTest implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_test_generator")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private Test test;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "test", targetEntity = UserTask.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTask> tasks;
}
