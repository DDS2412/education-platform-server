package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;


@Data
@Accessors(chain = true)
@Entity
@Table(name = "tests")
public class Test implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_generator")
    private Integer id;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "test_uniq_number")
    private Integer testUniqNumber;

    @OneToMany(mappedBy = "test", targetEntity = Task.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    @OneToMany(mappedBy = "test", targetEntity = UserTask.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTask> userTests;
}
