package tpo.domains;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "messages")
public class Message implements Identifiable<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    private Integer id;

    private String text;

    private String type;

    @Column(name = "meta_information")
    private String metaInformation;

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
