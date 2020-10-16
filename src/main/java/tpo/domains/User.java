package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User implements Identifiable<Integer>  {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private Integer id;

    private String login;

    private String password;

    private String type;

    private String token;

    @ManyToMany(mappedBy = "users")
    private List<Chat> chats;

    @OneToMany(mappedBy = "user", targetEntity = UserTest.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTest> tests;

}
