package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "chats")
public class Chat implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_generator")
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "chat", targetEntity = Message.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @ManyToMany
    @JoinTable(
            name = "chats_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
